package com.example.ebook_starter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ReaderController {


    private ArrayList<String> pages = new ArrayList<>();
    private int currentPageIndex = 0;
    private ArrayList<Bookmark> bookmarks = new ArrayList<>();

    @FXML
    private Label bookTitle;

    @FXML
    private Label bookAuthor;

    @FXML
    private TextArea pageContent;

    @FXML
    private TextField pageNumber;
    @FXML
    private Label totalPagesLabel = new Label("of ...");

    // Method to initialize the controller
    public void initialize() {

    }

    public void loadBookText(String text) {
        loadBook(text); // Assuming loadBook is adapted to accept a String
        extractAndSetBookDetails();
        if (!pages.isEmpty()) {
            displayPage(currentPageIndex);
        }
    }

    private void loadBook(String text) {
        try {
            // NOTE: WANT A DIFFERENT BOOK? CHANGE IT HERE.
            pages = readBookFromFile(text);
            totalPagesLabel.setText(" of " + (pages.size()-1));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the book.");
        }
    }

    private ArrayList<String> readBookFromFile(String resourcePath) throws IOException {
        ArrayList<String> pages = new ArrayList<>();
        StringBuilder currentPage = new StringBuilder();
        int lineCount = 0;
        int linesPerPage = 32; // You can adjust this value

        // Use try-with-resources to ensure the InputStream is closed after use
        try (InputStream is = getClass().getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                currentPage.append(line).append("\n");
                lineCount++;
                if (lineCount == linesPerPage) {
                    pages.add(currentPage.toString());
                    currentPage = new StringBuilder(); // Reset for the next page
                    lineCount = 0;
                }
            }
            // Add the last page if there's remaining content
            if (!currentPage.toString().isEmpty()) {
                pages.add(currentPage.toString());
            }
        }

        return pages;
    }

    private void extractAndSetBookDetails() {
        // TODO: Set the book title and author (with bookTitle.setText() and
        //  bookAuthor.setText()) and remove these lines from the first page.

    }

    private void displayPage(int pageIndex) {
        // TODO: Display the specified page by setting
        //  pageContent, pageNumber, and currentPageIndex.
    }

    public void showAddBookmarkModal() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookmark-modal-view.fxml"));
            Parent root = loader.load();

            BookmarkModalController modalController = loader.getController();
            modalController.setMainController(this);
            modalController.preloadData(currentPageIndex + 1, LocalDateTime.now()); // Assuming currentPageIndex is the current page index

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setMinWidth(200); // Set the minimum width of the window
            stage.setMinHeight(300); // Set the minimum height of the window
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onJumpToPage(){
        // TODO: Jump to the page specified by
        //  pageNumber.getText()
    }

    @FXML
    private void onViewBookmarks() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookmark-list-view.fxml")); // Update path as necessary
            Parent root = loader.load();

            BookmarkListViewController bookmarksListController = loader.getController();

            // Check if there's at least one bookmark to display
            if (!bookmarks.isEmpty()) {
                bookmarksListController.populateTable(bookmarks);
                bookmarksListController.setReaderController(this); // Pass the reference
            }

            Stage stage = new Stage();
            stage.setTitle("Bookmarks");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // TODO: Implement methods to add and remove
    //  bookmarks in the bookmarks list.

    @FXML
    private void onNextPage() {
        // TODO: Advance to the next page.
    }

    @FXML
    private void onPreviousPage() {
        // TODO: Return to the previous page.
    }

    @FXML
    private void onFirstPage() {
        // TODO: Display the first page.
    }

    @FXML
    private void onBookmarkPage() {
        showAddBookmarkModal();
    }

    // Getter for bookTitle text
    public String getBookTitleText() {
        return bookTitle.getText();
    }

    // Getter for bookAuthor text
    public String getBookAuthorText() {
        return bookAuthor.getText();
    }

    // Getter for pageContent text
    public String getPageContentText() {
        return pageContent.getText();
    }

    // Getter for pageNumber as int
    public int getPageNumberValue() {
        // Assuming pageNumber's text is always a valid integer
        return Integer.parseInt(pageNumber.getText());
    }


}
