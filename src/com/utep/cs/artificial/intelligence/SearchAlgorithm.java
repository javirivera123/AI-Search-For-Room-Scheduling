package com.utep.cs.artificial.intelligence;

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
    double T = 1000;                                      // Temperature

    while(T > 0){                                       // Evaluate solutions until the temperature cools down
      double Ec = problem.evaluateSchedule(currentSolution);   // The energy for our random solution
      Schedule mutatedSolution = switchCourses(currentSolution);  // Switch 1 course to a random room & time slot
      double En = problem.evaluateSchedule(mutatedSolution); // Evaluate this mutated solution
      double deltaE = En - Ec;    // Measure the energy difference between the mutated solution and the current solution
      if(deltaE > 0){ // If the difference is positive then swap solutions
        currentSolution = mutatedSolution;
      }
      else if((Math.exp(deltaE/T)) > 1){ // If the solution is negative find the probability of the solution being
        currentSolution = mutatedSolution; // a good solution, if it is > 1 then swap solutions
      }
      T = T / 2; // Decrement the temperature by half
    }
    return currentSolution; // Return the solution 
  }

  private Schedule switchCourses(Schedule currentSolution) {

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
