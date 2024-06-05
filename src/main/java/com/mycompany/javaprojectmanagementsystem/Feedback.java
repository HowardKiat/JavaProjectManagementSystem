/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojectmanagementsystem;

/**
 *
 * @author User
 */
public class Feedback {
    private String reviewer;
    private int rating;
    private String comments;
    
    public Feedback(String reviewer, int rating, String comments) {
        this.reviewer = reviewer;
        this.rating = rating;
        this.comments = comments;
    
    }
    
    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
