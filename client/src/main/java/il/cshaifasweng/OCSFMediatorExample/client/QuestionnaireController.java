package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.QuestionnaireEvent;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

/**
 * QuestionnaireController
 * Displays questionnaire for covid symptoms.
 * After filling the questionnaire switches to schedule new covid test appointment screen.
 *
 */

public class QuestionnaireController {

    @FXML
    private Pane Menubar;

    @FXML
    private ChoiceBox<String> cough;

    @FXML
    private ChoiceBox<String> fever;

    @FXML
    private ChoiceBox<String> metPatient;


    @FXML
    private Label questionnaireLabel;

    @FXML
    private Label questionnaireLabel1;

    @FXML
    private Label questionnaireLabel2;

    @FXML
    private Label questionnaireLabel3;

    @FXML
    private Label questionnaireLabel4;

    @FXML
    private Label questionnaireLabel5;

    @FXML
    private Label questionnaireLabel6;

    @FXML
    private Button saveQuestionnaire;

    @FXML
    private ChoiceBox<String> smell;

    @FXML
    private ChoiceBox<String> taste;

    @FXML
    private ChoiceBox<String> tired;

    @FXML
    private Label warningtext;

    @FXML
    void QuestionnaireSave(ActionEvent event) {
        if (smell.getSelectionModel().getSelectedItem()!= null && fever.getSelectionModel().getSelectedItem()!= null && metPatient.getSelectionModel().getSelectedItem()!= null && tired.getSelectionModel().getSelectedItem()!= null && taste.getSelectionModel().getSelectedItem()!= null && cough.getSelectionModel().getSelectedItem()!= null) {
            boolean met1, smell1, fever1, tired1, cough1, taste1;
            met1 = metPatient.getSelectionModel().getSelectedItem().equals("Yes");
            if (smell.getSelectionModel() != null)
                smell1 = smell.getSelectionModel().getSelectedItem().equals("Yes");
            else smell1 = false;
            fever1 = fever.getSelectionModel().getSelectedItem().equals("Yes");
            cough1 = cough.getSelectionModel().getSelectedItem().equals("Yes");
            taste1 = taste.getSelectionModel().getSelectedItem().equals("Yes");
            tired1 = tired.getSelectionModel().getSelectedItem().equals("Yes");
            warningtext.setVisible(false);
            try {
                Message msg = new Message();
                msg.setMet(met1);
                msg.setCough(cough1);
                msg.setFever(fever1);
                msg.setTaste(taste1);
                msg.setTired(tired1);
                msg.setSmell(smell1);
                msg.setUsername(App.getUsername());
                msg.setAction("Add questionnaire");
                SimpleClient.getClient().openConnection();
                SimpleClient.getClient().sendToServer(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            warningtext.setVisible(true);
        }

    }
    @Subscribe
    public void onQuestionnaireEvent(QuestionnaireEvent event) {
        ChangeScreens.changeTestAppScreen();
    }

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        Menubar.getChildren().clear();
        Menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        warningtext.setVisible(false);
        fever.setVisible(true);
        metPatient.setVisible(true);
        questionnaireLabel.setVisible(true);
        questionnaireLabel1.setVisible(true);
        questionnaireLabel2.setVisible(true);
        questionnaireLabel3.setVisible(true);
        questionnaireLabel4.setVisible(true);
        questionnaireLabel5.setVisible(true);
        questionnaireLabel6.setVisible(true);
        saveQuestionnaire.setVisible(true);
        smell.setVisible(true);
        taste.setVisible(true);
        tired.setVisible(true);
        cough.getItems().clear();
        cough.getItems().add("Yes");
        cough.getItems().add("No");
        metPatient.getItems().clear();
        metPatient.getItems().add("Yes");
        metPatient.getItems().add("No");
        fever.getItems().clear();
        fever.getItems().add("Yes");
        fever.getItems().add("No");
        smell.getItems().clear();
        smell.getItems().add("Yes");
        smell.getItems().add("No");
        taste.getItems().clear();
        taste.getItems().add("Yes");
        taste.getItems().add("No");
        tired.getItems().clear();
        tired.getItems().add("Yes");
        tired.getItems().add("No");
    }

}
