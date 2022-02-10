package ru.message;

import org.junit.jupiter.api.*;

public class MessageTest {
    private static long suiteStartTime;
    private long testStartTime;
    final static Message message = new MyMessageBuilder()
            .setMessageBody("messageBody")
            .setMessageStatus(MessageStatus.MESSAGE)
            .setNickname("nickname")
            .setTime()
            .build();

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
    public void getMessageStatusTest() {
        //given:
        MessageStatus expected = MessageStatus.MESSAGE;
        //when:
        MessageStatus result = message.getMessageStatus();
        //then:
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getMessageBodyTest() {
        //given:
        String expected = "messageBody";
        //when:
        String result = message.getMessageBody();
        //then:
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getNicknameTest() {
        //given:
        String expected = "nickname";
        //when:
        String result = message.getNickname();
        //then:
        Assertions.assertEquals(expected, result);
    }
}
