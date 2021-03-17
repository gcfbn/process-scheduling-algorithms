package testPackage;

import algorithmsPackage.*;
import dataPackage.DataGenerator;
import dataPackage.Process;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String filePath = "./dataSets.ser";

        System.out.println(DataGenerator.generateDataFile(filePath));

        System.out.println();

        // FCFS
        {
            System.out.println("FCFS algorithm:");

            ArrayList<Process> processes;

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {

                int length = inputStream.readInt();
                System.out.println("Number of datasets: " + length);

                double totalWaitingTime = 0.0;

                for (int i = 0; i < length; i++) {
                    processes = (ArrayList<Process>) inputStream.readObject();

                    Algorithm algorithm = new FirstComeFirstServed(processes);
                    totalWaitingTime += algorithm.getAverageWaitingTime();
                }

                double averageWaitingTime = totalWaitingTime / length;
                System.out.println("Average waiting time: " + averageWaitingTime);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        System.out.println();

        // Non-preemptive SJF
        {
            System.out.println("Non-preemptive SJF algorithm:");

            ArrayList<Process> processes;

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {

                int length = inputStream.readInt();
                System.out.println("Number of datasets: " + length);

                double totalWaitingTime = 0.0;

                for (int i = 0; i < length; i++) {
                    processes = (ArrayList<Process>) inputStream.readObject();

                    Algorithm algorithm = new NonPreemptiveShortestJobFirst(processes);
                    totalWaitingTime += algorithm.getAverageWaitingTime();
                }

                double averageWaitingTime = totalWaitingTime / length;
                System.out.println("Average waiting time: " + averageWaitingTime);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        System.out.println();

        // Preemptive SJF
        {
            System.out.println("Preemptive SJF algorithm:");

            ArrayList<Process> processes;

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {

                int length = inputStream.readInt();
                System.out.println("Number of datasets: " + length);

                double totalWaitingTime = 0.0;

                for (int i = 0; i < length; i++) {
                    processes = (ArrayList<Process>) inputStream.readObject();

                    Algorithm algorithm = new PreemptiveShortestJobFirst(processes);
                    totalWaitingTime += algorithm.getAverageWaitingTime();
                }

                double averageWaitingTime = totalWaitingTime / length;
                System.out.println("Average waiting time: " + averageWaitingTime);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        System.out.println();

        // Round Robin
        {
            System.out.println("Round Robin algorithm:");

            ArrayList<Process> processes;

            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {

                int length = inputStream.readInt();
                System.out.println("Number of datasets: " + length);

                double totalWaitingTime = 0.0;

                for (int i = 0; i < length; i++) {
                    processes = (ArrayList<Process>) inputStream.readObject();

                    Algorithm algorithm = new RoundRobin(processes);
                    totalWaitingTime += algorithm.getAverageWaitingTime();
                }

                double averageWaitingTime = totalWaitingTime / length;
                System.out.println("Average waiting time: " + averageWaitingTime);

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

//    Example output:
//
//    true
//
//    FCFS algorithm:
//    Number of datasets: 40
//    Average waiting time: 137.6926975828731
//
//    Non-preemptive SJF algorithm:
//    Number of datasets: 40
//    Average waiting time: 62.68004133227023
//
//    Preemptive SJF algorithm:
//    Number of datasets: 40
//    Average waiting time: 60.44126586435813
//
//    Round Robin algorithm:
//    Number of datasets: 40
//    Average waiting time: 139.56037233097703
//
//    Process finished with exit code 0
}
