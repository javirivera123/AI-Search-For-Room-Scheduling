AI-Search-For-Room-Scheduling
======

### About the program


To view the main method for this assignment [Click Here](https://github.com/javirivera123/AI-Search-For-Room-Scheduling/blob/master/src/com/utep/cs/Main/Main.java).

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
