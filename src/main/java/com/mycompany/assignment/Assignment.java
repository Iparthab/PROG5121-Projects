package com.mycompany.assignment;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assignment {
    
    public static class Login {
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private String cellPhoneNumber;
        
        public Login(String firstName, String lastName, String username, String password, String cellPhoneNumber) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
            this.cellPhoneNumber = cellPhoneNumber;
        }
        
        public boolean checkUserName() {
            return username.contains("_") && username.length() <= 5;
        }
        
        public boolean checkPasswordComplexity() {
            boolean lengthCheck = password.length() >= 8;
            boolean uppercaseCheck = false;
            boolean numberCheck = false;
            boolean specialCharCheck = false;
            
            for(int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);
                if (Character.isUpperCase(ch)) uppercaseCheck = true;
                else if (Character.isDigit(ch)) numberCheck = true;
                else if (!Character.isLetterOrDigit(ch)) specialCharCheck = true;
            }
            return lengthCheck && uppercaseCheck && numberCheck && specialCharCheck;
        }
        
        public boolean checkCellPhoneNumber() { 
            String regex =  "^\\+27\\d{9}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(this.cellPhoneNumber);
            return matcher.matches();
            
        }
        
        public String registerUser() {
            if(!checkUserName()) {
                return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
            }
            if (!checkPasswordComplexity()) { 
                return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
            }
            return "Username and password successfully capturerd.";
        }
        
        public String checkCellPhoneRegistration() {
            if (checkCellPhoneNumber()) {
                return "Cell phone number successfully added.";
            } else{
                return "Cell phone number incorrectly formatted or does not contain international code.";
            }
        }
        
        public boolean loginUser(String enteredUsername, String enteredPassword) {
            return this.username.equals(enteredUsername) && this.password.equals(enteredPassword);
        }
        
        public String returnLoginStatus(boolean isLoggedIn) {
            if (isLoggedIn) {
                return "Welcome " + firstName + "," + lastName + " it is great to see you again.";
            } else {
                return "Username or password incorrect, please try again.";
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- Register a New Account ---");
        System.out.print("Enter First Name: ");
        String fName = scanner.nextLine();
        
        
        System.out.print("Enter Last Name: ");
        String lName = scanner.nextLine();
        
        
        System.out.print("Enter a Username: ");
        String uName = scanner.nextLine();
        
        System.out.print("Enter a Password: ");
        String pass = scanner.nextLine();
        
        System.out.print("Enter a South African Cell Number (e.g. +27...");
        String cell = scanner.nextLine();
        
        Login userLogin = new Login(fName, lName, uName, pass, cell);
        
        System.out.println("\n--- Registration Status ---");
        System.out.println(userLogin.registerUser());
        System.out.println(userLogin.checkCellPhoneRegistration());
        
        if (userLogin.checkUserName() && userLogin.checkPasswordComplexity() && userLogin.checkCellPhoneNumber() ) {
           System.out.println("\n--- Login ---");
           System.out.print("Enter Username: ");
           String loginUser = scanner.nextLine(); 
           System.out.print("Enter Password: ");
           String loginPass =scanner.nextLine();
       
           
           boolean isLoggedIn = userLogin.loginUser(loginUser, loginPass);
           System.out.println(userLogin.returnLoginStatus(isLoggedIn));
        } else{
            System.out.println("\nRegistration failed. Please restart the application and fix formatting errors.");
        }
        scanner.close();
        
    }
}