package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

@Entity
@Table(name = "users", schema = "project")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public static int user_id;
    @Column(name = "username")
    protected String username;
    @Column(name = "password")
    protected String password;
    @Column(name = "card_num")
    protected int card_num;
    @Column(name = "first_name")
    protected String first_name;
    @Column(name = "last_name")
    protected String last_name;
    @Column(name = "logged_in")
    boolean logged_in;

    public User() { }
    public User(String username, String password,int card) throws NoSuchAlgorithmException {
        this.username = username;
        this.password = hashPassword(password);
        this.card_num = card;
        this.logged_in = false;
    }

    public static int getUserId() {
        return user_id;
    }

    public static void setUserId(int userId) {
        user_id = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkPassword(String password) throws NoSuchAlgorithmException {
        return password.equals(hashPassword(this.password));
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = hashPassword(password);;
    }

    private String hashPassword(String plainPassword) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        String salt = getSalt();
        String generatedPassword;
        md.update(salt.getBytes());
        byte[] bytes = md.digest(plainPassword.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }

    private String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA-512");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return Arrays.toString(salt);
    }



    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String name) {
        this.first_name = name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String name) {
        this.last_name = name;
    }

    public int getCardNum(){
        return card_num;
    }

    public void setCardNum(int num){
        this.card_num = num;
    }

    public boolean checkCard(int num){
        return num==card_num;
    }

    public boolean isLoggedIn() {
        return logged_in;
    }

    public void setLoggedIn(boolean log_in) {
        this.logged_in = log_in;
    }

    @Override
    public String toString() {
        return "username: "+username
                +", password: "+password
                +", card number: "+card_num
                +", first name: "+first_name
                +", last name: "+last_name;
    }

}
