package student;

/**
 * A class representing a submission by a student.
 */
public class Submission {
    private String date;
    private String fileName;
    private String studentName;

    public Submission(String date, String fileName, String studentName) {
        this.date = date;
        this.fileName = fileName;
        this.studentName = studentName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
        return date + " - " + fileName + " - " + studentName;
    }
}
