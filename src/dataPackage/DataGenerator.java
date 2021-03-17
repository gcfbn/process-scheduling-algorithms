package dataPackage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataGenerator {


    // set distribution of processes duration
    // when creating a new process, this class gets a random probability in range (0.0, 1.0) and matches it with ranges:
    // 1) (0.0, SHORT_PROCESS_RANGE)                     - new process is a short process
    // 2) [SHORT_PROCESS_RANGE, MEDIUM_PROCESSES_RANGE)  - new process is a medium process
    // 3) [MEDIUM_PROCESSES_RANGE, 1.0)                  - new process is a long process

    private final static double SHORT_PROCESSES_RANGE = 0.7;   // short process duration is between 0 and 30% of MAX_LENGTH
    private final static double MEDIUM_PROCESSES_RANGE = 0.9;  // medium process duration is between 30% and 70% of MAX_LENGTH


    private final static int MAX_LENGTH = 20; // max length of a single process
    private final static int NUMBER_OF_PROCESSES = 50;    // number of process in a single dataset
    private final static int MAX_ARRIVAL_TIME = 100;

    private final static int DATA_SETS = 40; // number of different dataset

    public static boolean generateDataFile(String filePath) {

        ObjectOutputStream outputStream;

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        } catch (IOException e) {
            return false;
        }

        try {
            outputStream.writeInt(DATA_SETS);   //write number of datasets to file
        } catch (IOException e) {
            return false;
        }

        for (int i = 0; i < DATA_SETS; i++) {

            ArrayList<Process> processes = new ArrayList<>();

            for (int j = 0; j < NUMBER_OF_PROCESSES; j++)
                processes.add(getRandomProcess());

            try {
                outputStream.writeObject(processes);    // write dataset to file
            } catch (IOException e) {
                return false;
            }
        }

        try {
            outputStream.close();
            return true;
        } catch (IOException | NullPointerException e) {
            return false;
        }
    }


    private static Process getRandomProcess() {

        double probability = Math.random();
        double randomDuration;

        // if random probability is in 1) range, generate duration between 0 and 0.3 * MAX_LENGTH
        if (probability < SHORT_PROCESSES_RANGE)
            randomDuration = MAX_LENGTH * Math.random() * 0.3  + 0.000001;    //+0.000001 prevents getting a process which lasts 0

            //if random probability is in 2) range, generate duration between 0.3 and 0.7 of MAX_LENGTH
        else if (probability < MEDIUM_PROCESSES_RANGE)
            randomDuration = MAX_LENGTH * (SHORT_PROCESSES_RANGE + Math.random() * (MEDIUM_PROCESSES_RANGE - SHORT_PROCESSES_RANGE));

            //if random probability is in 3) range, generate duration between 0.7 and 1.0 of MAX_LENGTH
        else randomDuration = MAX_LENGTH * (MEDIUM_PROCESSES_RANGE + Math.random() * (1.0 - MEDIUM_PROCESSES_RANGE));

        double randomArrivalTime = Math.random() * MAX_ARRIVAL_TIME;

        return new Process(randomDuration, randomArrivalTime);
    }

}
