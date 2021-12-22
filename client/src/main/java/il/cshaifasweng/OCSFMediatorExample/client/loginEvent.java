package il.cshaifasweng.OCSFMediatorExample.client;

public class loginEvent {
    private String userType;
    private String username;
    private String status;

    public loginEvent(String userType,String status,String username) {
        this.userType = userType;
        this.status = status;
        this.username = username;
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
