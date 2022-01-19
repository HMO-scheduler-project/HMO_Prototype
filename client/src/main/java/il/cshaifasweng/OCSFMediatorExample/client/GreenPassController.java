package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.GreenPassEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.GreenPass;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * GreenPassController
 * Display a green pass if a patient is vaccinated to covid or display a message that patient isn't vaccinated and switch
 * the screen to schedule appointment to covid vaccine screen so he can schedule appointment.
 *
 */

public class GreenPassController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane gridPane;

    @FXML
    private ImageView image;

    @FXML
    private Pane Menubar;
    @FXML
    private TextField UserId;

    @FXML
    private TextField expireDate;

    @FXML
    private TextField greenPassId;

    @FXML
    private TextField issueDate;

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        Menubar.getChildren().clear();
        Menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        greenPassId.setVisible(false);
        UserId.setVisible(false);
        issueDate.setVisible(false);
        expireDate.setVisible(false);
        gridPane.setVisible(false);

        try {
            Message msg = new Message();
            msg.setUsername(App.getUsername());
            msg.setAction("Get green pass");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Subscribe
    public void GotGreenPassEvent(GreenPassEvent event)
    {
        if (event.getGreenPass()==null)
        {
            showAlert("Error","You have to be vaccinated to get a green pass");
            ChangeScreens.changeToVaccineAppScreen();
        }
        else {
            GreenPass greenPass=event.getGreenPass();
            greenPassId.setText(String.valueOf(greenPass.getGreenpass_id()));
            UserId.setText(String.valueOf(greenPass.getUser().user_id));
            issueDate.setText(greenPass.getIssue_date().toString());
            expireDate.setText(greenPass.getExpiration_date().toString());
            greenPassId.setEditable(false);
            UserId.setEditable(false);
            issueDate.setEditable(false);
            expireDate.setEditable(false);
            gridPane.setVisible(true);
            greenPassId.setVisible(true);
            UserId.setVisible(true);
            issueDate.setVisible(true);
            expireDate.setVisible(true);
            issueDate.setEditable(false);
        }
    }
    public void showAlert(String title, String head) {
        Platform.runLater(new Runnable() {
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                try {
                    alert.setTitle(title);
                    alert.setHeaderText(null);
                    alert.setContentText(head);
                    alert.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
