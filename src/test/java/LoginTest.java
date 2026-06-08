/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.mycompany.quickchat.Login;

/**
 *
 * @author asele
 */
public class LoginTest {
    
    
    //create a test object that i will use to test the login.
    Login login = new Login(); 
    
    //First gonna test username.
    @Test
    public void testValidUsername() {
        //This checks that the username has an underscore
        //checks that it contains 5 characters length
        assertTrue(login.checkUserName("kyl_1"));
       
    }
    
    @Test
    public void testInvalidUsername_NoUnderscore() {
        //To check wherther the username has no underscore
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }
    
    @Test
    public void testInvalidUsername_TooLong() {
        //Check wherther the username contains more than 5 characters
        assertFalse(login.checkUserName("kyl_e1"));
        
    }
    
    //Gonna test Password
    @Test
    public void testValidPassword() {
        //check wherther the password contains 8 characters long 
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        
    }
    
    @Test
    public void testInvalidPassword_NoSpecial()  {
        //check wherther the password has no special characters
        assertFalse(login.checkPasswordComplexity("Chsecke99"));
   
    }
    
    @Test
    public void testInvalidPassword_NoCapitalLetter()  {
            //If the password has no capital lettter
            assertFalse(login.checkPasswordComplexity("ch&&sec@ke99!"));
    }
    
    //Gonna test phone number 
    //Testing if it is has the South Africa code
    @Test
    public void testValidPhoneNumber(){
        assertTrue(login.checkCellPhoneNumber("+2783896897"));
     }
    
    
    //Testing that it contains +27
    @Test
    public void testInvalidPhoneNumber() {
        assertFalse(login.checkCellPhoneNumber("083896897"));
    }
    
    @Test
    public void testRegisterUser_Success() {
       String result = login.registerUser("Anam","Selekane","kyl_1","Ch&&@ke99!", "+2796489328");
       assertEquals("User registered successfully",result);
    }

     @Test
     public void testRegisterUser_InvalidUsername()  {
         String result = login.registerUser("Anam","Selekane","ky11","Ch&&@ke99!","+27796489328");
         assertTrue(result.contains("Username is not correctly formatted"));
     }
     
     
     @Test
     public void testRegisterUser_InvalidPassword()  {
         String result = login.registerUser("Anam","Selekane","kyl_1","pass","+27796489328");
         assertTrue(result.contains("Password is not correctly formatted"));
     }
     
     @Test
     public void testRegisterUser_InvalidPhone() {
         String result = login.registerUser("Anam","Selekane","kyl_1","Ch&&@ke99!","0796489328");
         assertTrue(result.contains("Cell phone number incorrectly formatted"));
     }

    @Test
    public void testLoginSuccess()  {
        // Registering with correct details
        login.registerUser("Anam","Selekane","kyl_1","Ch&&sec@ke99!","+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    
    // Testing log in fail
    @Test
    public void testLoginFail() {
        
        login.registerUser("kyle!!!!!!!", "ch&&sec@ke99!", "+27838968976");
        
        // entering wrong details
        assertFalse(login.loginUser("kyle!!!!!!!", "password"));
    }
}
    



