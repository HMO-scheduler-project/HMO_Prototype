package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class PatientMainScreen {
    private final String[] tips = {"""
Eat a variety of foods.\s
For good health, we need more than 40 different nutrients, and no single food can
supply them all. It is not about a single meal, it is about a balanced food choice
over time that will make a difference!""",
            """
Base your diet on plenty of foods rich in carbohydrates.
About half the calories in our diet should come from foods rich in carbohydrates,
such as: cereals, rice, pasta, potatoes, and bread.
It is a good idea to include at least one of these at every meal.
Wholegrain foods, like wholegrain bread, pasta, and cereals, will increase our
fibre intake.""",
            """
Drink plenty of fluids
Adults need to drink at least 1.5 litres of fluid a day!
Or more if it's very hot or they are physically active.
Water is the best source, of course, and we can use tap or mineral water,
sparkling or non-sparkling, plain or flavoured.
Fruit juices, tea, soft drinks, milk and other drinks, can all be okay -
from time to time.""",
            """
Get on the move, make it a habit!
Physical activity is important for people of all weight ranges and
health conditions. It helps us burn off the extra calories,it is good for
the heart and circulatory system, it maintains or increases our muscle mass,
it helps us focus, and improves overallhealth well-being.
We don't have to be top athletes to get on the move!
150 minutes per week of moderate physical activity is advised, and it can easily
become part of our daily routine""",
"""
Get enough sleep
The importance of getting enough quality sleep cannot be overstated.
Poor sleep can drive insulin resistance, can disrupt your appetite hormones,
and reduce your physical and mental performance.
What’s more, poor sleep is one of the strongest individual risk factors for
weight gain and obesity. People who do not get enough sleep tend to make food
choices that are higher in fat, sugar, and calories, potentially leading to
unwanted weight gain""",
            """
Nurture your social relationships
Social relationships — with friends, family, and loved ones you care about —
are important not only for your mental well-being but also your physical health.
Studies show that people who have close friends and family are healthier and live
much longer than those who do not\s"""};

    @FXML
    private Pane menubar;

    @FXML
    private TableView<Appointment> nearestAppTable;

    @FXML
    private TableColumn<Appointment,LocalDate> DateCol;

    @FXML
    private TableColumn<Appointment, LocalTime> TimeCol;

    @FXML
    private TableColumn<Appointment,String> ClinicCol;


    @FXML
    private Button newAppBtn;

    @FXML
    private TextField welcomeTF;

    @FXML
    private TextArea tipTF;

    @FXML
    void pressOnNewApp(ActionEvent event) {
        ChangeScreens.changeNewAppScreen();
    }

    @FXML
    void initialize() throws IOException {
        Parent menuBarParent = App.loadFXML("menuBar.fxml");
        menubar.getChildren().clear();
        menubar.getChildren().add(menuBarParent);
        EventBus.getDefault().register(this);
        welcomeTF.setText("Welcome " + App.getFirst_name());
        Random rand = new Random();
        int num = rand.nextInt(0,6);
        tipTF.setText("Tip of the day: "+ tips[num]);
        try {
            Message msg= new Message();
            msg.setUsername(App.getUsername());
            msg.setAction("GetNearestApps");
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onNearestAppsUpdateEvent(nearestAppsEvent event) {
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        TimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        ClinicCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getClinic().getName()));
        nearestAppTable.setItems(FXCollections.observableList(event.getAppsList()));
    }
}
