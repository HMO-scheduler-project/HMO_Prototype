package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

/**
 * MenuBarController
 * Contains the menubar that all screens in PC are using.
 *
 */

public class MenuBarController {
    @FXML
    private MenuItem mainPageBtn;
    @FXML
    private Menu ClinicsBtn;
    @FXML
    private MenuItem OpeningHoursBtn;
    @FXML
    private MenuItem contactInfoBtn;
    @FXML
    private Menu appointments;
    @FXML
    private MenuItem newAppBtn;
    @FXML
    private Menu reports;
    @FXML
    private MenuItem reportsBtn;
    @FXML
    private MenuItem viewAppsBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Menu updates;
    @FXML
    private MenuItem UpdateBtn;
    @FXML
    private MenuItem greenPassbtn;
    @FXML
    private Menu greenpass;


    @FXML
    void pressContactInfoBtn(ActionEvent event) {
        ChangeScreens.changeToContactInfoScreen();
    }
    @FXML
    void pressMainPageBtn(ActionEvent event) { ChangeScreens.changeToMainPage();}
    @FXML
    void pressNewAppBtn(ActionEvent event) { ChangeScreens.changeNewAppScreen();}

    @FXML
    void pressViewAppsBtn(ActionEvent event) {
        ChangeScreens.changeToViewAppsScreen();
    }
    @FXML
    void pressOnLogout(ActionEvent event){
        App.logout(true,"PC");
    }

    @FXML
    void pressOpeningHoursBtn(ActionEvent event) {
        ChangeScreens.changeToOpeningHoursScreen();
    }

    @FXML
    void pressReportsBtn(ActionEvent event){
        ChangeScreens.changeToReportsScreen();
    }

    @FXML
    void pressHoursUpdate(ActionEvent event){
        ChangeScreens.changeToUpdateHoursScreen();
    }


    public void initialize() {
        if(App.getUserType().equals("Manager") || App.getUserType().equals("HMO_Manager")) {
            reports.setVisible(true);
            updates.setVisible(true);
        }
        appointments.setVisible(App.getUserType().equals("Patient"));
        greenpass.setVisible(App.getUserType().equals("Patient"));
    }
    @FXML
    void pressGreenPass(ActionEvent event) {
        ChangeScreens.changeToGreenPassScreen();
    }

}
