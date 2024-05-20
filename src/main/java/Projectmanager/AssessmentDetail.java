/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projectmanager;

/**
 *
 * @author Darren
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AssessmentDetail {
    private String projectName;
    private String projectType;
    private String supervisor;
    private String secondMarker;

    public AssessmentDetail(String projectName, String projectType, String supervisor, String secondMarker) {
        this.projectName = projectName;
        this.projectType = projectType;
        this.supervisor = supervisor;
        this.secondMarker = secondMarker;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public String getSecondMarker() {
        return secondMarker;
    }

    @Override
    public String toString() {
        return projectName + ", " + projectType + ", " + supervisor + ", " + secondMarker;
    }

    public static List<AssessmentDetail> loadAssessmentDetailsFromFile(String filePath) {
        List<AssessmentDetail> assessments = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 4) {
                    AssessmentDetail assessment = new AssessmentDetail(data[0], data[1], data[2], data[3]);
                    assessments.add(assessment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return assessments;
    }
}
