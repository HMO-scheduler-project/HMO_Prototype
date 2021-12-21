package il.cshaifasweng.OCSFMediatorExample.client;

public class showContactInfoEvent {
    private String address;
    private String phone_num;

    public showContactInfoEvent(String address,String phone_num) {
        this.address = address;
        this.phone_num = phone_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phone_num;
    }

    public void setPhoneNum(String phone_num) {
        this.phone_num = phone_num;
    }
}
