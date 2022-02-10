package ru.message;


public class Message implements Comparable<Message> {

    private final MessageStatus messageStatus;
    private final String nickname;
    private final String messageBody;
    private final String time;

    public Message(MessageStatus messageStatus, String nickname, String messageBody, String time) {
        this.messageStatus = messageStatus;
        this.nickname = nickname;
        this.messageBody = messageBody;
        this.time = time;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getTime() {
        return time;
    }


    @Override
    public int compareTo(Message message) {
        int result = this.getTime().compareTo(message.getTime());
        if (result == 0) {
            return 1;
        } else {
            return result;
        }
    }
}