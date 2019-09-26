import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Truck  {

    private int truckID;
    private int weight;
    private int waitingTime;
    private int timeToLeave;

    public Truck(int enteredWeight, int time){
        truckID=time/3;
        weight=enteredWeight;
        waitingTime=0;
    }

    public int getTimeToLeave(){
        return timeToLeave;
    }
    public void timeToWait(int queue){
        timeToLeave=queue+weight;
    }

    public void timePass(){
        timeToLeave--;
        waitingTime++;
    }

    public int getWeight() {
        return weight;
    }

    public int getTruckID() {
        return truckID;
    }

    public int getWaitingTime() {
        return waitingTime;
    }
}