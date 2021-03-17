package dataPackage.comparators;

import dataPackage.Process;

import java.util.Comparator;

public class DurationComparator implements Comparator<Process> {

    @Override
    public int compare(Process p1, Process p2) {
        return Double.compare(p1.getDuration(), p2.getDuration());
    }
}
