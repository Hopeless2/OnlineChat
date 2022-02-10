package ru.client;

import ru.chat.Chat;
import ru.service.JSON_Parser;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    private static final String[] settings = getConnectionSettings();
    private static final Chat chat = new Chat();
    private static final Scanner SCANNER = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        assert settings != null;
        var socketAddress = new InetSocketAddress(settings[0], Integer.parseInt(settings[1]));
        final var socketChannel = SocketChannel.open();

        try (socketChannel) {
            socketChannel.connect(socketAddress);
            final var inputBuffer = ByteBuffer.allocate(2 << 10);

            System.out.println("Введите имя");
            var nickname = SCANNER.nextLine();
            var message = chat.register(nickname);
            var text = JSON_Parser.to_JSON(message);

            socketChannel.write(ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8)));
            Output output = new Output(socketChannel, chat);
            output.start();

            while (true) {
                if (inputBuffer.hasRemaining()) {
                    int bytesCount = socketChannel.read(inputBuffer);
                    if (bytesCount == -1) break;
                    text = new String(inputBuffer.array(), 0, bytesCount,
                            StandardCharsets.UTF_8).trim();
                    chat.read(text);
                    inputBuffer.clear();
                }
            }
        }
    }

    public static String[] getConnectionSettings() {
        try (var fileInputStream = new FileInputStream("C:\\Users\\Елисей\\Desktop\\Документы\\Java\\Курсач многопоток сетевой чат\\OnlineChat\\Client\\settings.txt");
             var bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            byte[] buffer = bufferedInputStream.readAllBytes();
            var text = new String(buffer, 0, buffer.length,
                    StandardCharsets.UTF_8);
            return text.split(" ");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
