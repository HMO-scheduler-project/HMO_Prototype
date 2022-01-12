package il.cshaifasweng.OCSFMediatorExample.client;
public class QuestionnaireEvent {
    private boolean saved;
    public QuestionnaireEvent(boolean saved) {
        this.saved=saved;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }
}
