package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.GreenPass;

public class GreenPassEvent {
    protected GreenPass greenPass;

    public GreenPassEvent(GreenPass greenPass) {
        this.greenPass = greenPass;
    }

    public GreenPass getGreenPass() {
        return greenPass;
    }

    public void setGreenPass(GreenPass greenPass) {
        this.greenPass = greenPass;
    }
}
