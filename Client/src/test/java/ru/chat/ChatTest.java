package ru.chat;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.message.Message;
import ru.message.MessageStatus;
import ru.message.MyMessageBuilder;

import java.util.stream.Stream;

public class ChatTest {
    private static long suiteStartTime;
    private long testStartTime;
    final static Chat chat = new Chat();

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
    public void registerTest() {
        //given:
        String nickname = "someNickname";
        final Message expected = new MyMessageBuilder()
                .setMessageBody("")
                .setMessageStatus(MessageStatus.CONNECT)
                .setNickname(nickname)
                .setTime()
                .build();
        //when:
        final Message result = chat.register(nickname);
        //then:
        Assertions.assertEquals(expected.getMessageStatus(), result.getMessageStatus());
        Assertions.assertEquals(expected.getMessageBody(), result.getMessageBody());
        Assertions.assertEquals(expected.getNickname(), result.getNickname());
        Assertions.assertEquals(expected.getClass(), result.getClass());
    }

    @ParameterizedTest
    @MethodSource("message")
    public void writeTest(String messageBody, MessageStatus messageStatus, String nickname) {
        //given:
        final Message expected = new MyMessageBuilder()
                .setMessageBody(messageBody)
                .setMessageStatus(messageStatus)
                .setNickname(nickname)
                .setTime()
                .build();
        //when:
        final Message result = chat.write(messageBody);
        //then:
        Assertions.assertEquals(expected.getMessageStatus(), result.getMessageStatus());
        Assertions.assertEquals(expected.getMessageBody(), result.getMessageBody());
        Assertions.assertEquals(expected.getNickname(), result.getNickname());
        Assertions.assertEquals(expected.getClass(), result.getClass());
    }

    private static Stream<Arguments> message() {
        return Stream.of(
                Arguments.of("end", MessageStatus.DISCONNECT, "someNickname"),
                Arguments.of("someMessage", MessageStatus.MESSAGE, "someNickname"),
                Arguments.of("", MessageStatus.MESSAGE, "someNickname"));
    }
}
