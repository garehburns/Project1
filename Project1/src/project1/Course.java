package project1;

public abstract class Course {
    public String courseName;
    public String CRN;
    public String courseNumber;
    public String sectionNumber;
    public double creditHours;
    public String courseType;
    public String instructor;
    
    public String getNum(){
        return courseNumber;
    }

}