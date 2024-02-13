package com.example.ebook_starter;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReaderControllerTest {

    private static ReaderController controller;

    @BeforeAll
    public static void setUpClass() throws Exception {
        // Launch the application in a new thread
        new Thread(() -> Application.launch(EReaderApplication.class)).start();

        // Wait for the application to start
        Thread.sleep(10000); // Adjust the sleep time as necessary

        // Obtain the controller instance from the running application
        controller = EReaderApplication.getReaderController();
    }


    // --------- This assume that EReader is being loaded with "tester.txt" ---------//
    @Test
    public void testBookDetails() {

    }

}
