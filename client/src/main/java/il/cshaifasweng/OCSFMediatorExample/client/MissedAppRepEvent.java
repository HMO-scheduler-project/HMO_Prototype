package il.cshaifasweng.OCSFMediatorExample.client;

import Reports.MissedAppRep;

public class MissedAppRepEvent {
   private MissedAppRep missedAppRep;

    public MissedAppRepEvent(MissedAppRep missedAppRep) {
        this.missedAppRep = missedAppRep;
    }

    public MissedAppRepEvent() {
    }

    public MissedAppRep getMissingAppRep() {
        return missedAppRep;
    }

    public void setMissedAppRep(MissedAppRep missingAppRep) {
        this.missedAppRep = missedAppRep;
    }
}
