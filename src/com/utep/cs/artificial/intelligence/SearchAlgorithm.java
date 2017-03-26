package com.utep.cs.artificial.intelligence;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SearchAlgorithm {

  // Your search algorithm should return a solution in the form of a valid
  // schedule before the deadline given (deadline is given by system time in ms)
  public Schedule solve(SchedulingProblem problem, long deadline) {

    // get an empty solution to start from
    Schedule solution = problem.getEmptySchedule();
    // YOUR CODE HERE


    return solution;
  }

  public Schedule simulatedAnnealing(SchedulingProblem problem, long deadline) {

    Schedule currentSolution = naiveBaseline(problem, deadline);  // Begin with a random solution
    double T = 1000000;                                      // Temperature
    double randomNum = random(0, 1);
    while(T > 0){                                       // Evaluate solutions until the temperature cools down
      double Ec = problem.evaluateSchedule(currentSolution);   // The energy for our random solution
      Schedule mutatedSolution = switchCourses(currentSolution);  // Switch 1 course to a random room & time slot
      double En = problem.evaluateSchedule(mutatedSolution); // Evaluate this mutated solution
      double deltaE = En - Ec;    // Measure the energy difference between the mutated solution and the current solution
      if(deltaE > 0){ // If the difference is positive then swap solutions
        currentSolution = mutatedSolution;
      }

      else if((Math.exp(deltaE/T)) > randomNum){ // If the solution is negative find the probability of the
        // solution
        // being
        currentSolution = mutatedSolution; // a good solution, if it is > 1 then swap solutions
      }
      T = T / 2; // Decrement the temperature by half
    }
    return currentSolution; // Return the solution
  }

  private double random(int Low, int High) {
    double random = ThreadLocalRandom.current().nextDouble(Low, High);
    return random;
  }

  private Schedule switchCourses(Schedule currentSolution) {
    Random r = new Random();

    // Slot 1
    int row = 0;
    int column = 0;

    // Slot 2
    int row1 = 0;
    int column1 = 0;

    // Iterate through the two slots that we will be swapping
    for(int slot = 0 ; slot < 2; slot++) {
      // Find a random room
      int Low = 0;
      int High = currentSolution.schedule.length;
      int randomRoom = r.nextInt(High-Low) + Low;
      boolean courseIsFound = false;

      // Iterate until you find a random course excluding values of -1
      while (!courseIsFound) {
        High = 10; // # of Time slots
        int randomTimeSlot = r.nextInt(High - Low) + Low;

        // We are picky for the first slot we are trying to swap, we must make sure the random slot we pick is filled
        // and not contain a -1.
        if ((slot == 0) & (currentSolution.schedule[randomRoom][randomTimeSlot] != -1)) {
            row = randomRoom;
            column = randomTimeSlot;
            courseIsFound = true;

        } else if((slot == 1) & (currentSolution.schedule[randomRoom][randomTimeSlot] != -1)) { // Only Gets called
          // when we found slot 0.
          row1 = randomRoom;
          column1 = randomTimeSlot;
          courseIsFound = true;
        }
      }
    }
    // Store temp courses
    int tempCourse1 =  currentSolution.schedule[row][column];
    int tempCourse2 = currentSolution.schedule[row1][column1];

    // Do the swap here
    currentSolution.schedule[row][column] = tempCourse2;
    currentSolution.schedule[row1][column1] = tempCourse1;

    return currentSolution;
  }

  // This is a very naive baseline scheduling strategy
  // It should be easily beaten by any reasonable strategy
  public Schedule naiveBaseline(SchedulingProblem problem, long deadline) {

    // get an empty solution to start from
    Schedule solution = problem.getEmptySchedule();

    for (int i = 0; i < problem.courses.size(); i++) {
      Course c = problem.courses.get(i);
      boolean scheduled = false;
      for (int j = 0; j < c.timeSlotValues.length; j++) {
        if (scheduled) break;
        if (c.timeSlotValues[j] > 0) {
          for (int k = 0; k < problem.rooms.size(); k++) {
            if (solution.schedule[k][j] < 0) {
              solution.schedule[k][j] = i;
              scheduled = true;
              break;
            }
          }
        }
      }
    }
    return solution;
  }
}
