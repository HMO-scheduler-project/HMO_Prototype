package il.cshaifasweng.OCSFMediatorExample.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * HostPortController
 * Contains textField that receives desired host and port from input in order to connect to desired server.
 *
 */

public class HostPortController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Hosttxt;

    @FXML
    private TextField Porttxt;

    @FXML
    private Button connectBtn;


    @FXML
    void pressOnConnect(ActionEvent event) {
    if(Hosttxt.getText()!=null && Porttxt.getText()!=null && !Hosttxt.getText().equals("") && !Porttxt.getText().equals("")) {
        App.setHost(Hosttxt.getText());
        App.setPort(Integer.parseInt(Porttxt.getText()));
        SimpleClient client=SimpleClient.getClient();
        ChangeScreens.changeToChooseDeviceScreen();
    }
    else {
    if (Hosttxt.getText()==null || Hosttxt.getText().equals(""))
        showAlert("Error","Please enter the host to connect to server");

    else {
    if (Porttxt.getText()==null || Porttxt.getText().equals(""))
        showAlert("Error","Please enter the port to connect to server");}}}
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
    void initialize() {

    }

}
