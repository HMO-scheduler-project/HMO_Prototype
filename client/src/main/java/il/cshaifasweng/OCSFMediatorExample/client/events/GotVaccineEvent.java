package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.Covid19VaccineApp;
import il.cshaifasweng.OCSFMediatorExample.entities.InfluenzaVaccineApp;

public class GotVaccineEvent {
    private boolean covid_vaccine;
    private boolean influenza_vaccine;
    private Covid19VaccineApp covid19VaccineApp;
    private InfluenzaVaccineApp influenzaVaccineApp;

    public GotVaccineEvent(boolean covid_vaccine, boolean influenza_vaccine, Covid19VaccineApp covid19VaccineApp, InfluenzaVaccineApp influenzaVaccineApp) {
        this.covid_vaccine = covid_vaccine;
        this.influenza_vaccine = influenza_vaccine;
        this.covid19VaccineApp = covid19VaccineApp;
        this.influenzaVaccineApp = influenzaVaccineApp;
    }

    public Covid19VaccineApp getCovid19VaccineApp() {
        return covid19VaccineApp;
    }

    public void setCovid19VaccineApp(Covid19VaccineApp covid19VaccineApp) {
        this.covid19VaccineApp = covid19VaccineApp;
    }

    public InfluenzaVaccineApp getInfluenzaVaccineApp() {
        return influenzaVaccineApp;
    }

    public void setInfluenzaVaccineApp(InfluenzaVaccineApp influenzaVaccineApp) {
        this.influenzaVaccineApp = influenzaVaccineApp;
    }

    public boolean isCovid_vaccine() {
        return covid_vaccine;
    }

    public void setCovid_vaccine(boolean covid_vaccine) {
        this.covid_vaccine = covid_vaccine;
    }

    public boolean isInfluenza_vaccine() {
        return influenza_vaccine;
    }

    public void setInfluenza_vaccine(boolean influenza_vaccine) {
        this.influenza_vaccine = influenza_vaccine;
    }
}
