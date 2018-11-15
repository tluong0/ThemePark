
import java.util.Random;

/*
 * 
 */

/**
 *
 * @author leahluong
 */
public final class Ride {
    private int rideTime; // how long the ride take
    private int capacity;//how many people can the ride take
    public DynamicQueue regPass,fastPass;
    public Person[] riding;//array holding people riding
    public int countPeopleInLine;
    public int startRiding=0;//time the ride start
    public int nextRide=0;
    
    
    public Ride(){
        //initialize both queue
        this.regPass = new DynamicQueue();
        this.fastPass = new DynamicQueue();
        this.capacity = setCapacity(); 
        this.rideTime = setRideTime(); 
        this.riding = new Person[capacity];
        this.countPeopleInLine = countPeopleinLine();
        this.startRiding = 0;
    }
    public void setStartRiding(int time){
        this.startRiding = time;
    }
    public void setNextRide(){
        this.nextRide=this.startRiding+this.rideTime;
    }
    public int setCapacity(){
        Random r = new Random();
        int ranNum = r.nextInt(5)+1; //random capacity 1-5
        return ranNum;
    }
    public int getNextRide(){
        return this.nextRide;
    }
    public int getCapacity(){
        return this.capacity;
    }
    public int setRideTime(){
        Random r = new Random();
        int ranNum = r.nextInt(5)+1; //random time 1min-5mins
        return ranNum;
    }
    public int getRideTime(){
        return this.rideTime;
    }
    public int countPeopleinLine(){
        return this.regPass.getCount()+this.fastPass.getCount();
    }
    public String toString(){
        return "Ride";
    }
    
    
}
