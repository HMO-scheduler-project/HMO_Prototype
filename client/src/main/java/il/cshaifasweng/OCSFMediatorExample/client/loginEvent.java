package il.cshaifasweng.OCSFMediatorExample.client;

public class loginEvent {
    private String userType;
    private String username;
    private String first_name;
    private String status;

    public loginEvent(String userType,String status,String username,String first_name) {
        this.userType = userType;
        this.status = status;
        this.username = username;
        this.first_name = first_name;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
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
