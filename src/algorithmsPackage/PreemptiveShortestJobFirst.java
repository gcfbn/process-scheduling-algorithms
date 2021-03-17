package algorithmsPackage;

import dataPackage.Process;
import dataPackage.comparators.ArrivalTimeComparator;
import dataPackage.comparators.DurationComparator;
import dataPackage.comparators.RemainingTimeComparator;

import java.util.ArrayList;

public class PreemptiveShortestJobFirst implements Algorithm {

    private final static double QUANTUM_OF_TIME = 1.0;

    private ArrayList<Process> processes;

    public PreemptiveShortestJobFirst(ArrayList<Process> processes) {
        this.processes = processes;
    }

    @Override
    public double getAverageWaitingTime() {

        processes.sort(new ArrivalTimeComparator());

        Process currentProcess = processes.get(0);
        double currentTime = currentProcess.getArrivalTime();

        int completedProcesses = 0;

        // create list with processes that already arrived
        ArrayList<Process> queue = new ArrayList<>();
        queue.add(currentProcess);

        while (completedProcesses < processes.size()) {

            // jump in time to the arrival of next process
            if (currentTime < currentProcess.getArrivalTime()) currentTime = currentProcess.getArrivalTime();


            if (currentProcess.getRemainingTime() >= QUANTUM_OF_TIME) {

                currentTime += QUANTUM_OF_TIME;
                currentProcess.setRemainingTime(currentProcess.getRemainingTime() - QUANTUM_OF_TIME);
            } else {   // process will be completed

                currentTime += currentProcess.getRemainingTime();

                currentProcess.setRemainingTime(0.0);
                currentProcess.setIsDone(true);
                currentProcess.setCompletionTime(currentTime);

                completedProcesses++;

                // process has been completed, so it should be removed from the queue
                queue.remove(0);

            }

            // add to the queue processes that have arrived
            int j = 0;
            Process tempProcess = processes.get(j);
            while (tempProcess.getArrivalTime() <= currentTime && j < processes.size()) {

                // if processes is not completed and is not in the queue, add it to the queue
                if (!tempProcess.getIsDone() && !queue.contains(tempProcess))
                    queue.add(tempProcess);

                j++;
                if (j < processes.size()) tempProcess = processes.get(j);
            }

            queue.sort(new RemainingTimeComparator());

            if (!queue.isEmpty())
                currentProcess = queue.get(0);

                // if there is no unexecuted process with arrivalTime <= currentTime,
                // find process with shortest arrivalTime that is not yet executed
            else if (completedProcesses < processes.size()) {

                int k = 0;
                Process shortestProcess = processes.get(0);

                while (shortestProcess.getIsDone() && k < processes.size() - 1)
                    shortestProcess = processes.get(++k);

                currentProcess = shortestProcess;
                queue.add(currentProcess);
            }
            // in other case, all processes are done and algorithm ends

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
