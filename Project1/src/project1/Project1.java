package project1;

import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.time.LocalTime;

public class Project1 {

    public static void main(String[] args) {
        // These hashmaps will contain different setups of the course offerings.
        // byCRN is used for registering, the key is the CRN an the value is the course information.
        //byCourseNumber is used for searching for classes (option 1 in the UI). The key is the courseNumber and the value is the course information.
        //registered contains whatever courses are added using option 2.The key is the CRN and the value is the course information.
        HashMap<String, Course> byCRN = new HashMap<String, Course>();
        HashMap<String, ArrayList<Course>> byCourseNumber = new HashMap<String, ArrayList<Course>>();
        HashMap<String, Course> registered = new HashMap<String, Course>();
        try {
            Scanner in = new Scanner(Paths.get("project1input.csv"), "UTF-8");
            //The delimeter below was used because even though the file is tab delimeted. 
            //The end lines weren't picked up so the last piece of info and the first piece of info on the line below became one entry. 
            //The way that I've delimeted it should fix that so that each course has become its own 'entry' per say.
            in.useDelimiter("\r\n");
            
            
            while (in.hasNext()) {                
                String nextLine = in.next();
                //The line is then split based on tabs so that we can pull each piece of information seperately.
                //Since online courses and traditional courses have different amounts of information available, 
                //I've had to make sure no 'out of bounds' exceptions are being thrown.
                //I did this by checking the length of the entry (Traditional Courses have a length greater than 10)
                String[] line = nextLine.split("\t");
                //If you want to change the location of this logic, go ahead,.
                //I just wanted to provide all of the logic so that you can rewrite if necessary in your own style.
                //All that the below code is doing is appending the information to the correct properties on the Course object
                
                
                if (line.length < 10) {
                    OnlineClass course = new OnlineClass();                    
                    for (int i = 0; i < line.length; i++) {
                        switch(i) {
                            case 0:
                                course.courseName = line[i];
                                //System.out.println(course.courseNumber);
                                break;
                            case 1:
                                course.CRN = line[i];
                                break;
                            case 2:
                                course.courseNumber = line[i];
                                break;
                            case 3: 
                                int num = Integer.parseInt(line[i]);
                                course.sectionNumber = String.format("%03d", num);
                                break;
                            case 4:
                                course.creditHours = Double.parseDouble(line[i]);
                                break;
                            case 5:
                                course.courseType = line[i];
                                break;
                            case 6:
                                course.instructor = line[i];
                                break;
                        }
                    }
                    byCRN.put(course.CRN, course);
                } else {
                    TraditionalClass course = new TraditionalClass();
                    for (int i = 0; i < line.length; i++) {
                        switch(i) {
                            case 0:
                                course.courseName = line[i];
                                break;
                            case 1:
                                course.CRN = line[i];
                                break;
                            case 2:
                                course.courseNumber = line[i];
                                break;
                            case 3: 
                                int num = Integer.parseInt(line[i]);
                                course.sectionNumber = String.format("%03d", num);
                                break;
                            case 4:
                                course.creditHours = Double.parseDouble(line[i]);
                                break;
                            case 5:
                                course.startingTime = LocalTime.parse(line[i]);
                                break;
                            case 6:
                                course.stoppingTime = LocalTime.parse(line[i]);
                                break;
                            case 7:
                                course.scheduledMeetingDays = line[i];
                                break;
                            case 8:
                                course.scheduledMeetingRoom = line[i];
                                break;
                            case 9:
                                course.courseType = line[i];
                                break;
                            case 10:
                                course.instructor = line[i];
                                break;
                        }
                    }
                    byCRN.put(course.CRN, course);
                }
            }
            in.close();
            //This ArrayList is used to store the Course information for any course that has the same courseNumber property
            //The key is the courseNumber and the value is an ArrayList of any course with that courseNumber
            ArrayList<Course> courseList = new ArrayList<Course>();            
            
            //This is the process that I used to add the courses with the same courseNumber to the ArrayList
            for(String c: byCRN.keySet()) {
                Course cur = byCRN.get(c);
                
                if(!byCourseNumber.containsKey(cur.courseNumber)) {
                    byCourseNumber.put(cur.courseNumber, new ArrayList<Course>());
                }
                //The below line adds the ArrayList to the byCourseNumber HashMap
                byCourseNumber.get(cur.courseNumber).add(cur);
            }

            
            //System.out.println(byCourseNumber);
            
            //All the logic below is just my rough UI so that I could do bare minimum testing of the logic above.
            Scanner user = new Scanner(System.in);
            boolean done = false;
            while(!done) {
                System.out.print("1) Search Courses\n2) Register For Course\n3) View Trial Schedule\n4) Quit\n\nYour Choice? ");
                int choice = user.nextInt();
                user.nextLine();
                
                if(choice == 1) {
                    System.out.print("Enter the course number in the format SS NNN (for example, CS 201): ");
                    String crn = user.nextLine();
                    ArrayList<Course> chosen = byCourseNumber.get(crn);
                    System.out.println(chosen);
                }
                else if(choice == 2) {
                    System.out.print("Please enter CRN: ");
                    String crn = user.nextLine();
                    registered.put(crn, byCRN.get(crn));
                    System.out.println("Course added successfully!\n");
                }
                else if(choice == 3) {
                    System.out.println(registered);
                }
                else if(choice == 4) {
                    done = true;
                }
            }
        }
        
        catch (Exception e) {
            
            System.err.println(e.toString());
            
        }
    }
}