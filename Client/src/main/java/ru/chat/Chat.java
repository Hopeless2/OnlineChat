package ru.chat;

import ru.logger.ChatLogger;
import ru.message.Message;
import ru.message.MessageStatus;
import ru.message.MyMessageBuilder;
import ru.service.JSON_Parser;

public class Chat {
    private String nickname = null;
    private boolean isRegistered = false;
    private final ChatLogger LOGGER = new ChatLogger();

    public Chat() {

    }

    public Message register(String nickname) {
        this.nickname = nickname;
        var message = buildMessage("");
        isRegistered = true;
        toConsole(message);
        return message;
    }

    public Message write(String messageBody) {
        var message = buildMessage(messageBody);
        toConsole(message);
        return message;
    }

    public void read(String textJson) {
        var message = JSON_Parser.to_Message(textJson);
        toConsole(message);
    }


    private void toConsole(Message message) {
        String text = LOGGER.newMessage(message);
        System.out.println(text);
    }

    private Message buildMessage(String messageBody) {
        MessageStatus ms;
        if (messageBody.equals("") && !isRegistered) {
            ms = MessageStatus.CONNECT;
        } else if (messageBody.equals("end")) {
            ms = MessageStatus.DISCONNECT;
        } else {
            ms = MessageStatus.MESSAGE;
        }
        return new MyMessageBuilder()
                .setMessageStatus(ms)
                .setNickname(this.nickname)
                .setMessageBody(messageBody)
                .setTime()
                .build();
    }

}
