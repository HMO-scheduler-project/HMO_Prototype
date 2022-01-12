package il.cshaifasweng.OCSFMediatorExample.client.events;

import java.util.ArrayList;
import java.util.List;

public class ServiceListUpdateEvent {
    private List<String> serviceNames;

    public ServiceListUpdateEvent(List<String> servicesNames) {
        this.serviceNames = servicesNames;
    }

    public List<String> getServiceNames() {
        return serviceNames;
    }

    public void setServiceNames(ArrayList<String> serviceNames) {
        this.serviceNames = serviceNames;
    }
}
