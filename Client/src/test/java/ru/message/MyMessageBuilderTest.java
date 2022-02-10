package ru.message;

import org.junit.jupiter.api.*;

public class MyMessageBuilderTest {
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
    public void newMessageTest() {
        //given:
        String messageBody = "messageBody";
        MessageStatus messageStatus = MessageStatus.MESSAGE;
        String nickname = "nickname";
        //when::
        final Message result = new MyMessageBuilder()
                .setMessageBody(messageBody)
                .setMessageStatus(messageStatus)
                .setNickname(nickname)
                .setTime()
                .build();
        //then:
        Assertions.assertEquals(Message.class, result.getClass());
        Assertions.assertEquals(messageBody, result.getMessageBody());
        Assertions.assertEquals(messageStatus, result.getMessageStatus());
        Assertions.assertEquals(nickname, result.getNickname());
    }
}
