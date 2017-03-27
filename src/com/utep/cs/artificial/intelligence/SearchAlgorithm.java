package com.utep.cs.artificial.intelligence;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SearchAlgorithm {



  // Your search algorithm should return a solution in the form of a valid
  // schedule before the deadline given (deadline is given by system time in ms)
  public Schedule solve(SchedulingProblem problem, long deadline) {

    // get an empty solution
    Schedule solution = problem.getEmptySchedule();

    solution= naiveBaseline(problem, deadline); // random solution

    // final solution
    Schedule solutionFinal = problem.getEmptySchedule();

    // Create a mapping from "temperature" to "deadline"
    Map<Integer, Double> deadlineToTemperatureMap = new HashMap<Integer, Double>();
    double temperature = 100;
      for (int i = 0; i < 10; i++) {
        deadlineToTemperatureMap.put(i, temperature);
        temperature = temperature - 10;
      }
      solutionFinal = simulatedAnnealing(problem, solution, deadlineToTemperatureMap);
    return solutionFinal;
  }

  /**
   * @param problem a random problem based on the arguments of # of buildings, rooms and courses
   * @param schedule a mapping from time to "temperature"
   * @param map
   * @return a solution state
   */
  public Schedule simulatedAnnealing(SchedulingProblem problem, Schedule schedule, Map<Integer, Double> map) {
    double T = 10; // controls probability of downward steps

    Schedule current = schedule; // Random solution schedule
    // Decrease temperature by 1 degree
    int i = 0;

    while (T > 0) {

      Schedule temp = current;
      // Evaluate the of current solution
      double deltaCurr = problem.evaluateSchedule(current);

      // Modify the random solution
      Schedule next = switchCourses(temp);

      // Evaluate the modified random solution
      double deltaNext = problem.evaluateSchedule(next);

      // Find the value difference
      double deltaE = deltaNext - deltaCurr;
      double temperature = map.get(i);
      System.out.println("temperateture " + temperature);
      if (deltaE > 0) {
        current = next;
      }

      // If the value difference is >= than a random value between 0 and 1 then,
      // Assign the current solution to the modified solution, since this solution is much better
      else if (Math.exp(deltaE / temperature) >= random(0, 1)) {
          current = next;
      }
      i++;
      T--;
    }
      return current;
  }

  private double random(int Low, int High) {
    double random = ThreadLocalRandom.current().nextDouble(Low, High);
    return random;
  }

  private Schedule switchCourses(Schedule currSolution) {
    Random r = new Random();
    Schedule tempSolution = currSolution;
    // Slot 1
    int row = 0;
    int column = 0;

    // Slot 2
    int row1 = 0;
    int column1 = 0;

    // Iterate through the two slots that we will be swapping
    for(int slot = 0 ; slot < 2; slot++) {
      boolean courseIsFound = false;
      // Iterate until you find a random course excluding values of -1
      while (!courseIsFound) {
        // Find a random room
        int Low = 0;
        int High = tempSolution.schedule.length;
        int randomRoom = r.nextInt(High-Low) + Low;
        High = 10; // # of Time slots
        int randomTimeSlot = r.nextInt(High - Low) + Low;

        // We are picky for the first slot we are trying to swap, we must make sure the random slot we pick is filled
        // and not contain a -1.
        if ((slot == 0) & (tempSolution.schedule[randomRoom][randomTimeSlot] != -1)) {
            row = randomRoom;
            column = randomTimeSlot;
            courseIsFound = true;

        } else if((slot == 1)) { // Only Gets called
          // when we found slot 0.
          Low = 0;
          High = tempSolution.schedule.length;
          randomRoom = r.nextInt(High-Low) + Low;
          row1 = randomRoom;
          column1 = randomTimeSlot;
          courseIsFound = true;
        }
      }
    }
    // Store temp courses
    int tempCourse1 =  tempSolution.schedule[row][column];
    int tempCourse2 = tempSolution.schedule[row1][column1];

    // Do the swap here
    tempSolution.schedule[row][column] = tempCourse2;
    tempSolution.schedule[row1][column1] = tempCourse1;

    return tempSolution;
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
