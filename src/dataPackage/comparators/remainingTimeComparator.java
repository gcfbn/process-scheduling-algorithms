package dataPackage.comparators;

import dataPackage.Process;

import java.util.Comparator;

public class remainingTimeComparator implements Comparator<Process> {

    @Override
    public int compare(Process p1, Process p2) {
        return Double.compare(p1.getRemainingTime(), p2.getRemainingTime());
    }
}
