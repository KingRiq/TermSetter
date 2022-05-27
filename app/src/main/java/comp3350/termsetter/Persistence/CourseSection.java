package comp3350.termsetter.Persistence;

import java.io.Serializable;

public class CourseSection implements Serializable
{
    private final String section;
    private final String days;
    private final String timeSlot;
    private final String period;
    private int occupants;
    private int maxOccupancy;

    public CourseSection(String section, String days, String timeSlot, String period)
    {
        this.section = section;
        this.days = days;
        this.timeSlot = timeSlot;
        this.period = period;
        this.occupants = 0;
    }

    public String getSection() {
        return section;
    }

    public String getDays() { return days; }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getPeriod() {
        return period;
    }

    public int getOccupancy(){ return occupants; }

    public void setMaxOccupancy(int max) {
        maxOccupancy = max;
    }

    public void enroll()
    {
        if (courseAvailable())
            occupants++;
    }

    public boolean courseAvailable() {
        return occupants < maxOccupancy;
    }

    public boolean equals(CourseSection otherSection){
        return section.equals(otherSection.getSection()) && days.equals(otherSection.getDays()) && timeSlot.equals(otherSection.getTimeSlot()) && period.equals(otherSection.getPeriod());
    }

}

