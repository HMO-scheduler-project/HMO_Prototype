package il.cshaifasweng.OCSFMediatorExample.client.events;

import il.cshaifasweng.OCSFMediatorExample.entities.MessageToManager;

import java.util.List;

public class MessagesUpdateEvent {
    List<MessageToManager> messagesList;

    public MessagesUpdateEvent(List<MessageToManager> messagesList) {
        this.messagesList = messagesList;
    }

    public List<MessageToManager> getMessagesList() {
        return messagesList;
    }

    public void setMessagesList(List<MessageToManager> messagesList) {
        this.messagesList = messagesList;
    }
}
