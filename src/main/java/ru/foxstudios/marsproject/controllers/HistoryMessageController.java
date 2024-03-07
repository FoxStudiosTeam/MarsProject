package ru.foxstudios.marsproject.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ru.foxstudios.marsproject.MarsApplication;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryMessageController {
    @FXML
    public Button exitHistoryPaneButton;
    @FXML
    public Button swapForm;
    @FXML
    public Label dateTextHistory;
    @FXML
    public Button closeProgramHistory;
    @FXML
    public void ExitHistoryPaneButton(ActionEvent event) throws IOException {
        MarsApplication.changeScene("hello-view.fxml");
    }
    @FXML
    public void SwapForm(ActionEvent event) throws IOException {
        MarsApplication.changeScene("form-view.fxml");
    }
    @FXML
    public void CloseProgramHistory(ActionEvent event){
        Platform.exit();
    }
    @FXML
    public void initialize() {
        dateTextHistory.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
}
