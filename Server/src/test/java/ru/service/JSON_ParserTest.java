package ru.service;

import org.junit.jupiter.api.*;
import ru.message.Message;
import ru.message.MessageStatus;
import ru.service.JSON_Parser;

public class JSON_ParserTest {
    private static long suiteStartTime;
    private long testStartTime;
    @BeforeAll
    public static void initSuite() {
        System.out.println("Running MessageSenderTest");
        suiteStartTime = System.nanoTime();
    }

    @AfterAll
    public static void completeSuite() {
        System.out.println("MessageSenderTest complete: " + (System.nanoTime() - suiteStartTime));
    }

    @BeforeEach
    public void initTest() {
        System.out.println("Starting new test");
        testStartTime = System.nanoTime();
    }

    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete:" + (System.nanoTime() - testStartTime));
    }

    @Test
    public void to_JSONTest(){
        //given:
        final Message message = new Message(MessageStatus.CONNECT, "someNickname", "", "1");
        final String expected = "{\"messageStatus\":\"CONNECT\",\"nickname\":\"someNickname\",\"messageBody\":\"\",\"time\":\"1\"}";
        //when:
        final String result = JSON_Parser.to_JSON(message);
        //then:
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void to_MessageTest(){
        //given:
        final Message expected = new Message(MessageStatus.CONNECT, "someNickname", "", "1");
        final String text = "{\"messageStatus\":\"CONNECT\",\"nickname\":\"someNickname\",\"messageBody\":\"\",\"time\":\"1\"}";
        //when:
        final Message result = JSON_Parser.to_Message(text);
        //then:
        Assertions.assertEquals(expected.getClass(), result.getClass());
        Assertions.assertEquals(expected.getMessageBody(), result.getMessageBody());
        Assertions.assertEquals(expected.getMessageStatus(), result.getMessageStatus());
        Assertions.assertEquals(expected.getNickname(), result.getNickname());
    }
}
