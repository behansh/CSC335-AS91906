
/**
 * Contains and manages the queue of students and teachers.
 *
 * @Se Behan
 * @15/05/2026
 */
public class Queue
{
    private Person front;
    private Person back;
    
    public Queue(){
        
    }
    
    public boolean queueEmpty(){
        boolean isEmpty = false;
        if(front == null && back == null){
            isEmpty = true;
        }
        return isEmpty;
    }
    
    public void enqueue(Person person){
        boolean isEmpty = queueEmpty();
        if(isEmpty == true){
            front = person;
            back = person;
        }else{
            back.addBehind(person);
            back = person;
        }
    }
    
    public Person dequeue(){
        boolean isEmpty = queueEmpty();
        Person person = new Person();
        if(isEmpty == true){
            return null;
        }else if(front == back){
            person = front;
            front = null;
            back = null;
        }else{
            person = front;
            front = front.getBehind();
        }
        return person;
    }
}