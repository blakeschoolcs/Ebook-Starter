package com.example.ebook_starter;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.stage.Stage;

public class BookmarkModalController {

    @FXML
    private TextField pageNumberField, creationTimeField, noteField, keywordsField;

    private ReaderController mainController;

    public void setMainController(ReaderController mainController) {
        this.mainController = mainController;
    }

    public void preloadData(int pageNumber, LocalDateTime creationTime) {
        pageNumberField.setText(String.valueOf(pageNumber));
        creationTimeField.setText(creationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @FXML
    private void handleAddBookmark() {
        int pageNumber = Integer.parseInt(pageNumberField.getText());
        String note = noteField.getText();
        String keywords = keywordsField.getText();

        // TODO: Create a bookmark and store it in the mainController

        // Close the modal
        Stage stage = (Stage) pageNumberField.getScene().getWindow();
        stage.close();
    }
}
