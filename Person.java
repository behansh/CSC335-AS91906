
/**
 * Contains information about a specfic person in the queue.
 *
 * @Se Behan
 * @22/05/2026
 */
public class Person
{
    private String name = "";
    private boolean teacher = false;
    private int arrivalTime;
    private Person behind;
    
    public Person(){
        
    }
    
    public Person(String name, boolean teacher, int arrivalTime){
        this.name = name;
        this.teacher = teacher;
        this.arrivalTime = arrivalTime;
    }
    
    public void setBehind(Person person){
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