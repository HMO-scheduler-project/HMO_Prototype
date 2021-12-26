package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Pair;

import java.io.IOException;

public class ChangeScreens {

    public static void changeToOpenningHoursScreen() throws IOException {
        Platform.runLater(() -> {
        App.setWindowTitle("Opening hours");
            try {
                App.setContent("openningHoursScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void changeToContactInfoScreen() throws IOException {
        Platform.runLater(() -> {
            App.setWindowTitle("Contact info");
            try {
                App.setContent("contactInfoScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void changeToMainPage() throws IOException {
        Platform.runLater(() -> {
            App.setWindowTitle("main page");
            switch (App.getUserType()) {
                case "Patient" -> {
                    try {
                        App.setContent("PatientMainScreen");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case "Manager" -> {
                    try {
                        App.setContent("ManagerMainScreen");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                case "Employee" -> {
                    try {
                        App.setContent("EmployeeMainPage");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void changeToLogin() {
        Platform.runLater(() -> {
            App.setWindowTitle("login");
            try {
                App.setContent("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void changeToStationLogin() {
        Platform.runLater(() -> {
            App.setWindowTitle("stationLogin");
            try {
                App.setContent("stationLogin");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void changeToWaitingRoomScreen() {
        Platform.runLater(() -> {
            App.setWindowTitle("WaitingRoomScreen");
            try {
                App.setContent("WaitingRoomScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
