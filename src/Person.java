
import java.util.Random;

/*
 * person class 
 */

/**
 *
 * @author leahluong
 */
public class Person {
    private String name;
    //keep track of time arrival, time wait and time take to complete the ride
    private int arrivalTime, waitTime,competeTime; 
    private char pass;//randomly assign fast pass or regular pass
    private Person next;

    public Person(String name) {
        this.name = name;
        this.pass = setPass();
        this.next = null;
        this.arrivalTime = this.waitTime = this.competeTime=0;
    }

    
  public char setPass(){
      char pass;
      Random r = new Random();
      int num = r.nextInt(10);
      if(num<7)
          pass='F';//70% of getting fast pass
      else
          pass = 'R'; //regular pass line
      return pass;
  }
    public char getPass(){
        return this.pass;
    }
    public Person getNext(){
        return this.next;
    }
//    public int getHeight(){
//        return this.height;
//    }
//    
    public void setNext(Person p){
        this.next=p;
    }
    public String getName(){
        return this.name;
    }
    public void setArrivalTime(int time){
        this.arrivalTime = time;
    }
    public int getArrivalTime(){
        return this.arrivalTime;
    }
    
    
    @Override
    public String toString(){
        return "" +this.name +"("+ this.arrivalTime+ " - "+ this.pass +")";
    }
    
    
}
