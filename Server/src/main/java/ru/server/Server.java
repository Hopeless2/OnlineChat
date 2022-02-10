package ru.server;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final String[] settings = getConnectionSettings();
    public static List<ServerThread> serverList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        final var serverChannel = ServerSocketChannel.open();
        try (serverChannel) {
            assert settings != null;
            serverChannel.bind(new InetSocketAddress(settings[0], Integer.parseInt(settings[1])));
            while (true) {
                var socketChannel = serverChannel.accept();
                System.out.println("new connection");
                try {
                    serverList.add(new ServerThread(socketChannel));
                } catch (IOException e) {
                    socketChannel.close();
                }
            }
        }
    }

    public static String[] getConnectionSettings() {
        try (var fileInputStream = new FileInputStream("C:\\Users\\Елисей\\Desktop\\Документы\\Java\\Курсач многопоток сетевой чат\\OnlineChat\\Server\\settings.txt");
             var bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            byte[] buffer = bufferedInputStream.readAllBytes();
            String text = new String(buffer, 0, buffer.length,
                    StandardCharsets.UTF_8);
            return text.split(" ");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}