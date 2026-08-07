
/**
 * Holds the teacher and student queues and runs the whole program.
 *
 * @Se Behan
 * @07/08/2026
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
    public int totalQueueSize = 0;
    public ArrayList<Person> line = new ArrayList<Person>();
    public ArrayList<Person> allPeople = new ArrayList<Person>();
    public int currentTime = 0;
    public int[] studentArrivals = new int[60];
    public int[] teacherArrivals = new int[60];
    public int[] numberServed = new int[60];
    public Cafeteria(){
        getConfig();
        cafeEnqueue();
        boolean teacherSkip = true;
        cafeRun(teacherSkip);

        teacherQueue = new Queue();
        normalQueue = new Queue();
        allPeople.clear();
        currentTime = 0;
        cafeEnqueue();
        teacherSkip = false;
        cafeRun(teacherSkip);

    }

    public void cafeRun(boolean teacherSkip){
        if(teacherSkip == true){
            System.out.println("These are the statistics for if the teachers skip the queue.");
        }else{
            System.out.println("These are the statistics for if the teachers stay in the queue with the students.");
        }
        currentTime = 0;
        allPeople.clear();
        numberServed = new int[60];
        int peopleServed = 0;
        int servingTime = 30;
        while(peopleServed < totalQueueSize){
            Person person = cafeDequeue(teacherSkip);
            if(currentTime < person.getArrivalTime()){
                currentTime = person.getArrivalTime();
            }
            person.setServedTime(currentTime);
            person.setWaitTime(currentTime - person.getArrivalTime());
            int minute = currentTime / 60;
            if(minute < numberServed.length){
                numberServed[minute]++;
            }
            allPeople.add(person);
            currentTime += servingTime;
            peopleServed++;
        }
        
        
        int studentTotal = 0;
        int studentCount = 0;
        int teacherTotal = 0;
        int teacherCount = 0;
        for(Person person : allPeople){
            if(person.getTeacher() == true){
                teacherTotal += person.getWaitTime();
                teacherCount++;
            }else{
                studentTotal += person.getWaitTime();
                studentCount++;
            }
        }
        double avgStudentTimeSeconds = (studentTotal / studentCount);
        double avgTeacherTimeSeconds = (teacherTotal / teacherCount);
        
        double avgStudentTimeMinutes = avgStudentTimeSeconds / 60;
        double avgTeacherTimeMinutes = avgTeacherTimeSeconds / 60;
        if(avgStudentTimeSeconds >= 60){
            System.out.println("The average student wait time is " + Math.round(avgStudentTimeMinutes * 100) / 100 + " minutes.");
        }else{
            System.out.println("The average student wait time is " + Math.round(avgStudentTimeSeconds * 100) / 100 + " seconds.");
        }
        
        if(avgTeacherTimeSeconds >= 60){
            System.out.println("The average teacher wait time is " + Math.round(avgTeacherTimeMinutes * 100) / 100 + " minutes.");
        }else{
            System.out.println("The average teacher wait time is " + Math.round(avgTeacherTimeSeconds * 100) / 100 + " seconds.");
        }
        
        
        System.out.println();
        System.out.println("Minute" + "   " + "Students" + "   " + "Teachers" + "   " + "Served");
        for (int i = 0; i < 60; i++) {
            System.out.println(i + "\t" + studentArrivals[i] + "\t\t" + teacherArrivals[i] + "\t\t" + numberServed[i]);
        }
        for(int i = 0;i<10;i++){
            System.out.println();
        }

    }

    public void cafeEnqueue(){
        for(int i = 0;i < line.size();i++){
            if(line.get(i).getTeacher() == true){
                teacherQueue.enqueue(line.get(i));
            }else{
                normalQueue.enqueue(line.get(i));
            }
        }
    }

    public Person cafeDequeue(boolean teacherSkip){
        if(teacherSkip == true){
            if(teacherQueue.queueEmpty() == true){
                return normalQueue.dequeue();
            }else{
                if(teacherQueue.getFront().getArrivalTime() > currentTime){
                    return normalQueue.dequeue();
                }else{
                    return teacherQueue.dequeue();
                }
            }
        }else{
            if(teacherQueue.queueEmpty()){
                return normalQueue.dequeue();
            }
            if(normalQueue.queueEmpty()){
                return teacherQueue.dequeue();
            }
            if(teacherQueue.getFront().getArrivalTime() <= normalQueue.getFront().getArrivalTime()){
                return teacherQueue.dequeue();
            } else {
                return normalQueue.dequeue();
            }
        }
    }

    public void getConfig(){
        String fileName = "cfg.txt";
        //System.out.println("What is the name of the config file? Leave blank to use default.");
        //add code to change file name.
        int errors = 0;
        ArrayList<Person> peopleErrors = new ArrayList<Person>();

        File configFile = new File(fileName);
        try{
            Scanner reader = new Scanner(configFile);
            if(!reader.hasNextLine()){
                System.out.println("There is a blank line in the cfg file. Please remove it so that the file can be read.");
            }else{
                while(reader.hasNextLine()){
                    String infoLine = reader.nextLine();
                    String[] info = infoLine.split(";");
                    String name = info[0];
                    boolean teacher = false;
                    if(Integer.parseInt(info[1]) >= 1){
                        teacher = true;
                    }
                    int arrivalTime = Integer.parseInt(info[2]);
                    if(arrivalTime > 3570){
                        errors++;
                        Person person = new Person(name, teacher, arrivalTime);
                        peopleErrors.add(person);
                    }else if(arrivalTime < 0){
                        errors++;
                        Person person = new Person(name, teacher, arrivalTime);
                        peopleErrors.add(person);
                    }else{
                        int minute = arrivalTime / 60;
                        if(teacher == true){
                            teacherArrivals[minute]++;
                        }else{
                            studentArrivals[minute]++;
                        }

                        Person person = new Person(name, teacher, arrivalTime);
                        line.add(person);
                        totalQueueSize++;
                    }

                }
            }
        }catch(IOException e){
            System.out.println("There was a file reading error.");
            e.printStackTrace();
        }
        line.sort(Comparator.comparing(Person::getArrivalTime));//Sorts by arrival time
        if(errors > 0){
            System.out.println("There were " + errors + " errors in the cfg file.");
            System.out.println("These people were excluded from the simulation:");
            for(Person person : peopleErrors){
                System.out.println(person.getName());
            }
        }
    }
}