package il.cshaifasweng.OCSFMediatorExample.client.events;

public class GotVaccineEvent {
    private boolean covid_vaccine;
    private boolean influenza_vaccine;

    public GotVaccineEvent(boolean covid_vaccine, boolean influenza_vaccine) {
        this.covid_vaccine = covid_vaccine;
        this.influenza_vaccine = influenza_vaccine;
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
