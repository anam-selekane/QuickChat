/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.json.JSONObject;

public class Message {

    private String messageID;
    private String messageText;
    private int numMessages;
    private String recipient;
    private String messageHash;

// =========================================================
    // PART 3 ARRAYS
    // =========================================================

    private static List<String> sentMessages = new ArrayList<>();
    private static List<String> disregardedMessages = new ArrayList<>();
    private static List<String> storedMessages = new ArrayList<>();
    private static List<String> messageHashes = new ArrayList<>();
    private static List<String> messageIDs = new ArrayList<>();
    private static List<String> recipientList = new ArrayList<>();

    public Message() {
    }

    public Message(String messageID, int numMessages, String recipient, String messageText) {
        this.messageID = messageID;
        this.numMessages = numMessages;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public int getNumMessages() {
        return numMessages;
    }

    public void setNumMessages(int numMessages) {
        this.numMessages = numMessages;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public void setMessageHash(String messageHash) {
        this.messageHash = messageHash;
    }

    public void generateMessageID() {
        Random rand = new Random();
        StringBuilder id = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            id.append(rand.nextInt(10));
        }
        this.messageID = id.toString();
    }

    public String checkMessageLength(String messageText) {
        if (messageText == null) {
            return "Message is empty.";
        }
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            return "Message exceeds 250 characters";
        }
    }

    public String checkRecipientCell(String recipient) {
        if (recipient != null && recipient.startsWith("+27") && recipient.length() == 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted.";
        }
    }

    public final String createMessageHash() {
        if (messageID == null || messageID.length() < 2 || messageText == null || messageText.isBlank()) {
            return "";
        }
        String[] words = messageText.split(" ");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");
        return messageID.substring(0, 2) + ":" + numMessages + ":" + (firstWord + lastWord).toUpperCase();
    }

    public String sentMessage(int option) {
        switch (option) {
            case 1:
                if (messageText != null) sentMessages.add(messageText);
                if (messageID != null) messageIDs.add(messageID);
                if (recipient != null) recipientList.add(recipient);
                if (messageHash != null) messageHashes.add(messageHash);
                return "Message successfully sent.";
            case 2:
                if (messageText != null) disregardedMessages.add(messageText);
                return "Press 0 to delete the message.";
            case 3:
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    public String sentMessage() {
        return sentMessage(1);
    }

    public String printMessage() {
        return "Message[ID=" + messageID + ", recipient=" + recipient + ", text=" + messageText + "]";
    }

    public void storeMessage() {
        if (messageID == null || messageID.isBlank()) {
            generateMessageID();
        }
        this.messageHash = createMessageHash();
        JSONObject json = new JSONObject();
        json.put("MessageID", messageID);
        json.put("Recipient", recipient);
        json.put("Message", messageText);
        json.put("Hash", messageHash);

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(json.toString() + "\n");
            storedMessages.add(messageText);
            if (messageID != null) messageIDs.add(messageID);
            if (recipient != null) recipientList.add(recipient);
            if (messageHash != null) messageHashes.add(messageHash);
        } catch (IOException e) {
            System.err.println("Could not store message: " + e.getMessage());
        }
    }

    public static void loadStoredMessages() {
        storedMessages.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("messages.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JSONObject obj = new JSONObject(line);
                storedMessages.add(obj.getString("Message"));
            }
        } catch (IOException e) {
            System.out.println("stored messages found.");
        }
    }

    public static String displayLongestMessage() {
        String longest = "";
        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    public static String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                if (i < sentMessages.size()) {
                    return sentMessages.get(i);
                }
            }
        }
        return "Message not found.";
    }

    public static String searchByRecipient(String recipient) {
        StringBuilder results = new StringBuilder();
        for (int i = 0; i < recipientList.size(); i++) {
            if (recipientList.get(i).equals(recipient)) {
                if (i < sentMessages.size()) {
                    results.append(sentMessages.get(i)).append("\n");
                }
            }
        }
        for (String stored : storedMessages) {
            results.append(stored).append("\n");
        }
        if (results.length() == 0) return "No messages found.";
        return results.toString();
    }

    public static String deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(hash)) {
                String deletedMessage = i < sentMessages.size() ? sentMessages.get(i) : "";
                messageHashes.remove(i);
                if (i < messageIDs.size()) messageIDs.remove(i);
                if (i < recipientList.size()) recipientList.remove(i);
                if (i < sentMessages.size()) sentMessages.remove(i);
                return "Message: " + deletedMessage + " successfully deleted.";
            }
        }
        return "Hash not found.";
    }

    public static String printMessagesReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== Message Report ===\n");
        for (int i = 0; i < sentMessages.size(); i++) {
            report.append("Message Hash: ").append(i < messageHashes.size() ? messageHashes.get(i) : "").append("\n");
            report.append("Recipient: ").append(i < recipientList.size() ? recipientList.get(i) : "").append("\n");
            report.append("Message: ").append(sentMessages.get(i)).append("\n");
            report.append("--------------------------------\n");
        }
        return report.toString();
    }

    public static List<String> getSentMessages() { return sentMessages; }
    public static List<String> getStoredMessages() { return storedMessages; }
    public static List<String> getDisregardedMessages() { return disregardedMessages; }
    public static List<String> getMessageHashes() { return messageHashes; }
    public static List<String> getMessageIDs() { return messageIDs; }
    public static List<String> getRecipientList() { return recipientList; }
}

