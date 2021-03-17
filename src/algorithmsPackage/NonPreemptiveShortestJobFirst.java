package algorithmsPackage;

import dataPackage.Process;
import dataPackage.comparators.ArrivalTimeComparator;
import dataPackage.comparators.DurationComparator;

import java.util.ArrayList;

public class NonPreemptiveShortestJobFirst implements Algorithm {

    private ArrayList<Process> processes;

    public NonPreemptiveShortestJobFirst(ArrayList<Process> processes) {
        this.processes = new ArrayList<>(processes);
    }

    @Override
    public double getAverageWaitingTime() {

        processes.sort(new ArrivalTimeComparator());

        Process currentProcess = processes.get(0);
        double currentTime = currentProcess.getArrivalTime();

        // create list with processes that already arrived
        ArrayList<Process> queue = new ArrayList<>();
        queue.add(currentProcess);

        for (int i = 0; i < processes.size(); i++) {

            // jump in time to the arrival of next process
            if (currentTime < currentProcess.getArrivalTime()) currentTime = currentProcess.getArrivalTime();

//            System.out.println("current time: " + currentTime);
//            System.out.println("current process: " + currentProcess.toString());

            // execute process
            currentProcess.setRemainingTime(0.0);
            currentProcess.setIsDone(true);

            // add duration of the process to the current time
            currentTime += currentProcess.getDuration();
            currentProcess.setCompletionTime(currentTime);

            // remove process from the queue
            queue.remove(0);

            int j = 0;
            Process tempProcess = processes.get(j);
            while (tempProcess.getArrivalTime() <= currentTime && j < processes.size()) {

                // if processes is not executed and is not in the queue, add it to the queue
                if (!tempProcess.getIsDone() && !queue.contains(tempProcess))
                    queue.add(tempProcess);

                j++;
                if (j < processes.size()) tempProcess = processes.get(j);
            }

            queue.sort(new DurationComparator());

            // if there is no unexecuted process with arrivalTime <= currentTime,
            // currentProcess = process with shortest arrivalTime
            if (!queue.isEmpty())
                currentProcess = queue.get(0);
            else if (i < processes.size() - 1)
                currentProcess = processes.get(i + 1);
        }

        double totalWaitingTime = 0.0;

        // add waiting time and calculate average
        for (Process p : processes) {
            double x = p.getCompletionTime() - p.getDuration() - p.getArrivalTime();
            totalWaitingTime += (x);
        }

        return totalWaitingTime / processes.size();
    }
}
