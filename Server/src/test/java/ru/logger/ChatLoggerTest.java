package ru.logger;

import org.junit.jupiter.api.*;
import ru.message.Message;
import ru.message.MessageStatus;

public class ChatLoggerTest {
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
    public void newMessage_Test(){
        //given:
        ChatLogger logger = ChatLogger.get();
        final String expected = "\n" +
                "#############################################\n" +
                "(1)НОВОЕ СОЕДИНЕНИЕ Никнейм: someNickname\n" +
                "#############################################\n" +
                "\n";
        //when:
        final Message message = new Message(MessageStatus.CONNECT, "someNickname", "", "1");
        final String result = logger.newMessage(message);
        //then:
        Assertions.assertEquals(expected, result);
    }
}
