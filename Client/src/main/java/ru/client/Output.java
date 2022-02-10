package ru.client;

import ru.chat.Chat;
import ru.message.MessageStatus;
import ru.service.JSON_Parser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Output extends Thread {
    private final SocketChannel socketChannel;
    private final Chat chat;
    private final Scanner scanner = new Scanner(System.in);

    public Output(SocketChannel socketChannel, Chat chat) {
        this.socketChannel = socketChannel;
        this.chat = chat;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String messageBody;
                System.out.println("Введите сообщение");
                messageBody = scanner.nextLine();
                var message = chat.write(messageBody);
                var text = JSON_Parser.to_JSON(message);
                socketChannel.write(ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8)));
                if (message.getMessageStatus().equals(MessageStatus.DISCONNECT)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.interrupt();
    }
}
