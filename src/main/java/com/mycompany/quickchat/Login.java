/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quickchat;

/**
 *
 * @author asele
 */
public class Login {
    
    
    //This variables are used to store the user's details
    //Once the user registers, their data is saved here.
    //----------------------------------
    String username;
    String password;
    String phoneNumber;
    
    
    //-----------------------------------------------------
    //1. USERNAME
    // required to ensure the following:
    //-Username must not contain more that 5 characters long 
    //-Username must contain an underscore "_"
    //------------------------------------------------------------------------
    public boolean checkUserName(String username)   {
        
       //username.length() <= 5 ensure that the username is short
       //username.contains("_") check the username that it contains an underscore
       return username.length() <= 5 && username.contains ("_");
       
    }
       
       
       //=======================================================================
       //2. PASSWORD
       // required to ensure the following: 
       // Passowrd must be atleast an 8 characters long
       //Password must contain at least 1 capital letter
       //Password must contain at least 1 number
       //Password must at least contain 1 special character (!,@,#,%,*, etc)
       //------------------------------------------------------------------
       
       public boolean checkPasswordComplexity(String password)  {
           //Declear booleans
           boolean hasCapital = false;
           boolean hasNumber = false;
           boolean hasSpecial = false;
           
           //Loop through each character in the password 
           for (int i = 0 ;i < password.length(); i++) {
               char c = password.charAt(i); // check if they used the correct character
               if (Character.isUpperCase(c)) {
                   hasCapital = true; // look for the capital letter at the beginning
               } else if (Character.isDigit(c)){
                   hasNumber = true; //look for a number
               } else if (!Character.isLetterOrDigit(c)) {
                   hasSpecial = true; //look for a special character 
               }
           }
           // All conditions in the password must be True to return true.
           return password.length() >=8 && hasCapital && hasNumber && hasSpecial;
           }
       
       
       
       //========================================================================
       //3.PhoneNumber
       // required to ensure the following:
       //Phone number must start with +27
       //Phone number must not be more than 12 characters long
       
       //This is because:
       //+27 = international code for South Africa
       //9 digits follow after +27(normal cell number)
       //-----------------------------------------------------------------------
       public boolean checkCellPhoneNumber(String phone){
           return phone.startsWith("+27") && phone.length()<= 12;
      //The phone number must be a registered number in South Africa that starts with +27 and tham 9 letters after
       }
       
       
     //=========================================================================
       //4.Register user method
       //required to check the following:
       //Checks the username
       //Check the password 
       //Check the phone number 
       //Stores the data if everything is correct
       //Returns specific messsages
       //-----------------------------------------------------------------------
       public String registerUser(String username,String password,String phoneNumber) {
           
           if (!checkUserName(username)){
          return "Username is not correctly formatted;please ensure that the paasword contains an underscore and is no more than 5 characters in length.";
           }
           if(!checkPasswordComplexity(password)){
           return "Password is not correctly formatted; please ensure that the password contains at least eight characters,a capital letter,a number,and a special character.";
           }
           if(!checkCellPhoneNumber(phoneNumber)){
           return "Cell phone number incorrectly formatted or does not contain international code.";
           }  
           //The program can only accept them when they log in the correct details
           
           this.username=username;
           this.password=password;
           this.phoneNumber=phoneNumber;
           
           //When they all return true,print out the feedback
           
           return"User registered successfully.";
       }
           
           //The program must allow the user to log in using the same details they used to register with 
   
    public boolean loginUser(String username,String password)  {
               return this.username.equals(username)&& this.password.equals(password);
           }
               
        public String returnLoginStatus(boolean success) {
             if(success){
                 return "Welcome"+username +" it is nice to see you again.";
            } else {
                 return "Username or password incorrect, please try again.";
            }
       }
}
