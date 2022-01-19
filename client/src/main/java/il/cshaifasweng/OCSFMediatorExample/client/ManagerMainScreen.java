package il.cshaifasweng.OCSFMediatorExample.client;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

/**
 * ManagerMainPage
 * Contains Hello message and a list of daily tasks.
 *
 */

public class ManagerMainScreen {
    protected String tasks = """
            1. Don't forget to update employees hours.
            2. Don't forget to get inventory list from the lab.
            3. Don't forget to close air condition and lights at the end of the day.
            4. Don't forget to send weekly report to the main office.
            """;

    @FXML
    private Pane menubar;

    @FXML
    private TextField welcomeTF;

    @FXML
    private TextArea tasksList;



    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        welcomeTF.setText("Welcome " + App.getFirst_name());
        tasksList.setText(tasks);
    }


}
