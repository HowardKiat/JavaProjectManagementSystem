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
import java.util.*;

public class AssignStudent {

    public static List<String[]> readDataFromFile(String filePath) {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void writeDataToFile(List<String[]> data, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] record : data) {
                bw.write(String.join(", ", record));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String studentAssessmentFile = "Student_Assessment.txt";
        String studentIntake = "3-2024"; // Example intake to assign

        List<String[]> studentAssessmentData = readDataFromFile(studentAssessmentFile);
        List<String[]> assignedStudents = new ArrayList<>();

        for (String[] assessment : studentAssessmentData) {
            String assessmentIntake = assessment[4]; // Assuming intake is at index 4

            if (assessmentIntake.equals(studentIntake)) {
                String[] assignment = {
                    assessment[2], // Student name
                    assessment[3], // TP
                    assessment[0], // Assessment type
                    assessment[1], // Assessment name
                    assessment[5], // Supervisor
                    assessment[6]  // Second marker
                };
                assignedStudents.add(assignment);
            }
        }

        String outputFile = "assignedStudents.txt";
        writeDataToFile(assignedStudents, outputFile);
    }
}
    

