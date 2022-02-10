package ru.logger;

import org.apache.log4j.Priority;
import org.apache.log4j.xml.DOMConfigurator;
import ru.message.Message;

import java.time.LocalDateTime;

public class ChatLogger {
    private static volatile ChatLogger instance;

    private static volatile org.apache.log4j.Logger logger;
    private final String delimiter = "\n#############################################\n";
    private final String start = delimiter + delimiter + "Запуск программы: " + LocalDateTime.now() + delimiter + delimiter;

    private ChatLogger() {
        DOMConfigurator.configure("C:\\Users\\Елисей\\Desktop\\Документы\\Java\\Курсач многопоток сетевой чат\\OnlineChat\\Server\\log4j.xml");
        logger = org.apache.log4j.Logger.getLogger(ChatLogger.class);
        logger.log(Priority.INFO, start);
    }

    public static ChatLogger get() {
        if (instance == null) instance = new ChatLogger();
        return instance;
    }

    public String newMessage(Message message) {
        String text = refactorMessage(message);
        logger.log(Priority.INFO, text);
        return text;
    }

    private String refactorMessage(Message message) {
        String text;
        switch (message.getMessageStatus()) {
            case CONNECT -> {
                text = delimiter + "(" + message.getTime() + ")" + "НОВОЕ СОЕДИНЕНИЕ" +
                        " Никнейм: " + message.getNickname() + delimiter + "\n";
                return text;
            }
            case MESSAGE -> {
                text = delimiter + "(" + message.getTime() + ")" +
                        message.getNickname() + ": " + message.getMessageBody() + delimiter + "\n";
                return text;
            }
            case DISCONNECT -> {
                text = delimiter + "(" + message.getTime() + ")" +
                        "Пользователь " + message.getNickname() + " вышел из чата" + delimiter + "\n";
                return text;
            }
            default -> {
                return "";
            }
        }
    }
}
