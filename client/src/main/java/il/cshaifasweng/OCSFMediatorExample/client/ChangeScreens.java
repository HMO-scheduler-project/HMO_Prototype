package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.application.Platform;

import java.io.IOException;

public class ChangeScreens {

    public static void changeToOpeningHoursScreen() {
        Platform.runLater(() -> {
        App.setWindowTitle("Opening hours");
            try {
                App.setContent("openingHoursScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void changeToReportsScreen(){
        Platform.runLater(()->{
            App.setWindowTitle("reports screen");
            try{
                App.setContent("reportsScreen");
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }

    public static void changeToContactInfoScreen() {
        Platform.runLater(() -> {
            App.setWindowTitle("Contact info");
            try {
                App.setContent("contactInfoScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void changeToMainPage() {
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
            App.setWindowTitle("Welcome");
            try {
                App.setContent("login");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void changeToStationLogin() {
        Platform.runLater(() -> {
            App.setWindowTitle("Welcome");
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

    public static void changeNewAppScreen(){
        Platform.runLater(() -> {
            App.setWindowTitle("schedule new appointment");
            try {
                App.setContent("newAppointmentScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void changeToUpdateHoursScreen(){
        Platform.runLater(() -> {
            App.setWindowTitle("update hours");
            try {
                App.setContent("UpdateHoursScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
