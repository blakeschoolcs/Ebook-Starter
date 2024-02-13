package com.example.ebook_starter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class EReaderApplication extends Application {
    private static ReaderController readerController;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EReaderApplication.class.getResource("reader-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 525, 800);
        readerController = fxmlLoader.getController();

        // Load the text into the controller
        String textToLoad = "/tester.txt"; // Adjust this to load different text
        readerController.loadBookText(textToLoad);

        stage.setTitle("EReader");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static ReaderController getReaderController() {
        return readerController;
    }
}
