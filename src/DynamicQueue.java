/*
 * dynamic queue 
 */

/**
 *
 * @author leahluong
 */
public class DynamicQueue {

    private Person head;
    private Person rear;
    private int count;
    private int heightRestriction;//height restriction in inches

    public DynamicQueue() {
        this.head = null;
        this.rear = null;
        this.count = 0;
        this.heightRestriction = 0;
    }

    public DynamicQueue(int height) {
        this.head = null;
        this.rear = null;
        this.count = 0;
        this.heightRestriction = height;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public Person getLastPerson() {
        Person temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        return temp;
    }

    public boolean enqueue(Person person) {//enqueue a person at the end of the line

        if (head == null) {
            head = person;
            rear = person;
            count++;
            return true;
        } else {
            rear.setNext(person);
            rear = person;
            count++;
            return true;
        }

    }

    public Person dequeue() { //return the head person and set head to head.next
        Person dequeuePerson = null;
        if (head != null) {
            dequeuePerson = head;
            head = head.getNext();
            count--;
        }
        return dequeuePerson;
    }

    public int getCount() {
        return this.count;
    }

    public String toString() {
        String queue = "";
        if (head != null) {
            Person temp = head;
            queue += (temp.toString());
            while (temp.getNext() != null) {

                temp = temp.getNext();
                queue += (temp.toString()) + "\t";
            }

        }
        return queue;
    }
}
