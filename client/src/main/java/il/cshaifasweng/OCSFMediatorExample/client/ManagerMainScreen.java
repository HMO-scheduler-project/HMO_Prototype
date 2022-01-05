package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;

public class ManagerMainScreen {

    @FXML
    private Button AverageWaitingTimeRep;

    @FXML
    private Pane menubar;

    @FXML
    private Button numOfNoShowsRep;

    @FXML
    private Button numOfPatientsRep;

    @FXML
    private Label reportsLabel;

    @FXML
    private TextField welcomeTF;

    @FXML
    void pressOnAverageWaitingTimeRep(ActionEvent event) {
    }

    @FXML
    void pressOnNumOfNoShowsRep(ActionEvent event) {
    }

    @FXML
    void pressOnNumberOfPatientsRep(ActionEvent event) {
    }

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        welcomeTF.setText("Welcome " + App.getFirst_name());
    }

}
