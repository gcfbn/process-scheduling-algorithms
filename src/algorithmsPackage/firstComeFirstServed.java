package algorithmsPackage;

import dataPackage.Process;
import dataPackage.comparators.arrivalTimeComparator;

import java.util.ArrayList;

public class firstComeFirstServed implements Algorithm{

    private final ArrayList<Process> processList;

    public firstComeFirstServed(ArrayList<Process> processList) {
        this.processList = processList;
    }

    @Override
    public float getAverageWaitingTime() {

        processList.sort(new arrivalTimeComparator());

        double totalWaitingTime = 0.0;
        double currentTime = 0.0;

        for (Process p : processList){

            //jump in time to the next process
            if (currentTime < p.getArrivalTime()) currentTime = p.getArrivalTime();

            currentTime += p.getDuration();
            p.setCompletionTime(currentTime);
            p.setIsDone(true);
            p.setRemainingTime(0.0);

            totalWaitingTime += (p.getCompletionTime() - p.getDuration() - p.getArrivalTime());
        }

        return (float) (totalWaitingTime / processList.size());
    }
}
