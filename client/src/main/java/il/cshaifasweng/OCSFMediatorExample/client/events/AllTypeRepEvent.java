package il.cshaifasweng.OCSFMediatorExample.client.events;

import Reports.AwaitingTimeRep;
import Reports.MissedAppRep;
import Reports.ServicesTypeRep;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;

import java.util.List;

public class AllTypeRepEvent {
    private List<AwaitingTimeRep>  awaitingTimeRep;
    private List<ServicesTypeRep>  servicesTypeRep;
    private List<MissedAppRep>     missedAppRep;

    public AllTypeRepEvent(List<AwaitingTimeRep> awaitingTimeRep, List<ServicesTypeRep> servicesTypeRep, List<MissedAppRep> missedAppRep) {
        this.awaitingTimeRep = awaitingTimeRep;
        this.servicesTypeRep = servicesTypeRep;
        this.missedAppRep = missedAppRep;
    }

    public List<AwaitingTimeRep> getAwaitingTimeRep() {
        return awaitingTimeRep;
    }

    public void setAwaitingTimeRep(List<AwaitingTimeRep> awaitingTimeRep) {
        this.awaitingTimeRep = awaitingTimeRep;
    }

    public List<ServicesTypeRep> getServicesTypeRep() {
        return servicesTypeRep;
    }

    public void setServicesTypeRep(List<ServicesTypeRep> servicesTypeRep) {
        this.servicesTypeRep = servicesTypeRep;
    }

    public List<MissedAppRep> getMissedAppRep() {
        return missedAppRep;
    }

    public void setMissedAppRep(List<MissedAppRep> missedAppRep) {
        this.missedAppRep = missedAppRep;
    }
}
