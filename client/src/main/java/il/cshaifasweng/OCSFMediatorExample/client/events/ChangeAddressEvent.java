package il.cshaifasweng.OCSFMediatorExample.client.events;

public class ChangeAddressEvent {
    private String address;

    public ChangeAddressEvent(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
