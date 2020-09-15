package project1;

public class OnlineClass extends Course {
    
    public OnlineClass() {}
    
    public String toString() {
        return ("#" + CRN + ": " + courseNumber + "-" + sectionNumber + " (" + courseName + "), " + instructor + ", " + courseType + "\n");
    }
    
}