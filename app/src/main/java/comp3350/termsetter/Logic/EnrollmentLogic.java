package comp3350.termsetter.Logic;

import java.util.ArrayList;
import java.util.List;
import comp3350.termsetter.Persistence.CourseOffering;
import comp3350.termsetter.Persistence.CourseSection;
import comp3350.termsetter.Persistence.DomainSpecific.hsqldbObjects.EnrollAccess;
import comp3350.termsetter.Persistence.Main;
import comp3350.termsetter.Persistence.StudentPersistence;


public class EnrollmentLogic
{
    private String courseCode;
    private CourseSection selectedClass;
    private String message;
    private EnrollAccess enrollAccess;
    private AccessManager accessManager;
    private StudentPersistence database;
    private String studentID;

    //EnrollmentLogic Constructor
    public EnrollmentLogic(String studentID, String courseCode, CourseSection selectedClass)
    {
        this.courseCode = courseCode;
        this.selectedClass = selectedClass;
        this.studentID = studentID;
        message = "";
        String path = Main.getDBPathName();
        enrollAccess = new EnrollAccess(path);

        loadClasses();
    }

    public void loadClasses()
    {
        List<String> results = enrollAccess.getStudentEnrollment(studentID);

        boolean success = true;
        // adding enrollment results to currClasses
        for(int i = 0; i < results.size() && success; i++)
        {
            String result = results.get(i);
            String[] tokens = result.split("@");
            CourseOffering currCourse = new CourseOffering(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
            success = addCourse(currCourse);
            if(success)
            {
                CourseSection currClass = new CourseSection(tokens[3], tokens[4], tokens[5], tokens[6]);
                success = addSection(currClass);
                if (!success)
                {
                    message = "Error: Failed to enroll due to a time conflict with " + tokens[0] + " (" + tokens[4] + ": "+ tokens[5] + ")";
                }
            }
            else {
                message = "Error: Already enrolled in this course!";
            }
        }
        if(success) {
            if(results.size() < 6){
                confirmEnroll();
                message = "Successfully enrolled in " + courseCode + " " + selectedClass.getSection() + ".";
            }
            else{
                message = "You cannot enroll in more than six courses. You must drop a class to add a new one.";
            }
        }
    }

    public boolean addSection(CourseSection courseSection)
    {
        boolean added = false;
        String days = courseSection.getDays();
        String timeSlot = courseSection.getTimeSlot();

        if (!checkConflict(days, timeSlot))
            added = true;

        return added;
    }

    public boolean addCourse(CourseOffering courseOffering)
    {
        boolean added = false;
        String cCode = courseOffering.getCourseCode();

        if (!checkCodeDup(cCode))
            added = true;

        return added;
    }

    //Conflict check method
    public boolean checkConflict(String days, String timeSlot)
    {
        boolean conflict = false;

        String selectedClassDays = selectedClass.getDays();

        //get and parse timesSlot from selectedClass
        String timeS = selectedClass.getTimeSlot();
        String[] tSlot1 = timeS.split("-");
        int startS = parseTime(tSlot1[0]);
        int endS = parseTime(tSlot1[1]);

        //parse timeSlot
        String[] tSlot2 = timeSlot.split("-");
        int start = parseTime(tSlot2[0]);
        int end = parseTime(tSlot2[1]);

        if (daysOverlap(selectedClassDays, days) && end > startS && end < endS)
            conflict = true;

        else if (daysOverlap(selectedClassDays, days) && start > startS && start < endS)
            conflict = true;

        else if (daysOverlap(selectedClassDays, days) && start == startS && end == endS)
            conflict = true;

        return conflict;
    }

    //function to check code duplicates
     public boolean checkCodeDup (String cCode)
     {
         boolean conflict = false;

         if (courseCode.equals(cCode))
             conflict = true;

         return conflict;
     }

     //function to check day overlap
    public static boolean daysOverlap(String daySet1, String daySet2)
    {
        boolean conflict = false;

        if (daySet1.contains(daySet2))
            conflict = true;
        else if (daySet2.contains(daySet1))
            conflict = true;
        else if (daySet1.equals(daySet2))
            conflict = true;

        return conflict;
    }

    //function to parse time
    public static int parseTime(String time)
    {
        String[] timeParts = time.split(":");
        int hour = Integer.parseInt(timeParts[0].trim());
        int mins = Integer.parseInt(timeParts[1].trim());

        return calculateMinutes(hour,mins);
    }

    //helper for changing time into minutes
    public static int calculateMinutes(int hour, int minute) { return hour*60+minute; }

    public String getMessage(){ return message; }

    private void confirmEnroll()
    {
        enrollAccess.enroll(studentID, courseCode, selectedClass.getSection());
    }


}