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

    // get an empty solution to start from
    Schedule solution = problem.getEmptySchedule();

    solution = naiveBaseline(problem, deadline);
    double T = 1000;
    while(T > 0){
      double Ec = problem.evaluateSchedule(solution);
      Schedule mutatedSolution = switchCourses(solution);
      double En = problem.evaluateSchedule(mutatedSolution);
      double deltaE = En - Ec;
      if(deltaE > 0){
        solution = mutatedSolution;
      }
      else if((Math.exp(deltaE/T)) > 1){
        solution = mutatedSolution;
      }
      T = T / 2;
    }
    return solution;
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
