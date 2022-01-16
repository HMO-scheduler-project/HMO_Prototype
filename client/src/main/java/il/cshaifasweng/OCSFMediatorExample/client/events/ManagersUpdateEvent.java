package il.cshaifasweng.OCSFMediatorExample.client.events;

import java.util.List;

public class ManagersUpdateEvent {
    List<String> managersList;

    public ManagersUpdateEvent(List<String> managersList) {
        this.managersList = managersList;
    }

    public List<String> getManagersList() {
        return managersList;
    }

    public void setManagersList(List<String> managersList) {
        this.managersList = managersList;
    }
}
