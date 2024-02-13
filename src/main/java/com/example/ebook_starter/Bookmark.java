package com.example.ebook_starter;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter;


public class Bookmark {

    // Instance Variables
    private int pageNumber;
    private String note;
    private ArrayList<String> keywords = new ArrayList<>();
    private LocalDateTime creationTime; // Variable to store the creation timestamp

    // Constructor
    Bookmark(){
        // TODO: Create a constructor that takes arguments
        this.pageNumber = 0;
        this.note = "";
        this.keywords = new ArrayList<String>();
        this.creationTime = LocalDateTime.now(); // Set the creation timestamp to the current date and time
    }

    public LocalDateTime getCreationTime() {

        return creationTime; // Getter for the creation timestamp
    }
    public String getFormattedCreationTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss");
        return creationTime.format(formatter);
    }
}
