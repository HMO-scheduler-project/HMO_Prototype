package il.cshaifasweng.OCSFMediatorExample.client;

import antlr.ASTNULLType;
import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Employee;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Patient;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.io.IOException;
import java.util.List;

public class waitingRoomScreenController {

    @FXML
    Pane menubar;
    @FXML
    private static Label timeLabel;
    @FXML
    private static TextField time;             //show the current time
    @FXML
    private static Label nextPatientLabel;
    @FXML
    private static TextField nextPatient;      //show the next patient name
    @FXML
    private static Label roomLabel;
    @FXML
    private static TextField room;             //show the next patient room number

    @Subscribe
    public static void callNextPatient(String patientName, Employee employee) {
        nextPatient.setText(patientName);
        String room_num= Integer.toString(employee.getRoom_num());
        room.setText(room_num);

        nextPatient.setVisible(true);
        room.setVisible(true);
    }

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
//        String cssPath = getClass().getResource("/style.css").toString();
//        menuBarParent.getStylesheets().add(cssPath);
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);

        nextPatient.setVisible(false);
        room.setVisible(false);

    }
    //need to do local real time clock
}
