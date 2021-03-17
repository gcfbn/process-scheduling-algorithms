package dataPackage;

import java.io.Serial;
import java.io.Serializable;

public class Process implements Serializable {

    @Serial
    private static final long serialVersionUID = -7606342146735748446L;

    private final double duration, arrivalTime;
    private double completionTime, remainingTime;
    private boolean isDone = false;

    public Process(double duration, double arrivalTime) {
        this.duration = duration;
        this.arrivalTime = arrivalTime;
        this.remainingTime = duration;
    }

    @Override
    public String toString() {
        return "arrival time: " + arrivalTime + " duration: " + duration;
    }

    public void setCompletionTime(double completionTime) {
        this.completionTime = completionTime;
    }

    public void setRemainingTime(double remainingTime) {
        this.remainingTime = remainingTime;
    }

    public void setIsDone(boolean isDone){
        this.isDone = isDone;
    }

    public double getDuration() {
        return duration;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getCompletionTime() {
        return completionTime;
    }

    public double getRemainingTime() {
        return remainingTime;
    }

    public boolean getIsDone(){
        return isDone;
    }
}
