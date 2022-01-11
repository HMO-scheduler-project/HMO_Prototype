package il.cshaifasweng.OCSFMediatorExample.client.events;

import Reports.ServicesTypeRep;

public class ServicesTypeRepEvent {
  private  ServicesTypeRep servicesTypeRep;

    public ServicesTypeRepEvent(ServicesTypeRep servicesTypeRep) {
        this.servicesTypeRep = servicesTypeRep;
    }

    public ServicesTypeRepEvent() {
    }

    public ServicesTypeRep getServicesTypeRep() {
        return servicesTypeRep;
    }

    public void setServicesTypeRep(ServicesTypeRep servicesTypeRep) {
        this.servicesTypeRep = servicesTypeRep;
    }
}
