/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojectmanagementsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author User
 */
public class Presentation {
    public final String studentID;
    private final String name;
    private final String email;
    private final String intake;
    private final String topic; 
    private final Date date; 
    private final String startTime; 
    private final String endTime; 
    private final String reason; 
    private final String supervisor; 
    private String status;    
    

    public Presentation(String studentID, String name, String email, String intake,
                        String topic, Date date, String startTime, String endTime,
                        String reason, String supervisor, String status) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
        this.intake = intake;
        this.topic = topic;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reason = reason;
        this.supervisor = supervisor;
        this.status = status;
    }
    
       // Getter methods for accessing private fields
    public String getStudentID() {
        return studentID;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getIntake() {
        return intake;
    }
    
    public String getTopic() {
        return topic;
    }
    
    public Date getDate() {
        return date;
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
    
    public String getSupervisor() {
        return supervisor;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return String.join(",", studentID, name, email, intake, topic, df.format(date), startTime, endTime, reason, supervisor, status);
    }
    
    public static ArrayList<Presentation> readFromFile(String filePath) {
        ArrayList<Presentation> presentations = new ArrayList<>();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Date date = dateFormatter.parse(data[5]); // Parse the date using the correct format
                presentations.add(new Presentation(data[0], data[1], data[2], data[3], data[4], date, data[6], data[7], data[8], data[9], data[10]));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return presentations;
    }
    
    public static void writeToFile(ArrayList<Presentation> presentations, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Presentation presentation : presentations) {
                // Convert the Presentation object to a line of text
                // Assume a method to convert to a string exists
                bw.write(presentation.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
    public static void main(String[] args) {
        String filePath = "presentation_request.txt"; 
        ArrayList<Presentation> presentations = Presentation.readFromFile(filePath);

        for (Presentation presentation : presentations) {
            System.out.println(presentation);
        }
    }
}
