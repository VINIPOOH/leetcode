package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InsertInterval57 {
    public int[][] insert(int[][] intervals, int[] newInterval) {


        List<int[]> toReturn = new ArrayList<>(intervals.length);

        int i = 0;
        while (i < intervals.length && intervals[i][0] < newInterval[0]){
            toReturn.add(intervals[i]);
            i++;
        }

        if (i > 0 && intervals[i-1][1]> newInterval[0]){
            newInterval[0] = intervals[i-1][0];
            toReturn.removeLast();
            i--;
        }
        while (i< intervals.length && newInterval[1] >= intervals[i][0]){
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        toReturn.add(newInterval);
        while (i< intervals.length) {
            toReturn.add(intervals[i]);
            i++;
        }
        return toReturn.toArray(new int[toReturn.size()][2]);
    }
}
