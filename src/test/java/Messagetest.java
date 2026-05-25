/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import com.mycompany.quickchat.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author asele
 */
public class Messagetest {
    
     private Message message1;
     private Message message2;

    // Run before every test
    @BeforeEach
    public void setUp() {

        message1 = new Message();
        message1.setMessageText("Hi Mike, can you join us for dinner tonight?");
        message1.setRecipient("+27718693002");

        message2 = new Message();
        message2.setMessageText("Hi Keegan, did you receive the payment?");
        message2.setRecipient("08575975889"); // invalid
    }

    // ✅ TEST 1: Message under 250 chars
    @Test
    public void testCheckMessageLength_validMessage_returnsSuccess() {
        String result = message1.checkMessageLength(message1.getMessageText());
        assertEquals("Message ready to send.", result);
    }

    // ✅ TEST 2: Message over 250 chars
    @Test
    public void testCheckMessageLength_over250chars_returnsFailure() {
        String longMessage = "a".repeat(260);

        String result = message1.checkMessageLength(longMessage);

        assertTrue(result.contains("Message exceeds 250 characters"));
    }

    // ✅ TEST 3: Valid recipient
    @Test
    public void testCheckRecipientCell_validNumber_returnsSuccess() {
        String result = message1.checkRecipientCell(message1.getRecipient());
        assertEquals("Cell phone number successfully captured.", result);
    }

    // ✅ TEST 4: Invalid recipient
    @Test
    public void testCheckRecipientCell_invalidNumber_returnsFailure() {
        String result = message2.checkRecipientCell(message2.getRecipient());
        assertEquals("Cell phone number is incorrectly formatted.", result);
    }

    // ✅ TEST 5: Message Hash ends correctly
    @Test
    public void testCreateMessageHash_correctFormat() {

        message1.setMessageID("1234567890");
        message1.setNumMessages(0);

        String hash = message1.createMessageHash();

        assertTrue(hash.endsWith(":0:HITONIGHT"));
    }

    // ✅ TEST 6: Hash is uppercase
    @Test
    public void testCreateMessageHash_isUppercase() {

        message1.setMessageID("1234567890");
        message1.setNumMessages(0);

        String hash = message1.createMessageHash();

        assertEquals(hash, hash.toUpperCase());
    }

    // ✅ TEST 7: Message ID exists
    @Test
    public void testMessageID_notNull() {
        message1.generateMessageID();
        assertNotNull(message1.getMessageID());
    }

    // ✅ TEST 8: Message ID length = 10
    @Test
    public void testMessageID_lengthIs10() {
        message1.generateMessageID();
        assertEquals(10, message1.getMessageID().length());
    }

    // ✅ TEST 9: Send option
    @Test
    public void testSentMessage_send() {
        String result = message1.sentMessage(1);
        assertEquals("Message successfully sent.", result);
    }

    // ✅ TEST 10: Disregard option
    @Test
    public void testSentMessage_disregard() {
        String result = message1.sentMessage(2);
        assertEquals("Press 0 to delete the message.", result);
    }

    // ✅ TEST 11: Store option
    @Test
    public void testSentMessage_store() {
        String result = message1.sentMessage(3);
        assertEquals("Message successfully stored.", result);
    }

}
