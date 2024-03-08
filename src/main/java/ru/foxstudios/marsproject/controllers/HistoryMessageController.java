package ru.foxstudios.marsproject.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
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
    public Button langHistoryButton;
    public Button changeThemeHistoryButton;
    public VBox historyMainVBox;
    public VBox historyTaskVBox;
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
        dateTextHistory.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
    public void LangRU(){
        langHistoryButton.setText("RU");
        exitHistoryPaneButton.setText("Назад");
        swapForm.setText("Написать отчет");
    }
    public void LangEN(){
        langHistoryButton.setText("EN");
        exitHistoryPaneButton.setText("Back");
        swapForm.setText("Write a report");
    }
    @FXML
    public void LangHistoryButton() {
        if (MarsApplication.isRussian) {
            MarsApplication.isRussian = false;
            LangEN();
        } else {
            MarsApplication.isRussian = true;
            LangRU();
        }
    }
    @FXML
    public void BlackTheme(){
        historyMainVBox.setStyle("-fx-background-color: #8C8E97");
        historyTaskVBox.setStyle("-fx-background-color: #5F5F5F; -fx-background-radius: 5px");
    }
    @FXML
    public void LightTheme(){
        historyMainVBox.setStyle("-fx-background-color: #FFFFFF");
        historyTaskVBox.setStyle("-fx-background-color: #E8E7E5; -fx-background-radius: 5px");
    }
    @FXML
    public void ChangeHistoryTaskButton(){
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
