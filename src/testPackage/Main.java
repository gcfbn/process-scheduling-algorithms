package testPackage;

import algorithmsPackage.Algorithm;
import algorithmsPackage.firstComeFirstServed;
import dataPackage.Process;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){

        String filePath = "./dataSets.ser";

//        System.out.println(DataGenerator.generateDataFile(filePath));

        ArrayList<Process> processes = null;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))){
            int length = inputStream.readInt();
            processes = (ArrayList<Process>)(inputStream.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Algorithm algorithm = new firstComeFirstServed(processes);
        System.out.println(algorithm.getAverageWaitingTime());
    }
}
