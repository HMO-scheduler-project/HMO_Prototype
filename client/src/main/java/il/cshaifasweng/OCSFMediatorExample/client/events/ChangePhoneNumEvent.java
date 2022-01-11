package il.cshaifasweng.OCSFMediatorExample.client.events;

public class ChangePhoneNumEvent {
    private String phone_num;

    public ChangePhoneNumEvent(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getPhoneNum() {
        return phone_num;
    }

    public void setPhoneNum(String phone_num) {
        this.phone_num = phone_num;
    }
}
