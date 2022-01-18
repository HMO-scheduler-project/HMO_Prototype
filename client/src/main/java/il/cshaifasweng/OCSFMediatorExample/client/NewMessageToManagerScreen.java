package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.ManagedClinicListUpdateEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.ManagersUpdateEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.RemoveAppEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.sentMessageEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.MessageToManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class NewMessageToManagerScreen {

    @FXML
    private Pane menubar;

    @FXML
    private TextArea bodyTF;

    @FXML
    private Button sendBtn;

    @FXML
    private TextField titleTF;

    @FXML
    private ComboBox<String> chooseManager;

    @FXML
    void SendMessage(ActionEvent event) {
        if (chooseManager.getSelectionModel().getSelectedItem() != null) {
            Message msg = new Message();
            MessageToManager message = new MessageToManager(null,chooseManager.getSelectionModel().getSelectedItem(),titleTF.getText(),bodyTF.getText());
            msg.setMessageToManager(message);
            msg.setUsername(App.getUsername());
            msg.setAction("send message");
            try {
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            showAlert("Error", "Please choose manager first");
    }

    @Subscribe
    public void onSentMessageEvent(sentMessageEvent event) {
        if (event.isSent()) {
            showAlert("Success", "The message was sent successfully!");
        } else {
            showAlert("Error", "This message wasn't sent. Please try again!");
        }
    }


    public void showAlert(String title, String head) {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(null);
                alert.setContentText(head);
                alert.show();
            }
        });
    }

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        Message msg = new Message();
        msg.setAction("pull managers");
        try {
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onManagersUpdateEvent(ManagersUpdateEvent event) {
        for (String manager : event.getManagersList()) {
            if(!chooseManager.getItems().contains(manager)) {
                chooseManager.getItems().add(manager);
            }
        }
    }

}
