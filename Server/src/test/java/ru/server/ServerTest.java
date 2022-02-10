package ru.server;

import org.junit.jupiter.api.*;

public class ServerTest {
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
    public void getConnectionSettingsTest(){
        //before:
        String[] expected = new String[2];
        expected[0] = "localhost";
        expected[1] = "8080";
        //when:
        String[] result = Server.getConnectionSettings();
        //then:
        Assertions.assertEquals(expected[0], result[0]);
        Assertions.assertEquals(expected[1], result[1]);
    }
}
