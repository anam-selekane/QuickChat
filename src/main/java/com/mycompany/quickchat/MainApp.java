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

public class MainApp{
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Create Login object
        Login login = new Login();

        System.out.println("=== USER REGISTRATION ===");
        
        System.out.println("Enter your firstname:");
        String firstname;
        firstname = input.nextLine();
        
        System.out.println("Enter your lastname:");
        String lastname;
        lastname = input.nextLine();
        
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

        // Register user
        String response = login.registerUser(username, password, phone);
        System.out.println(response);

        // ===== LOGIN =====
        System.out.println("\n=== USER LOGIN ===");

        System.out.print("Enter username: ");
        String loginUsername = input.nextLine();

        System.out.print("Enter password: ");
        String loginPassword = input.nextLine();

        boolean loggedIn = login.loginUser(loginUsername, loginPassword);

        System.out.println(login.returnLoginStatus(loggedIn));

        // ===== PART 2 =====
        if (loggedIn) {
            System.out.println("\n=== WELCOME TO QUICKCHAT ===");

            boolean running = true;

            while (running) {
                System.out.println("\n== CHAT MENU ==");
                System.out.println("1. Send messages");
                System.out.println("2. Show recently sent messages");
                System.out.println("3. Quit");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("How many messages: ");
                        int numMessages = input.nextInt();
                        input.nextLine();

                        for (int i = 0; i < numMessages; i++) {
                            int messageNumber = i + 1;

                            System.out.print("Enter recipient number: ");
                            String recipient = input.nextLine();

                            System.out.print("Enter message: ");
                           
                        }
                        break;

                    case 2:
                        System.out.println("Coming soon...");
                        break;

                    case 3:
                        System.out.println("Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            }

        } else {
            System.out.println("Login failed.");
        }

        input.close();
    }
}