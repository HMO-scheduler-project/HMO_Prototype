package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

@Entity
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int user_id;
    protected String username;
    protected String password;
    protected int card_num;
    protected String first_name;
    protected String last_name;
    boolean logged_in;

    public User() { }
    public User(String username, String password,int card,String first_name,String last_name) throws NoSuchAlgorithmException {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.card_num = card;
        this.password = hashPassword(password);
        this.logged_in = false;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int userId) {
        user_id = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean checkPassword(String password) throws NoSuchAlgorithmException {
        return this.password.equals(hashPassword(password));
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        this.password = hashPassword(password);
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
        return first_name+card_num;
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
