package ru.message;


public interface MessageBuilder {

    public MessageBuilder setMessageStatus(MessageStatus messageStatus);

    public MessageBuilder setNickname(String nickname);

    public MessageBuilder setMessageBody(String messageBody);

    public MessageBuilder setTime();

    public Message build();
}