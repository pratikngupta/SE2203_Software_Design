package se2203b.assignments.ifinance;

import java.util.Arrays;

public class PasswordHash {
    // this class is used to hash the password
    // and check if the password matches the hash

    public String hashPassword(String password, String username) {

        //generate a random salt
        byte[] salt = new byte[32];

        //System.out.println("salt: " + Arrays.toString(salt));

        //add the salt and username to the password
        String hashedPassword = password + Arrays.toString(salt) + username;

        //System.out.println("hashedPassword: " + hashedPassword);

        //get first character of the username
        char firstChar = username.charAt(0);
        //get first character of the password
        char firstCharPassword = password.charAt(0);

        //get the ascii value of the first character
        int asciiValueChar = firstChar;

        //get the ascii value of the first character
        int asciiValueCharPassword = firstCharPassword;

        //add both ascii values
        int asciiValue = asciiValueChar + asciiValueCharPassword;

        //hash the password
        for (int i = asciiValue; i < 1000; i++) {
            hashedPassword = hashedPassword.hashCode() + "";
            //System.out.println("hashedPassword: " + hashedPassword);
        }

        return hashedPassword;
    }
}
