package ru.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.xml.DOMConfigurator;
import ru.message.Message;

import java.time.LocalDateTime;

public class ChatLogger {
    private final String delimiter = "\n#############################################\n";
    private final String startMessage = delimiter + delimiter + "Запуск программы: " + LocalDateTime.now() + delimiter + delimiter;
    private static org.apache.log4j.Logger logger;

    public ChatLogger() {
        DOMConfigurator.configure("C:\\Users\\Елисей\\Desktop\\Документы\\Java\\Курсач многопоток сетевой чат\\OnlineChat\\Client\\log4j.xml");
        logger = Logger.getLogger(ChatLogger.class);
        logger.log(Priority.INFO, startMessage);
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
                text = delimiter + "(" + message.getTime() + ")" + "НОВЫЙ УЧАСТНИК ЧАТА" +
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
