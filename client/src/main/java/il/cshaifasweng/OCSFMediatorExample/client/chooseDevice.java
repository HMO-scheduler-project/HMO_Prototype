package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * chooseDevice
 * Controller for chooseDevice screen where we can choose between - PC, appointment station and waiting room screen.
 *
 */

public class chooseDevice {

    @FXML
    private Button AppStationBtn;

    @FXML
    private Button pcBtn;

    @FXML
    private Button waitingRoomBtn;

    @FXML
    void goToPCLogin(ActionEvent event) {
        ChangeScreens.changeToLogin();
    }

    @FXML
    void goToStationLogin(ActionEvent event) {
        ChangeScreens.changeToStationChooseClinic();
    }

    @FXML
    void goToWaitingRoomScreen(ActionEvent event) {
        ChangeScreens.changeToWaitingRoomScreen();
    }

    @FXML
    void initialize() {
        assert AppStationBtn != null : "fx:id=\"AppStationBtn\" was not injected: check your FXML file 'chooseDevice.fxml'.";
        assert pcBtn != null : "fx:id=\"pcBtn\" was not injected: check your FXML file 'chooseDevice.fxml'.";
        assert waitingRoomBtn != null : "fx:id=\"waitingroomBtn\" was not injected: check your FXML file 'chooseDevice.fxml'.";

    }

}

