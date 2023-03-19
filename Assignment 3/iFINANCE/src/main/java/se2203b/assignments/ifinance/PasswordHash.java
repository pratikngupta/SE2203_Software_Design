package se2203b.assignments.ifinance;

public class PasswordHash {
    // this class is used to hash the password
    // and check if the password matches the hash

    public String hashPassword(String password, String username) {
        int hashed = password.hashCode() + username.hashCode();
        String hash = hashed + "";
        return hash;
    }
}
