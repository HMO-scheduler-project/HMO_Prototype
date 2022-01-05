
package il.cshaifasweng.OCSFMediatorExample.client;

public class stationLoginEvent {
    private String userType;
    private String username;
    private String status;
    private String cardnumber;

    public stationLoginEvent(String userType,String status,String username,String CardNumber) {
        this.userType = userType;
        this.status = status;
        this.username = username;
        this.cardnumber = CardNumber;
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
}

