package ru.service;


import com.google.gson.GsonBuilder;
import ru.message.Message;

public class JSON_Parser {
    public JSON_Parser() {
    }

    public static String to_JSON(Message message) {
        var builder = new GsonBuilder();
        var gson = builder.create();
        return gson.toJson(message);
    }

    public static Message to_Message(String text) {
        var builder = new GsonBuilder();
        var gson = builder.create();
        return gson.fromJson(text, Message.class);
    }
}
