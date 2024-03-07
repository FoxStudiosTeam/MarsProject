package ru.foxstudios.marsproject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.foxstudios.marsproject.MarsApplication;
import ru.foxstudios.marsproject.sevices.ToSendMessageService;

import java.io.File;
import java.io.IOException;

public class ToSendMessageController {
    @FXML
    private ToSendMessageService toSendMessageService = new ToSendMessageService();
    @FXML
    public Button toSendButton;
    @FXML
    public Pane taskPane;
    @FXML
    public Button exitTaskPaneButton;
    @FXML
    public Button pinFileButton;
    @FXML
    public Label pinFileNameLabel;
    @FXML
    public TextField textFieldNameMessage;
    @FXML
    public TextArea textAreaInfoMessage;

    @FXML
    public void ToSendButton(ActionEvent event){
        try {
//            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .build();
//            MediaType mediaType = MediaType.parse("text/plain");
//            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                    .addFormDataPart("name","test")
//                    .addFormDataPart("commentary","test123")
//                    .addFormDataPart("file","/C:/Users/Makson/Desktop/меню.txt",
//                            RequestBody.create(MediaType.parse("application/octet-stream"),
//                                    new File("/C:/Users/Makson/Desktop/меню.txt")))
//                    .build();
//            Request request = new Request.Builder()
//                    .url("http://127.0.0.1:55908/data/add")
//                    .method("POST", body)
//                    .build();
//            Response response = client.newCall(request).execute();
            toSendMessageService.toSendMessage(textFieldNameMessage.getText() + '\n' + textAreaInfoMessage.getText());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    @FXML
    public void PinFileButton(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл для отправки");
        Stage stage = (Stage) pinFileButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null){
            pinFileNameLabel.setText("Файл прикреплен");
        }
    }
    @FXML
    public void ExitTaskPaneButton(ActionEvent event) throws IOException {
        MarsApplication.changeScene("hello-view.fxml");
    }
}
