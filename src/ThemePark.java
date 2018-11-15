
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author leahluong
 */
public class ThemePark extends JFrame {
   
    private final int timeOpen = 0;
    private final int timeClose = 60;
    private int numOfRide;
    public Ride[] totalRide;
    private int happyRider = 0;
    public static int currentTime = 0;
    public static int numOfRidersArrive;
    private double averageWaitTime, averageSpentTime, maxWaitTime=0;
    private double totalTimeWaiting, totalTimeSpent;
    
   
    public ThemePark() {
        this.numOfRide = setNumOfRide();
        this.totalRide = new Ride[this.numOfRide];
        for (int i = 0; i < this.numOfRide; i++) {
            this.totalRide[i] = new Ride();
        }
        
        
    }

    public int setNumOfRide() {
        Random r = new Random();
        int ranNumOfRide = r.nextInt(5) + 1; //randomly have 1 - 5 rides
        return ranNumOfRide;
    }

    public void run() throws InterruptedException, FileNotFoundException {
         main my = new main();
        for (int i = this.timeOpen; i < this.timeClose; i++) {//60 mins
            
            
            currentTime = i;
            System.out.println("Time:" + i);
            System.out.println("Ride:" + this.numOfRide);
            getRiders();
            String time = String.format("\nAverage Time Waiting: %.2f",getAverageTimeWaiting());
        time+= String.format("\tMax Time Waiting: %.2f",this.maxWaitTime);
        time+= String.format("\tAverage Time Spent: %.2f",getAverageTimeSpent());
            my.output.setText("Time" + i + "\n" 
                    +"Ride" + this.numOfRide+ "\n"+
                    "Happy Riders: "+ this.happyRider+
                    "\n"+this.toString()+ridesInSession()+time);
//            System.out.println(toString());
            
            processRide();
            if(numOfRidersArrive>0){
            System.out.println(toString());}
            System.out.println(ridesInSession());
            System.out.println("Happy Riders: "+ this.happyRider);
           my.setVisible(true);
            
            Thread.sleep(1000);
            
        }
        
        
               
        
    }

    public int getNumOfRiderArrive() { //get random number of guests arrive
        
        Random r = new Random();
        int randRidersArrival = r.nextInt(10);
        if (randRidersArrival > 9) {
            numOfRidersArrive = 2;
        } else if (randRidersArrival > 5) {
            numOfRidersArrive = 1;
        } else {
            numOfRidersArrive = 0;
        }
        return numOfRidersArrive;
    }

    public void getRiders() throws FileNotFoundException {
//        Person[] riderHolders = new Person[getNumOfRiderArrive()];//array to hold guests arrive
        int numGuestsArrive = getNumOfRiderArrive();
        System.out.println("num of riders arrive: " + numGuestsArrive);
        for (int i = 0; i < numGuestsArrive; i++) {
            //get random name from a file
            FileReader file = new FileReader("name.txt");
            Scanner s = new Scanner(file);
            String name="";
            Random r = new Random();
            int ranNumToLoop = r.nextInt(31)+1;
            //lop through the file for random number of time to get random name
            for(int j = 0;j<ranNumToLoop;j++){
                name = s.next();
            }
            Person p = new Person(name);
            p.setArrivalTime(currentTime);
//            riderHolders[i] = p;
            Ride temp = new Ride();
            int numRide = 0;
            int index = getShortestLine();//find the Ride with shortest line

            temp = totalRide[index];
            //enter queue to ride with shortest line\
            //enter regular line or fast line
            if (p.getPass() == 'R') {
                temp.regPass.enqueue(p);

            } else {
                temp.fastPass.enqueue(p);
//               
            }
        }

    }

    public void processRide() {

        for (int i = 0; i < totalRide.length; i++) {
           
            for (int j = 0; j < totalRide[i].getCapacity(); j++) {
                if (totalRide[i].getNextRide()==0||totalRide[i].getNextRide()<=currentTime) {
                    if (totalRide[i].fastPass.getCount() > 0) {
                        totalRide[i].riding[j] = totalRide[i].fastPass.dequeue();
                        int timeWaitTing = (currentTime-totalRide[i].riding[j].getArrivalTime());
                        if(this.maxWaitTime<timeWaitTing)
                            this.maxWaitTime = timeWaitTing;
                        totalTimeWaiting +=timeWaitTing;
                        totalTimeSpent+=totalRide[i].getRideTime();
                        happyRider++;
                    }
                    else if (totalRide[i].regPass.getCount() > 0) {
                        totalRide[i].riding[j] = totalRide[i].regPass.dequeue();
                        happyRider++;
                        int timeWaitTing = (currentTime-totalRide[i].riding[j].getArrivalTime());
                        if(this.maxWaitTime<timeWaitTing)
                            this.maxWaitTime = timeWaitTing;
                        totalTimeWaiting +=timeWaitTing;
                    }
                    else 
                        totalRide[i].riding[j]= null;

                    totalRide[i].setStartRiding(currentTime);
                     totalRide[i].setNextRide();
                }
            }
        }
    }

    public int getShortestLine() {//find Ride with shortest queue to enter
        int minLine = totalRide[0].countPeopleinLine();
        int index = 0;
        for (int j = 0; j < totalRide.length; j++) {
            if (totalRide[j].countPeopleinLine() < minLine) {
                minLine = totalRide[j].countPeopleInLine;
                index = j;
            }
        }
        return index;
    }

    public String toString() {
        String themePark = "";
        for (int i = 0; i < this.totalRide.length; i++) {

            themePark += "Queue to Ride " + i +" " + totalRide[i].getRideTime()+ " mins";
            themePark += "\nReg Line \t\tFast Line"  + "\t";
            themePark += "\n"+totalRide[i].regPass.toString() +"\t\t" +totalRide[i].fastPass.toString() + "\n";
//            themePark+="Happy Riders: "+ this.happyRider;
//            themePark+= ridesInSession();
            themePark += "\n............................\n";

        }
        return themePark;
    }

    public String ridesInSession() {

        String rideInSession = "-------------------\n" + "Rides in Session\n";
        rideInSession += "-------------------\n";
        for (int i = 0; i < totalRide.length; i++) {
            for (int j = 0; j < totalRide[i].getCapacity(); j++) {
                if (totalRide[i].riding[j] != null) {
                    rideInSession += "" + totalRide[i].riding[j].toString() + "on ride " + i+"\n";
                }
            }
        }

        return rideInSession;
    }
    
    public double getAverageTimeWaiting(){
        return totalTimeWaiting/this.happyRider;
    }
    public double getAverageTimeSpent(){
        return totalTimeSpent/this.happyRider;
    }
    
    
}
