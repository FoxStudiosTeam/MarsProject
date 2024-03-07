package ru.foxstudios.marsproject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ru.foxstudios.marsproject.MarsApplication;

import java.io.IOException;

public class HistoryMessageController {
    @FXML
    public Button exitHistoryPaneButton;
    @FXML
    public void ExitHistoryPaneButton(ActionEvent event) throws IOException {
        MarsApplication.changeScene("hello-view.fxml");
    }
}
