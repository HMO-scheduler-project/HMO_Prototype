
package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.User;

public class stationLoginEvent {
    private String userType;
    private String username;
    private String status;
    private String cardnumber;
    private String first_name;

    public stationLoginEvent(String status) {
        this.status = status;
    }

    public stationLoginEvent(String userType, String status, User user) {
        this.userType = userType;
        this.status = status;
        this.username = user.getUsername();
        this.cardnumber = user.getCardNum();
        this.first_name = user.getFirstName();
    }
    public String getCardnumber() {
        return cardnumber;
    }
    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}

