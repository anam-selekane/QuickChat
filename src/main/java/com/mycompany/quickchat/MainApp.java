/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

import java.util.Scanner;

/**
 *
 * @author asele
 */
public class MainApp {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== USER REGISTRATION ===");
        System.out.print("Enter your firstname: ");
        String firstname = input.nextLine();
        System.out.print("Enter your lastname: ");
        String lastname = input.nextLine();

        String username;
        while (true) {
            System.out.print("Enter a username: ");
            username = input.nextLine();
            if (login.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Invalid username. Must contain '_' and be <= 5 characters.");
            }
        }

        String password;
        while (true) {
            System.out.print("Enter a password: ");
            password = input.nextLine();
            if (login.checkPasswordComplexity(password)) {
                System.out.println("Password captured successfully.");
                break;
            } else {
                System.out.println("Password must be 8+ chars, include capital, number, and special char.");
            }
        }

        String phone;
        while (true) {
            System.out.print("Enter SA phone (+27...): ");
            phone = input.nextLine();
            if (login.checkCellPhoneNumber(phone)) {
                System.out.println("Phone number captured successfully.");
                break;
            } else {
                System.out.println("Invalid phone number format.");
            }
        }

        String response = login.registerUser(firstname, lastname, username, password, phone);
        System.out.println(response);

        // ===== LOGIN =====
        System.out.println("\n=== USER LOGIN ===");
        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();
        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        System.out.println(login.returnLoginStatus(loggedIn));

        if (loggedIn) {
            System.out.println("\n=== WELCOME TO QUICKCHAT ===");
            boolean running = true;
            while (running) {
                System.out.println("\n== CHAT MENU ==");
                System.out.println("1. Send messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3.Quit");
                System.out.println("4.Stored messages");
                System.out.print("Choose an option: ");
                String choiceLine = input.nextLine();
                int choice;
                try {
                    choice = Integer.parseInt(choiceLine);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid option. Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.print("How many messages: ");
                        int numMessages;
                        try {
                            numMessages = Integer.parseInt(input.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid number. Returning to menu.");
                            break;
                        }

                        for (int i = 0; i < numMessages; i++) {
                            System.out.println("=== Message " + (i + 1) + " ===");
                            System.out.print("Enter recipient number: ");
                            String recipient = input.nextLine();
                            System.out.print("Enter message: ");
                            String messageTextInput = input.nextLine();

                            Message msg = new Message();
                            msg.generateMessageID();
                            msg.setNumMessages(i + 1);
                            msg.setRecipient(recipient);
                            msg.setMessageText(messageTextInput);

                            System.out.println(msg.checkRecipientCell(recipient));
                            System.out.println(msg.checkMessageLength(messageTextInput));
                            if (messageTextInput.length() > 250) {
                                System.out.println("You have exceeded your 250 characters");
                                continue;
                            }
                            System.out.println("Message ready to send");
                            String result = msg.sentMessage();
                            System.out.println(result);
                            System.out.println(msg.printMessage());
                        }
                        break;
                        
                    case 2:
                        System.out.println("Coming Soon");
                        
                        break;
                    case 3:
                        System.out.println("Exiting Quickchat");
                        running = false;
                        break;
                       
                    case 4:
                        System.out.println("4. Stored Messages");
                        System.out.println("a) Display all stored messages");
                        System.out.println("b) Display longest Message");
                        System.out.println("c) Search by messageID");
                        System.out.println("d) Search by recipient");
                        System.out.println("e) Delete by Message Hash");
                        System.out.println("f) Display full report");

                        String rawChoice = input.hasNextLine() ? input.nextLine().trim().toLowerCase() : "";
                        char subChoice = rawChoice.isEmpty() ? '\0' : rawChoice.charAt(0);

                        switch (subChoice) {

                            case 'a':
                                System.out.println("a) Display all stored messages");
                                Message.loadStoredMessages();
                                for (String s : Message.getStoredMessages()) {
                                    System.out.println(s);
                                }
                                break;
                            case 'b':
                                System.out.println("b) Display longest Message");
                                System.out.println(Message.displayLongestMessage());
                                break;
                            case 'c':
                                System.out.println("c) Search by messageID");
                                String id = input.nextLine();
                                System.out.println(Message.searchByMessageID(id));
                                break;
                            case 'd':
                                System.out.println("d) Search by recipient");
                                String recipientSearch = input.nextLine();
                                System.out.println(Message.searchByRecipient(recipientSearch));
                                break;
                            case 'e':
                                System.out.println("e) Delete by Message Hash");
                                String hash = input.nextLine();
                                System.out.println(Message.deleteByHash(hash));
                                break;
                            case 'f':
                                System.out.println("f) Display full report");
                                System.out.println(Message.printMessagesReport());
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again");
                                break;
                        }
               
                
            }
            }
            
            } else {
        
            System.out.println("Login failed.");
        }

        input.close();
        }
    }


    




