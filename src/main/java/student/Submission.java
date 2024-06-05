package student;

import java.io.File;
import java.util.Date;

/**
 * A class representing a submission by a student.
 */
public class Submission {
    private String date;
    private String fileName;
    private String studentName;
    private String time;
    private Date submissionDate;
    private File submittedFile;

    public Submission(String date, String time, String fileName, String studentName, Date submissionDate, File submittedFile) {
        this.date = date;
        this.time = time;
        this.fileName = fileName;
        this.studentName = studentName;
        this.submissionDate = submissionDate;
        this.submittedFile = submittedFile;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return date + " " + time + " - " + fileName + " - " + studentName;
    }
}
