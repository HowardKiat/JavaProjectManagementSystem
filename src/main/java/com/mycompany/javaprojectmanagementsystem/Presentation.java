/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojectmanagementsystem;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileReader;

/**
 *
 * @author User
 */
public class Presentation {
    private final String studentID;
    private final String name;
    private final String intake;
    private final String course;
    private final String topic; 
    private final Date appointmentDate; 
    private final String startTime; 
    private final String endTime; 
    private final String reason; 
    private final String supervisorName; 
    private final String status;   
    
    public Presentation(String studentID, String name, String intake, String course,
                        String topic, Date appointmentDate, String startTime, String endTime,
                        String reason, String supervisorName, String status) {
        this.studentID = studentID;
        this.name = name;
        this.intake = intake;
        this.course = course;
        this.topic = topic;
        this.appointmentDate = appointmentDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.supervisorName = supervisorName;
        this.status = status;
    }
    
       // Getter methods for accessing private fields
    public String getStudentID() {
        return studentID;
    }
    
    public String getName() {
        return name;
    }
    
    public String getIntake() {
        return intake;
    }
    
    public String getCourse() {
        return course;
    }
    
    public String getTopic() {
        return topic;
    }
    
    public Date getAppointmentDate() {
        return appointmentDate;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public String getReason() {
        return reason;
    }
    
    public String getSupervisorName() {
        return supervisorName;
    }
    
    public String getStatus() {
        return status;
    }
    
    @Override
    public String toString() {
        return "Presentation{" +
                "studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", intake='" + intake + '\'' +
                ", course='" + course + '\'' +
                ", topic='" + topic + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", reason='" + reason + '\'' +
                ", supervisorName='" + supervisorName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
    
    public static ArrayList<Presentation> readFromFile(String filePath) {
        //use presentaation_request text file later on
        ArrayList<Presentation> presentations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Presentation presentation = createPresentationFromData(data);
                presentations.add(presentation);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return presentations;
    }
    private static Presentation createPresentationFromData(String[] data) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date appointmentDate = dateFormat.parse(data[5]); // Assuming date format is yyyy-MM-dd
        return new Presentation(data[0], data[1], data[2], data[3], data[4], appointmentDate,
                data[6], data[7], data[8], data[9], data[10]);
    }
    
    public static void main(String[] args) {
        String filePath = "dummyReqP.txt"; 
        ArrayList<Presentation> presentations = Presentation.readFromFile(filePath);

        // Now you can work with the presentations obtained from the file
        for (Presentation presentation : presentations) {
            System.out.println(presentation);
        }
    }
}
