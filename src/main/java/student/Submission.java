/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student;

/**
 *
 * @author User
 */
public class Submission {
//    see if want to have moodle Link
    private String date;
    private String assessmentType;
    private String moodleLink;
    private final String fileName;
    
    public Submission(String date, String assessmentType, String moodleLink, String fileName) {
        this.date = date;
        this.assessmentType = assessmentType;
        this.moodleLink = moodleLink;
        this.fileName = fileName;
    }
    
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getMoodleLink() {
        return moodleLink;
    }

    public void setMoodleLink(String moodleLink) {
        this.moodleLink = moodleLink;
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return fileName + date;
        
//        record student name too
    }
}
