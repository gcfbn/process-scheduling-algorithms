package algorithmsPackage;

import dataPackage.Process;

import java.util.ArrayList;

public class QueueHelper {

    public static void addNewProcesses(double currentTime, ArrayList<Process> queue, ArrayList<Process> processes) {

        // add to the queue processes that have arrived
        int j = 0;
        Process tempProcess = processes.get(j);
        while (tempProcess.getArrivalTime() <= currentTime && j < processes.size()) {

            // if process is not completed and is not in the queue, add it to the queue
            if (!tempProcess.getIsDone() && !queue.contains(tempProcess))
                queue.add(tempProcess);

            j++;
            if (j < processes.size()) tempProcess = processes.get(j);
        }
    }
}
