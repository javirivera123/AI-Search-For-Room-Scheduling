AI-Search-For-Room-Scheduling
======

### About the program


To view the main method for this assignment [Click Here](https://github.com/javirivera123/AI-Search-For-Room-Scheduling/blob/master/src/com/utep/cs/Main/Main.java).

Contributors
======

Javi Rivera 
Oscar Ricaud
[Click Here for more info](https://github.com/javirivera123/AI-Search-For-Room-Scheduling/graphs/contributors)

HW2: Scheduling
======

	 CS 4320/5314: Artificial Intelligence, Spring 2017
	Homework Assignment 2: Search for Room Scheduling

DUE: Sun, March	26 at 11:59 PM

Objective: To explore the use of different search techniques for solving a complex
optimization problem.	

Groups:	You may	optionally work	in groups of two (2) students for this	project.	

The Room Scheduling Problem:
======

The scenario you will is based on creating a scheudle that assigns courses to classrooms based on some criteria. The goal of your algorithim is to find the best possible shcedules as quickly as possibly using a variety of search techniques.

More specifically, we have a set of N rooms, a set of M courses that need to be scheduled, and a set of L buildings.

Each building has an associated location, given by (x,y) coordinats.

Each room has the following properties:

	 1) A building 
	 2) A maximum capcity
	 
Each course has the following properties:

	 1) An enrollment number
	 2) A value for being scheduled
	 3) A list of values for each of 10 available time slots
	 4) A preferred building 
	 
There are 10 possible time slots, and each room can have only one class scheduled in each time slot. In addition, courses 
can only be scheduled in rooms where the capacity is greater than enrollment.

For each course, there is a list of values for each time slot. A value of 0 corresponds to infeasible (i.e the course cannot be held at this time), while any other positive value is a bonus given for scheduling the course in that particular time slot. 

Courses also have preferred buildings. Courses scheduled in another building receive a penalty based on the distance between where the course is actually scheduled and the preferred building. 

A solution is a mapping from rooms and time slots to courses. That is, each room can be assigned to hold one course in each available time slot. Courses are identified by their indicies from 0 to N-1. 

The overall value of a schedule is calculated as follows: 

	 - NEGATIVE_INFINITY if the schedule is invalid 
	 (e.g courses assigned multiple times to more than one room or time slot).
	 
	 - The sum of the values and time slot bonuses for all courses assigned to 
	 valid rooms (rooms with a large enough capacity).
	 
	 - Subtracting the sum of the penalties for scheduling courses away from their preferred building. 
	
You can find the exact definitions of these in the provided code. 

Your assignment:
=======

We have discussed many different search algorithims for problem solving, including BFS, DFS, iterative deepening, IDS, A*, Hill Climbing, simulated annealing and genetic algorithims. Your assignment is to implement and test two different search methods for solving this scheduling problem. 

1) Impelment a simulated annealing algorithim for solving this problem, and experiement with different 		temperature settings.

2) Implement backtracking search for constraint for solving this problem, including at least three heuristics for improving the search algorithim.

For both of these algoirthims you should test the performance of your methods empirecally. For the simulated annealing approach you should show how different temperature setting affect the performance. For the CSP approach, you should know how each of the heuristics improves the performance of your algorithm.


Deliverables:
=======

You will turn in two things:

1) Your Java code for the project, including a jar file and instructions for running your code.

2) A brief (2-4 pages) report describing the search methods you implmented and the results of
	your performance testing. You must include data from empirical tests that compare the performance 
	of your algoirthims (including different temperatures and heauristics) for problems of increasing size.
	
You will be evaluated based on whether you have working implemenetations of the algorithms that improve over simple baseline methods, as well as the quality of yoru testing and evaluation of the performance of your algorithms.

