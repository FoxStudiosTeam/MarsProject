package ru.foxstudios.marsproject.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.foxstudios.marsproject.MarsApplication;
import ru.foxstudios.marsproject.sevices.ToSendMessageService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ToSendMessageController {

//    private ToSendMessageService toSendMessageService = new ToSendMessageService();
    @FXML
    public Button toSendButton;
    @FXML
    public Pane taskPane;
    @FXML
    public Button exitTaskPaneButton;
    @FXML
    public Button swapHistory;
    @FXML
    public Button pinFileButton;
    @FXML
    public Label pinFileNameLabel;
    @FXML
    public TextField textFieldNameMessage;
    @FXML
    public TextArea textAreaInfoMessage;
    public Label dateTextCreateTask;
    public Label labelFIO;
    public Button langTaskButton;
    public Label labelReport;
    private String filePath = null;
    public VBox toSendMessageVBox;
    public Pane paneShadowTask;
    public Button changeThemeTaskButton;
    public Pane blankTaskPane;
    @FXML
    public Button closeProgramTask;
    //toSendMessageService.toSendMessage(textFieldNameMessage.getText() + '\n' + textAreaInfoMessage.getText());
    @FXML
    public void ToSendButton(ActionEvent event) {
        try {
            URL url = new URL("http://127.0.0.1:30007/data/add");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");

            OutputStream outputStream = connection.getOutputStream();
            String lineBreak = "\r\n";

            outputStream.write(("------WebKitFormBoundary7MA4YWxkTrZu0gW" + lineBreak).getBytes());
            outputStream.write(("Content-Disposition: form-data; name=\"name\"" + lineBreak + lineBreak + textFieldNameMessage.getText() + lineBreak).getBytes());

            outputStream.write(("------WebKitFormBoundary7MA4YWxkTrZu0gW" + lineBreak).getBytes());
            outputStream.write(("Content-Disposition: form-data; name=\"commentary\"" + lineBreak + lineBreak + textAreaInfoMessage.getText() + lineBreak).getBytes());

            try {
                File file = new File(filePath);
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] fileBytes = new byte[(int) file.length()];
                fileInputStream.read(fileBytes);
                fileInputStream.close();
                outputStream.write(("------WebKitFormBoundary7MA4YWxkTrZu0gW" + lineBreak).getBytes());
                outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + lineBreak).getBytes());
                outputStream.write(("Content-Type: application/octet-stream" + lineBreak + lineBreak).getBytes());
                outputStream.write(fileBytes);
                outputStream.write((lineBreak + "------WebKitFormBoundary7MA4YWxkTrZu0gW--" + lineBreak).getBytes());
                System.out.println(outputStream);
                outputStream.flush();
                outputStream.close();
            }
            catch (Exception e){
                outputStream.write(("------WebKitFormBoundary7MA4YWxkTrZu0gW" + lineBreak).getBytes());
                outputStream.write(("Content-Disposition: form-data; name=\"file\"; filename=\""  + "\"" + lineBreak).getBytes());
                outputStream.write(("Content-Type: application/octet-stream" + lineBreak + lineBreak).getBytes());
                outputStream.write(("").getBytes());
                outputStream.write((lineBreak + "------WebKitFormBoundary7MA4YWxkTrZu0gW--" + lineBreak).getBytes());
                System.out.println(outputStream);
                outputStream.flush();
                outputStream.close();
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            connection.disconnect();
            textAreaInfoMessage.setText("");
            textFieldNameMessage.setText("");
            filePath = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void PinFileButton(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выберите файл для отправки");
        Stage stage = (Stage) pinFileButton.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null){
            filePath = selectedFile.getAbsolutePath();
            pinFileNameLabel.setText("(Файл прикреплен/The file is attached)");
        }
    }
    @FXML
    public void ExitTaskPaneButton(ActionEvent event) throws IOException {
        MarsApplication.changeScene("hello-view.fxml");
    }
    public void SwapHistory(ActionEvent event) throws IOException {
        MarsApplication.changeScene("history-view.fxml");
    }
    @FXML
    public void CloseProgramTask(ActionEvent event){
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
        dateTextCreateTask.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
    public void LangRU(){
        langTaskButton.setText("RU");
        labelFIO.setText("Ф.И.О.");
        labelReport.setText("Отчет");
        pinFileButton.setText("Прикрепить файл");
        toSendButton.setText("Отправить");
        exitTaskPaneButton.setText("Назад");
        swapHistory.setText("История");
    }
    public void LangEN(){
        langTaskButton.setText("EN");
        labelFIO.setText("L.F.P.");
        labelReport.setText("Report");
        pinFileButton.setText("Attach a file");
        toSendButton.setText("Send");
        exitTaskPaneButton.setText("Back");
        swapHistory.setText("History");
    }
    @FXML
    public void LangTaskButton() {
        if (MarsApplication.isRussian) {
            MarsApplication.isRussian = false;
            LangEN();
        } else {
            MarsApplication.isRussian = true;
            LangRU();
        }
    }
    public void BlackTheme(){
        pinFileButton.setStyle("-fx-background-color: #2C2C2C; -fx-text-fill: white; -fx-padding: 0 0 0 0; -fx-border-color: #FFFFFF; -fx-border-radius: 5px");
        toSendButton.setStyle("-fx-background-color: #2C2C2C; -fx-text-fill: white; -fx-padding:  0 0 0 25; -fx-border-color: #FFFFFF; -fx-border-radius: 5px");
        toSendMessageVBox.setStyle("-fx-background-color: #8C8E97");
        paneShadowTask.setStyle("-fx-background-color: #5F5F5F; -fx-background-radius: 7px");
        blankTaskPane.setStyle("-fx-background-color: #2C2C2C; -fx-background-radius: 10px");

    }
    public void LightTheme(){
        pinFileButton.setStyle("-fx-background-color: #FF8E3D; -fx-text-fill: white; -fx-padding: 0 0 0 0; -fx-border-color: #FFFFFF; -fx-border-radius: 5px");
        toSendButton.setStyle("-fx-background-color: #FF8E3D; -fx-text-fill: white; -fx-padding:  0 0 0 25; -fx-border-color: #FFFFFF; -fx-border-radius: 5px");
        toSendMessageVBox.setStyle("-fx-background-color: #FFFFFF");
        paneShadowTask.setStyle("-fx-background-color: #E8E7E5; ; -fx-background-radius: 7px");
        blankTaskPane.setStyle("-fx-background-color: #FF8E3D; -fx-background-radius: 10px");
    }
    public void ChangeThemeTaskButton(){
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
