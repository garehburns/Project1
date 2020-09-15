package project1;

import java.time.LocalTime;

public class TraditionalClass extends Course {

    public LocalTime startingTime;
    public LocalTime stoppingTime;
    public String scheduledMeetingDays;
    public String scheduledMeetingRoom;

    
    public TraditionalClass() {}
    
    //The below method may or may not be necessary, depends on what you believe you need for the UI.
    public String toString() {
        //return "Traditional Class: " + CRN; // Put whatever properties you want to pull here in the same format as the example I gave.
        
        return ("#" + CRN + ": " + courseNumber + "-" + sectionNumber + " (" + courseName + "), " + instructor + ", " + courseType + ", " 
                + startingTime + " - " + stoppingTime + ", " + scheduledMeetingDays + ", " + scheduledMeetingRoom + "\n");
    }
    
}