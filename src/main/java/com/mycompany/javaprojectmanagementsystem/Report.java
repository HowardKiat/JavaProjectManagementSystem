/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojectmanagementsystem;

/**
 *
 * @author User
 */
public class Report {

    private String reportID;
    private String projectName;
    private String fileName;
    private String studentID;
    private String studentName;
    private String intake;
    private String submissionDate;
    private String submissionTime; 
    private double marks;
    private String grades;
    private String feedback;
    private String markingStatus;
    
    
    public Report(String reportID, String projectName, String fileName, String studentID, String studentName, 
                  String intake, String submissionDate, String submissionTime, double marks, String grades, 
                  String feedback, String markingStatus) {
        this.reportID = reportID;
        this.projectName = projectName;
        this.fileName = fileName;
        this.studentID = studentID;
        this.studentName = studentName;
        this.intake = intake;
        this.submissionDate = submissionDate;
        this.submissionTime = submissionTime;
        this.marks = marks;
        this.grades = grades;
        this.feedback = feedback;
        this.markingStatus = markingStatus;
        
    }
    
    public String getReportID() {
        return reportID;
    }

    public void setReportID(String reportID) {
        this.reportID = reportID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getIntake() {
        return intake;
    }

    public void setIntake(String intake) {
        this.intake = intake;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(String submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(String submissionTime) {
        this.submissionTime = submissionTime;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getMarkingStatus() {
        return markingStatus;
    }

    public void setMarkingStatus(String markingStatus) {
        this.markingStatus = markingStatus;
    }
}
