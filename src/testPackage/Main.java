package testPackage;

import algorithmsPackage.Algorithm;
import algorithmsPackage.NonPreemptiveShortestJobFirst;
import dataPackage.Process;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        String filePath = "./dataSets.ser";

//        System.out.println(DataGenerator.generateDataFile(filePath));

        ArrayList<Process> processes;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))){
            int length = inputStream.readInt();

            for (int i=0; i<length; i++){
                processes = (ArrayList<Process>)inputStream.readObject();

                Algorithm algorithm = new NonPreemptiveShortestJobFirst(processes);
                System.out.println("waiting time: " + algorithm.getAverageWaitingTime());
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
