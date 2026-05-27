
/**
 * Holds the teacher and student queues.
 *
 * @Se Behan
 * @22/05/2026
 */
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
public class Cafeteria
{
    private Queue teacherQueue = new Queue();
    private Queue studentQueue = new Queue();
    public Cafeteria(){
        getConfig();
    }
    
    public Person cafeDequeue(){
        if(teacherQueue.queueEmpty() == true){
            return studentQueue.dequeue();
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
                if(person.getTeacher() == true){
                    teacherQueue.enqueue(person);
                }else{
                    studentQueue.enqueue(person);
                }
            }
        }catch(IOException e){
            System.out.println("There was a file reading error.");
            e.printStackTrace();
        }
    }
}