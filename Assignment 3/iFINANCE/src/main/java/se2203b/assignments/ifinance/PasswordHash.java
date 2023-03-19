package se2203b.assignments.ifinance;
import java.util.Arrays;

public class PasswordHash {
    // this class is used to hash the password
    // and check if the password matches the hash

    public String hashPassword(String password, String username) {
//        int hashed = password.hashCode() + username.hashCode();
//        String hash = hashed + "";
//        return hash;

        //use salt to hash the password with the username as salt

        //generate a random salt
        byte[] salt = new byte[16];

        //add the salt to the password
        String hashedPassword = password + Arrays.toString(salt);

        //hash the password
        for (int i = 0; i < 1000; i++) {
            hashedPassword = hashedPassword.hashCode() + "" ;
        }

        return hashedPassword;
    }
}
