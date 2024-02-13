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

    @Test
    public void testBookDetails() {
        // Directly comparing string values
        assertEquals("Grimms’ Fairy Tales", controller.getBookTitleText());
        assertEquals("By Jacob Grimm and Wilhelm Grimm", controller.getBookAuthorText());

        // Verifying the numeric value for the page number
        assertEquals(3, controller.getPageNumberValue());
    }

}
