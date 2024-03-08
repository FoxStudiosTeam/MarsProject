package ru.foxstudios.marsproject.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.foxstudios.marsproject.MarsApplication;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MarsController {
    @FXML
    private Label dateText;
    @FXML
    public Button langButton;
    @FXML
    public Button createTaskButton;
    @FXML
    public Button historyButton;
    @FXML
    public Button closeProgram;
    public VBox helloVBox;

    @FXML
    public void CreateTask(ActionEvent event) throws IOException {
        MarsApplication.changeScene("form-view.fxml");
    }

    @FXML
    public void HistoryTask(ActionEvent event) throws IOException {
        MarsApplication.changeScene("history-view.fxml");
    }

    @FXML
    public void CloseProgram(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void initialize() {
        if(MarsApplication.isRussian){
            LangRU();
        }
        else{
            LangEN();
        }
        if(MarsApplication.isTheme){
            LightTheme();
        }
        else{
            BlackTheme();
        }
        dateText.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
    public void LangRU(){
        langButton.setText("RU");
        createTaskButton.setText("Написать отчет");
        historyButton.setText("История");
    }
    public void LangEN(){
        langButton.setText("EN");
        createTaskButton.setText("Write a report");
        historyButton.setText("History");
    }
    @FXML
    public void LangButton(ActionEvent event) {
        if (MarsApplication.isRussian) {
            MarsApplication.isRussian = false;
            LangEN();
        } else {
            MarsApplication.isRussian = true;
            LangRU();
        }
    }
    public void BlackTheme(){
        helloVBox.setStyle("-fx-background-color: #8C8E97");
        createTaskButton.setStyle("-fx-background-color: #2C2C2C; -fx-text-fill: white; -fx-padding: 1 1 1 20");
        historyButton.setStyle("-fx-background-color: #2C2C2C; -fx-text-fill: white; -fx-padding: 1 12 1 15");
    }
    public void LightTheme(){
        helloVBox.setStyle("-fx-background-color: #FFFFFF");
        createTaskButton.setStyle("-fx-background-color: #FF8E3D; -fx-text-fill: white; -fx-padding: 1 1 1 20");
        historyButton.setStyle("-fx-background-color: #FF8E3D; -fx-text-fill: white; -fx-padding: 1 12 1 15");
    }
    public void HelloVBox(){
        if (MarsApplication.isTheme){
            MarsApplication.isTheme = false;
            BlackTheme();
        }
        else{
            MarsApplication.isTheme = true;
            LightTheme();
        }
    }
}
