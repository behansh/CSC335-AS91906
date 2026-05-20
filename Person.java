
/**
 * Contains information about a specfic person in the queue.
 *
 * @Se Behan
 * @15/05/2026
 */
public class Person
{
    private boolean teacher = false;
    private int arrivalTime;
    private Person behind;
    
    public Person(){
        
    }
    
    public Person(boolean teacher, int arrivalTime){
        this.teacher = teacher;
        this.arrivalTime = arrivalTime;
    }
    
    public void addBehind(Person person){
        this.behind = person;
    }
    
    public Person getBehind(){
        return this.behind;
    }
    
    public boolean getTeacher(){
        return this.teacher;
    }
    
    public int getArrivalTime(){
        return this.arrivalTime;
    }
}