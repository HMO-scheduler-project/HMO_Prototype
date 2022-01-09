package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Patient;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.io.IOException;

public class waitingRoomScreenController {

    @FXML
    Pane menubar;
    @FXML
    private Label timeLabel;
    @FXML
    private TextField time;     //show the current time
    @FXML
    private Label nextPatientLabel;
    @FXML
    private TextField nextPatient;  //show the next patient name
    @FXML
    private Label roomLabel;
    @FXML
    private TextField room;         //show the next patient room number

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
//        String cssPath = getClass().getResource("/style.css").toString();
//        menuBarParent.getStylesheets().add(cssPath);
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);

    }

    @Subscribe
    public void showNextPatient (Patient event){
        nextPatientLabel.setText(event.getFullName());
    }

    @Subscribe
    public void showNextRoom (){}


}
