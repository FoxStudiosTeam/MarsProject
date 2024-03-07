package ru.foxstudios.marsproject.sevices;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ToSendMessageService {
    public void toSendMessage(String message) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out, StandardCharsets.UTF_8));
        writer.write(message);
        writer.flush();

    }
}
