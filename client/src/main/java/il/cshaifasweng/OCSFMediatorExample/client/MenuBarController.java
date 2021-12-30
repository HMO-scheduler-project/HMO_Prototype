package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {
    @FXML
    private MenuItem ChangeAppBtn;
    @FXML
    private Menu MainPageBtn;
    @FXML
    private Menu ClinicsBtn;
    @FXML
    private MenuItem OpenningHoursBtn;
    @FXML
    private MenuItem contactInfoBtn;
    @FXML
    private MenuItem newAppBtn;
    @FXML
    private MenuItem scheduledAppBtn;

    @FXML
    void pressChangeAppBtn(ActionEvent event) {}
    @FXML
    void pressContactInfoBtn(ActionEvent event) throws IOException {
        ChangeScreens.changeToContactInfoScreen();
    }
    @FXML
    void pressMainPageBtn(ActionEvent event) {}
    @FXML
    void pressNewAppBtn(ActionEvent event) {}

    @FXML
    void pressScheduledAppBtn(ActionEvent event) {}
    @FXML
    void pressOnLogout(ActionEvent event){
        App.logout(true);
    }

    @FXML
    void pressOpenningHoursBtn(ActionEvent event) throws IOException {
        ChangeScreens.changeToOpenningHoursScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        EventBus.getDefault().register(this);
    }

}
