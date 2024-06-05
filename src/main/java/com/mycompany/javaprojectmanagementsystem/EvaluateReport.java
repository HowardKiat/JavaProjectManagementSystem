/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojectmanagementsystem;

import java.util.List;

/**
 *
 * @author User
 */
public class EvaluateReport {
    public static String evaluateReport(Report report, List<Feedback> feedbackList) {
        int totalRating = 0;
        StringBuilder evaluationSummary = new StringBuilder();

        evaluationSummary.append("Report ID: ").append(report.getReportID()).append("\n");
        evaluationSummary.append("Project Name: ").append(report.getProjectName()).append("\n");
        evaluationSummary.append("Student Name: ").append(report.getStudentName()).append("\n\n");

        for (Feedback feedback : feedbackList) {
            totalRating += feedback.getRating();
            evaluationSummary.append("Reviewer: ").append(feedback.getReviewer())
                    .append("\nRating: ").append(feedback.getRating())
                    .append("\nComments: ").append(feedback.getComments()).append("\n\n");
        }

        double averageRating = feedbackList.isEmpty() ? 0 : (double) totalRating / feedbackList.size();
        evaluationSummary.append("Average Rating: ").append(averageRating).append("\n");

        String overallEvaluation;
        if (averageRating >= 8) {
            overallEvaluation = "Excellent";
        } else if (averageRating >= 5) {
            overallEvaluation = "Good";
        } else {
            overallEvaluation = "Needs Improvement";
        }

        evaluationSummary.append("Overall Evaluation: ").append(overallEvaluation);
        return evaluationSummary.toString();
    }  
}
