package com.example.ebook_starter;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Optional;

public class BookmarkListViewController {

    private ArrayList<Bookmark> bookmarksList = new ArrayList<>();
    private ReaderController readerController;


    @FXML
    private TableView<Bookmark> bookmarksTable;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Bookmark, Integer> pageColumn;

    @FXML
    private TableColumn<Bookmark, String> notesColumn;

    @FXML
    private TableColumn<Bookmark, String> keywordsColumn;

    @FXML
    private TableColumn<Bookmark, String> datesColumn;

    @FXML
    private TableColumn<Bookmark, Void> removeColumn;

    @FXML
    private TableColumn<Bookmark, Void> editColumn;

    public void initialize() {
        // Initialize column cell value factories
        pageColumn.setCellValueFactory(new PropertyValueFactory<>("pageNumber"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        keywordsColumn.setCellValueFactory(new PropertyValueFactory<>("keywords"));
        datesColumn.setCellValueFactory(new PropertyValueFactory<>("formattedCreationTime"));

        // Set up text wrapping for the notes column
        notesColumn.setCellFactory(tc -> {
            TableCell<Bookmark, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(notesColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell;
        });

        // Setup the remove column
        removeColumn.setCellFactory(param -> new TableCell<Bookmark, Void>() {
            private final Button removeButton = new Button("Remove");

            {
                removeButton.setOnAction((ActionEvent event) -> {
                    Bookmark bookmark = getTableView().getItems().get(getIndex());
                    removeBookmark(bookmark);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(removeButton);
                }
            }
        });

        // Setup the edit column
        editColumn.setCellFactory(param -> new TableCell<Bookmark, Void>() {
            private final Button editButton = new Button("Edit");

            {
                editButton.setOnAction((ActionEvent event) -> {
                    Bookmark bookmark = getTableView().getItems().get(getIndex());
                    showEditBookmarkDialog(bookmark);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                    setAlignment(Pos.CENTER); // Center-align the cell content including the button
                    // Ensure the button is always centered, even if the cell's size changes
                    editButton.setMaxWidth(Double.MAX_VALUE);
                    HBox.setHgrow(editButton, Priority.ALWAYS);
                    HBox hbox = new HBox(editButton);
                    hbox.setAlignment(Pos.CENTER); // Center alignment within the HBox
                    setGraphic(hbox);
                }
            }
        });

        // Ensure the table uses this policy to allow the column to grow
        bookmarksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Listener for changes to the search field text
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        //updateFilteredBookmarks(newValue);
        });

    }

    public void setReaderController(ReaderController controller) {
        this.readerController = controller;
    }

    public void populateTable(ArrayList<Bookmark> bookmarks) {
        bookmarksList.clear();
        bookmarksList.addAll(bookmarks);
        // TODO: Implement filtering based on the keyword in searchField.getText()
        bookmarksTable.getItems().setAll(bookmarksList);
    }

    // Remove a bookmark
    private final Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this bookmark?", ButtonType.YES, ButtonType.NO);

    private void removeBookmark(Bookmark bookmark) {
        // TODO: Remove a bookmark from all relevant ArrayLists.
    }

    private void showEditBookmarkDialog(Bookmark bookmark) {
        // TODO: Show a dialog to edit an existing bookmark.
        //  Update bookmark in all relevant ArrayLists.
    }

}

