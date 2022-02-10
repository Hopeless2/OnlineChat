package ru.server;

import ru.logger.ChatLogger;
import ru.message.MessageStatus;
import ru.service.JSON_Parser;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

class ServerThread extends Thread {

    private final SocketChannel socketChannel;
    private final ChatLogger chatLogger;

    public ServerThread(SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        chatLogger = ChatLogger.get();
        start();
    }

    @Override
    public void run() {
        final var inputBuffer = ByteBuffer.allocate(2 << 10);
        try {
            while (true) {
                var bytesCount = socketChannel.read(inputBuffer);
                if (bytesCount == -1) break;
                var text = new String(inputBuffer.array(), 0, bytesCount,
                        StandardCharsets.UTF_8);
                inputBuffer.clear();
                var message = JSON_Parser.to_Message(text);
                chatLogger.newMessage(message);
                for (ServerThread st : Server.serverList) {
                    if (this == st) continue;
                    st.send(text);
                }
                if (message.getMessageStatus().equals(MessageStatus.DISCONNECT)) {
                    break;
                }
            }
            socketChannel.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            this.interrupt();
        }
    }

    private void send(String text) {
        try {
            socketChannel.write(ByteBuffer.wrap(text.getBytes(StandardCharsets.UTF_8)));
        } catch (IOException ignored) {
        }
    }
}