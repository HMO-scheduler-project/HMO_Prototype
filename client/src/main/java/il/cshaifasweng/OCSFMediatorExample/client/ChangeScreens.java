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
                App.setContent("ReportPage");
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
                App.setContent("LoginWithCard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void changeToStationMainPage(){
        Platform.runLater(() -> {
            App.setWindowTitle("Welcome");
            try {
                App.setContent("StationMainPage");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void changeToStationChooseClinic(){
        Platform.runLater(() -> {
            App.setWindowTitle("Welcome");
            try {
                App.setContent("ChooseClinic");
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
                App.setContent("NewAppointment");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    public static void changeToViewAppsScreen() {
        Platform.runLater(() -> {
            App.setWindowTitle("View Appointments");
            try {
                App.setContent("ViewAppointmentsScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static void changeToDoctorAppScreen() {
        Platform.runLater(() -> {
            App.setWindowTitle("Doctor appointment");
            try {
                App.setContent("DoctorAppointmentScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static void changeToVaccineAppScreen() {
        Platform.runLater(() -> {
            App.setWindowTitle("Vaccine appointment");
            try {
                App.setContent("VaccineAppointmentScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static void changeQuestionnaireScreen() {
        Platform.runLater(() -> {
            App.setWindowTitle("Questionnaire");
            try {
                App.setContent("QuestionnaireScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static void changeTestAppScreen() {
        Platform.runLater(() -> {
            App.setWindowTitle("Covid test appointment");
            try {
                App.setContent("CovidTestAppScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    public static void changeSpecialAppScreen() {
        Platform.runLater(() -> {
            App.setWindowTitle("Special doctor appointment");
            try {
                App.setContent("SpecialDoctorAppScreen");
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
    public static void changeToGreenPassScreen(){
        Platform.runLater(() -> {
            App.setWindowTitle("Green Pass");
            try {
                App.setContent("GreenPassScreen");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
