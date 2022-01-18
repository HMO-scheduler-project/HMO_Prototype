package il.cshaifasweng.OCSFMediatorExample.server.ocsf;


public class clinicClient {
    private String clinic_name;
    private ConnectionToClient client;

    public clinicClient(String clinic_name, ConnectionToClient client) {
        this.clinic_name = clinic_name;
        this.client = client;
    }
    public String getClinic_name() {
        return clinic_name;
    }
    public void setClinic_name(String clinic_name) {
        this.clinic_name = clinic_name;
    }
    public ConnectionToClient getClient() {
        return client;
    }
    public void setClient(ConnectionToClient client) {
        this.client = client;
    }
}
