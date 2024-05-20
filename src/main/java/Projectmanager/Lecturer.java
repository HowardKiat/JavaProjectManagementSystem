/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projectmanager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darren
 */
public class Lecturer {
    private String email;
    private String password;
    private String name;
    private String id;
    private String position;
    private String additionalRole;

    public Lecturer(String email, String password, String name, String id, String position, String additionalRole) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.id = id;
        this.position = position;
        this.additionalRole = additionalRole;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public String getAdditionalRole() {
        return additionalRole;
    }

    public void setAdditionalRole(String additionalRole) {
        this.additionalRole = additionalRole;
    }

    public static List<Lecturer> loadLecturersFromFile(String filePath) {
        List<Lecturer> lecturers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    Lecturer lecturer = new Lecturer(data[0], data[1], data[2], data[3], data[4], data[5]);
                    lecturers.add(lecturer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log the error, display a message to the user)
            // You can choose to return an empty list or throw an exception here
        }

        return lecturers;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", position='" + position + '\'' +
                ", additionalRole='" + additionalRole + '\'' +
                '}';
    }
}


