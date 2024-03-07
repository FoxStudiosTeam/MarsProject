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
import javafx.stage.Stage;
import ru.foxstudios.marsproject.MarsApplication;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class MarsController {
    @FXML
    private Label dateText;
    @FXML
    public Button createTaskButton;
    @FXML
    public Button historyButton;
    @FXML
    public Button closeProgram;
    @FXML
    public void CreateTask(ActionEvent event) throws IOException {
        MarsApplication.changeScene("form-view.fxml");
    }
    @FXML
    public void HistoryTask(ActionEvent event) throws IOException{
        MarsApplication.changeScene("history-view.fxml");
    }
    @FXML
    public void CloseProgram(ActionEvent event){
        Platform.exit();
    }
    @FXML
    public void initialize() {
        dateText.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
}
