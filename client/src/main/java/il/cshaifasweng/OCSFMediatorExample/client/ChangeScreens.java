package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.io.IOException;

public class ChangeScreens {

    public static void changeToOpenningHoursScreen() throws IOException {
        App.setWindowTitle("Opening hours");
        App.setContent("openningHoursScreen");;
    }

    public static void changeToContactInfoScreen() throws IOException {
        App.setWindowTitle("Contact info");
        App.setContent("contactInfoScreen");;
    }

    public static void changeToMainPage() throws IOException {
        App.setWindowTitle("main page");
        switch (App.getUserType()) {
            case "Patient" -> App.setContent("PatientMainScreen");
            case "Manager" -> App.setContent("ManagerMainScreen");
            case "Employee" -> App.setContent("EmployeeMainPage");
        }
    }


}
