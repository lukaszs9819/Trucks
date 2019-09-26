
import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        Queue<Truck> firstQueue = new ArrayDeque<Truck>();
        Queue<Truck> secoundQueue = new ArrayDeque<Truck>();
        int lengthQueue1 = 0;
        int lengthQueue2 = 0;
        boolean programOn = true;
        int time = 0;
        int interval=3;
        while (programOn) {

            System.out.println("Enter your request:\n'arrive'-arrive of the truck\n'status'-check status of system");
            System.out.println("'step'-move system by one time unit \n'waitingTime'-check expected waiting time of selected truck");
            System.out.println("'exit'-to exit");
            String option = in.nextLine();
            System.out.println("\n" + option + '\n');
            switch (option) {
                case "arrive":
                    if(firstQueue.size()+secoundQueue.size()<12) {
                    while(interval<3) {
                        time++;
                        interval++;
                        if(firstQueue.size()>0) {
                            for (Truck item : firstQueue) {
                                item.timePass();
                            }
                            lengthQueue1--;
                            if (firstQueue.peek().getTimeToLeave() == 0) {
                                firstQueue.remove();
                            }
                        }
                        if(secoundQueue.size()>0) {
                            for (Truck item : secoundQueue) {
                                item.timePass();
                            }
                            lengthQueue2--;
                            if (secoundQueue.peek().getTimeToLeave() == 0) {
                                secoundQueue.remove();
                            }
                        }
                        /*
                        int length;
                        if(firstQueue.size()>secoundQueue.size())
                            length = secoundQueue.size();
                        else
                            length = firstQueue.size();
                        if(length>2) {
                            for(int i=0 ; i< length;i++) {
                                int counter = 0;
                                int counter1 = 0;
                                for (Truck item : firstQueue) {
                                    if (counter == i && i>2) {
                                        for(Truck item1 :secoundQueue){
                                            if (counter1 == i)
                                            {
                                                if(item.getWeight()>item1.getWeight() && item1.getTimeToLeave()-item1.getWeight()>item.getTimeToLeave()-item.getWeight()){
                                                    Truck pom = item;
                                                    item=item1;
                                                    item1=pom;
                                                }
                                            }
                                            counter1++;
                                        }
                                    }
                                    counter++;
                                }
                            }
                        }*/ //To miała być zamiana wyrazów do momentu gdy się zorientowałem że jednak się nie da XD
                    }

                        System.out.println("Enter weight of truck: ");
                        int weight = Integer.valueOf(in.nextLine());
                        Truck pom = new Truck(weight, time);

                        if (firstQueue.size()>5 || lengthQueue1 > lengthQueue2 ) {
                            pom.timeToWait(lengthQueue2);
                            lengthQueue2 += weight;
                            secoundQueue.add(pom);
                        } else {
                            pom.timeToWait(lengthQueue1);
                            lengthQueue1 += weight;
                            firstQueue.add(pom);
                        }
                        System.out.println("ID Number: " + pom.getTruckID());
                        interval = 0;
                    }
                    else{
                        System.out.println("Queue full, wait for empty place");
                    }
                    break;
                case "status":
                    System.out.println("System time: " + time + "\nKolejka pierwsza:");
                    for(Truck item: firstQueue){
                        System.out.println("Truck ID: " + item.getTruckID() + "\nWeight: " + item.getWeight() + "\nWaiting time: " + item.getWaitingTime() + "\nTime to leave: " + item.getTimeToLeave() + "\n");
                    }
                    System.out.println("Kolejka druga:");
                    for(Truck item: secoundQueue){
                        System.out.println("Truck ID: " + item.getTruckID() + "\nWeight: " + item.getWeight() + "\nWaiting time: " + item.getWaitingTime() + "\nTime to leave: " + item.getTimeToLeave()+ "\n");
                    }
                    break;
                case "step":
                    interval++;
                    time++;

                    if(firstQueue.size()>0) {
                        for (Truck item : firstQueue) {
                            item.timePass();
                        }
                        lengthQueue1--;
                        if (firstQueue.peek().getTimeToLeave() == 0) {
                            firstQueue.remove();
                        }
                    }
                    if(secoundQueue.size()>0) {
                        for (Truck item : secoundQueue) {
                            item.timePass();
                        }
                        lengthQueue2--;
                        if (secoundQueue.peek().getTimeToLeave() == 0) {
                            secoundQueue.remove();
                        }
                    }
                    int length;
                   /* if(firstQueue.size()>secoundQueue.size())
                         length = secoundQueue.size();
                    else
                        length = firstQueue.size();
                    if(length>2) {
                        for(int i=0 ; i< length;i++) {
                            int counter = 0;
                            int counter1 = 0;
                            for (Truck item : firstQueue) {
                                if (counter == i && i>2) {
                                    for(Truck item1 :secoundQueue){
                                        if (counter1 == i)
                                        {
                                          if(item.getWeight()>item1.getWeight() && item1.getTimeToLeave()-item1.getWeight()>item.getTimeToLeave()-item.getWeight()){
                                              Truck pom = item;
                                              item=item1;
                                              item1=pom;
                                          }
                                        }
                                        counter1++;
                                    }
                                }
                                counter++;
                            }
                        }
                    }*/ //To miała być zamiana wyrazów do momentu gdy się zorientowałem że jednak się nie da XD
                    if (interval==3 && firstQueue.size()+secoundQueue.size()<12)
                    {
                        System.out.println("Time passed, new truck arrive!\nEnter weight of truck: ");
                        int weight = Integer.valueOf(in.nextLine());
                        Truck pom = new Truck(weight, time);
                        if (firstQueue.size()>5 || lengthQueue1 > lengthQueue2 ) {
                            pom.timeToWait(lengthQueue2);
                            lengthQueue2 += weight;
                            secoundQueue.add(pom);

                        }
                        else {
                            pom.timeToWait(lengthQueue1);
                            lengthQueue1 += weight;
                            firstQueue.add(pom);
                        }
                        System.out.println("ID Number: " + pom.getTruckID());
                        interval = 0;
                    }
                    break;
                case "waitingTime":
                    System.out.println("Enter ID number: ");
                    int checkingID =Integer.valueOf(in.nextLine());
                    boolean buffor = true;
                    for(Truck item: firstQueue){
                        if(checkingID==item.getTruckID()){
                            System.out.println("Waiting time:" + item.getWaitingTime());
                            buffor = false;
                        }
                    }

                    for(Truck item: secoundQueue){
                        if(checkingID==item.getTruckID()) {
                            System.out.println("Waiting time:" + item.getWaitingTime());
                            buffor = false;
                        }
                    }
                    if(buffor)
                        System.out.println("There are not any truck with entered ID number");
                    break;
                case "exit":
                    programOn = false;
                    break;
                default:
                    System.out.println("Command not valid, try again");
            }
        }
    }
}

