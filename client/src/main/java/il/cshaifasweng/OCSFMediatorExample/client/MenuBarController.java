package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.io.IOException;


public class MenuBarController {
    @FXML
    private MenuItem ChangeAppBtn;
    @FXML
    private Menu MainPageBtn;
    @FXML
    private Menu ClinicsBtn;
    @FXML
    private MenuItem OpeningHoursBtn;
    @FXML
    private MenuItem contactInfoBtn;
    @FXML
    private MenuItem newAppBtn;
    @FXML
    private Menu reportsBtn;
    @FXML
    private MenuItem CancelAppBtn;
    @FXML
    private MenuItem viewAppsBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Menu UpdateBtn;

    @FXML
    void pressChangeAppBtn(ActionEvent event) {}
    @FXML
    void pressCancelAppBtn(ActionEvent event) {}
    @FXML
    void pressContactInfoBtn(ActionEvent event) {
        ChangeScreens.changeToContactInfoScreen();
    }
    @FXML
    void pressMainPageBtn(ActionEvent event) { ChangeScreens.changeToMainPage();}
    @FXML
    void pressNewAppBtn(ActionEvent event) { ChangeScreens.changeNewAppScreen();}

    @FXML
    void pressViewAppsBtn(ActionEvent event) {}
    @FXML
    void pressOnLogout(ActionEvent event){
        App.logout(true);
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
        reportsBtn.setVisible(App.getUserType().equals("Manager"));
        UpdateBtn.setVisible(App.getUserType().equals("Manager"));
    }

}