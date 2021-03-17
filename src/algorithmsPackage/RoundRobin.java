package algorithmsPackage;

import dataPackage.Process;
import dataPackage.comparators.ArrivalTimeComparator;

import java.util.ArrayList;

public class RoundRobin implements Algorithm{

    private static final double QUANTUM_OF_TIME = 0.5;

    private ArrayList<Process> processes;

    public RoundRobin(ArrayList<Process> processes) {
        this.processes = processes;
    }

    @Override
    public double getAverageWaitingTime() {

        processes.sort(new ArrivalTimeComparator());

        ArrayList<Process> queue = new ArrayList<>();

        Process currentProcess = processes.get(0);
        int currentProcessIndex = 0;
        queue.add(currentProcess);

        int completedProcesses = 0;
        double currentTime = currentProcess.getArrivalTime();

        while (completedProcesses < processes.size()){

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
                queue.remove(currentProcess);
            }

            // add to the queue processes that have arrived
            QueueHelper.addNewProcesses(currentTime, queue, processes);

            queue.sort(new ArrivalTimeComparator());

            if (!queue.isEmpty()){

                // if currentProcess is still in the queue, index should be changed to next process index
                if(!currentProcess.getIsDone())
                    currentProcessIndex++;

                if (currentProcessIndex >= queue.size()) currentProcessIndex = 0;

                currentProcess = queue.get(currentProcessIndex);
            }
            // if there is no unexecuted process with arrivalTime <= currentTime,
            // find process with shortest arrivalTime that is not yet executed
            else if (completedProcesses < processes.size()) {

                int k = 0;
                Process nextProcess = processes.get(0);

                while (nextProcess.getIsDone() && k < processes.size() - 1)
                    nextProcess = processes.get(++k);

                currentProcess = nextProcess;
                currentProcessIndex = 0;

                queue.add(nextProcess);
            }
            // in other case, all processes are done and algorithm ends
        }

        double totalWaitingTime = 0.0;

        for (Process p : processes) {
            double x = p.getCompletionTime() - p.getDuration() - p.getArrivalTime();
            totalWaitingTime += (x);
        }

        return totalWaitingTime / processes.size();
    }
}
