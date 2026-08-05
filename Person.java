
/**
 * Contains information about a specfic person in the queue.
 *
 * @Se Behan
 * @05/06/2026
 */
public class Person
{
    private String name = "";
    private boolean teacher = false;
    private int arrivalTime = 0;
    private int servedTime = 0;
    private int waitTime = 0;
    private Person behind;
    
    public Person(){
        
    }
    
    public Person(String name, boolean teacher, int arrivalTime){
        this.name = name;
        this.teacher = teacher;
        this.arrivalTime = arrivalTime;
    }
    
    public void setServedTime(int servedTime){
        this.servedTime = servedTime;
    }
    
    public void setWaitTime(int waitTime){
        this.waitTime = waitTime;
    }
    
    public int getWaitTime(){
        return this.waitTime;
    }
    
    public String getName(){
        return this.name;
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