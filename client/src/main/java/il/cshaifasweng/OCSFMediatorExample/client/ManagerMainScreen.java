package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import il.cshaifasweng.OCSFMediatorExample.client.events.MessagesUpdateEvent;
import il.cshaifasweng.OCSFMediatorExample.client.events.showMessageEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.MessageToManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * ManagerMainPage
 * Contains Hello message and table to display messages list from employees to manager.
 * After choosing a message display the content of the message in a text area.
 *
 */

public class ManagerMainScreen {

    @FXML
    private Pane menubar;

    @FXML
    private TextField welcomeTF;

    @FXML
    private Label MessageLabel;

    @FXML
    private TableView<MessageToManager> MessagesTable;

    @FXML
    private TableColumn<MessageToManager, String> fromCol;

    @FXML
    private TableColumn<MessageToManager, String> titleCol;

    @FXML
    private TextArea MessageTF;


    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        welcomeTF.setText("Welcome " + App.getFirst_name());
        try {
            Message msg= new Message();
            msg.setUsername(App.getUsername());
            msg.setAction("GetAllUnreadMessages");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onMessagesUpdateEvent(MessagesUpdateEvent event) {
        fromCol.setCellValueFactory(new PropertyValueFactory<>("from"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        MessagesTable.setItems(FXCollections.observableList(event.getMessagesList()));
    }

    @FXML
    public void viewChosenMessage(ActionEvent event) {
        if (MessagesTable.getSelectionModel().getSelectedItems() != null && MessagesTable.getSelectionModel().getSelectedIndex() != -1) {
            Message msg = new Message();
            msg.setMessageToManager(MessagesTable.getSelectionModel().getSelectedItem());
            msg.setAction("show message");
            try {
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            showAlert("Error", "Please choose a message first");
        }
    }

    @Subscribe
    public void onShowMessageEvent(showMessageEvent event) {
        MessageTF.setText(event.getMessage());
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

}
