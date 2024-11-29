package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
//--module-path /home/yuliya/Javafx/javafx-sdk-17.0.13/lib --add-modules javafx.controls,javafx.fxml

/**
 * Телефонная книга
 */
public class PhoneBook extends Application {
    private ObservableList<Contact> contacts = FXCollections.observableArrayList();
    private ListView<Contact> contactListView = new ListView<>(contacts);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Телефонный справочник");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Имя:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        Label phoneLabel = new Label("Телефон:");
        GridPane.setConstraints(phoneLabel, 0, 1);
        TextField phoneInput = new TextField();
        GridPane.setConstraints(phoneInput, 1, 1);

        Button addButton = new Button("Добавить");
        GridPane.setConstraints(addButton, 1, 2);
        addButton.setOnAction(e -> {
            String name = nameInput.getText();
            String phone = phoneInput.getText();
            if (!name.isEmpty() && !phone.isEmpty()) {
                contacts.add(new Contact(name, phone));
                nameInput.clear();
                phoneInput.clear();
            } else {
                showMessage("Ошибка", "Имя и телефон не могут быть пустыми!");
            }
        });

        Button deleteButton = new Button("Удалить");
        GridPane.setConstraints(deleteButton, 1, 3);
        deleteButton.setOnAction(e -> {
            Contact selectedContact = contactListView.getSelectionModel().getSelectedItem();
            if (selectedContact != null) {
                contacts.remove(selectedContact);
            } else {
                showMessage("Ошибка", "Выберите контакт для удаления!");
            }
        });

        contactListView.setPrefHeight(200);
        GridPane.setConstraints(contactListView, 0, 4, 2, 1);

        grid.getChildren().addAll(nameLabel, nameInput, phoneLabel, phoneInput, addButton, deleteButton, contactListView);

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
