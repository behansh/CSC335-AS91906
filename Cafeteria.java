
/**
 * Holds the teacher and student queues.
 *
 * @Se Behan
 * @05/06/2026
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
public class Cafeteria
{
    private Queue teacherQueue = new Queue();
    private Queue normalQueue = new Queue();
    public ArrayList<Person> line = new ArrayList<Person>();
    public int timePassed = 0;
    public int lastTime = 0;
    public Cafeteria(){
        getConfig();
        boolean teacherSkip = true;
        cafeEnqueue(teacherSkip);
        Person person = cafeDequeue();
        lastTime = person.getArrivalTime();
        timePassed += (person.getArrivalTime() - lastTime);
    }

    public void cafeEnqueue(boolean teacherSkip){
        if(teacherSkip == true){
            for(int i = 0;i < line.size();i++){
                if(line.get(i).getTeacher() == true){
                    teacherQueue.enqueue(line.get(i));
                }else{
                    normalQueue.enqueue(line.get(i));
                }
            }
        }else{
            for(int i = 0;i < line.size();i++){
                normalQueue.enqueue(line.get(i));
            }
        }
    }

    public Person cafeDequeue(){
        if(teacherQueue.queueEmpty() == true){
            return normalQueue.dequeue();
        }else{
            return teacherQueue.dequeue();
        }
    }

    public void getConfig(){
        String fileName = "cfg.txt";
        //System.out.println("What is the name of the config file? Leave blank to use default.");
        //add code to change file name.
        File configFile = new File(fileName);
        try{
            Scanner reader = new Scanner(configFile);
            while(reader.hasNextLine()){
                String infoLine = reader.nextLine();
                String[] info = infoLine.split(";");
                String name = info[0];
                boolean teacher = false;
                if(Integer.parseInt(info[1]) == 1){
                    teacher = true;
                }
                int arrivalTime = Integer.parseInt(info[2]);
                Person person = new Person(name, teacher, arrivalTime);
                line.add(person);
            }
        }catch(IOException e){
            System.out.println("There was a file reading error.");
            e.printStackTrace();
        }
        line.sort(Comparator.comparing(Person::getArrivalTime));//Sorts by arrival time
    }
}