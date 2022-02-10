package ru.message;


import java.time.LocalDateTime;

public class MyMessageBuilder implements MessageBuilder {
    private MessageStatus messageStatus;
    private String nickname;
    private String messageBody;
    private String time;

    @Override
    public MessageBuilder setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
        return this;
    }

    @Override
    public MessageBuilder setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    @Override
    public MessageBuilder setMessageBody(String messageBody) {
        this.messageBody = messageBody;
        return this;
    }

    @Override
    public MessageBuilder setTime() {
        time = LocalDateTime.now().toString();
        return this;
    }

    @Override
    public Message build() {
        return new Message(messageStatus, nickname, messageBody, time);
    }
}
