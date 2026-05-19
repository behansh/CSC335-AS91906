
/**
 * Contains information about a specfic person in the queue.
 *
 * @Se Behan
 * @15/05/2026
 */
public class Person
{
    private boolean occupation = false;
    private int arrivalTime;
    private Person behind;
    
    public Person(){
        
    }
    
    public Person(boolean occupation, int arrivalTime){
        this.occupation = occupation;
        this.arrivalTime = arrivalTime;
    }
    
    public void addBehind(Person person){
        this.behind = person;
    }
    
    public Person getBehind(){
        return this.behind;
    }
    
    public boolean getOccupation(){
        return this.occupation;
    }
    
    public int getArrivalTime(){
        return this.arrivalTime;
    }
}