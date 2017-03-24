package com.utep.cs.artificial.intelligence;

public class Schedule {
  int[][] schedule;

  Schedule(int nRooms, int nTimeSlots) {
    schedule = new int[nRooms][nTimeSlots];
  }
}
