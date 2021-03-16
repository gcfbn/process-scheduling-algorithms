package dataPackage.comparators;

import dataPackage.Process;

import java.util.Comparator;

public class arrivalTimeComparator implements Comparator<Process> {

    @Override
    public int compare(Process p1, Process p2) {
        return Double.compare(p1.getArrivalTime(), p2.getArrivalTime());
    }
}
