package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.printMessageToScreenEvent;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.time.LocalTime;

public class waitingRoomScreenController {

    @FXML
    private TextField timeTF;
    @FXML
    private Label nextPatientLabel;
    @FXML
    private TextField nextPatientTF;
    @FXML
    private Label roomLabel;
    @FXML
    private TextField roomTF;

    @Subscribe
    public void printMessageToScreen(printMessageToScreenEvent event) {
        nextPatientTF.setText(event.getPatient_name());
        roomTF.setText(Integer.toString(event.getRoom()));
        nextPatientTF.setVisible(true);
        roomTF.setVisible(true);
    }

    @FXML
    void initialize() throws IOException {

        EventBus.getDefault().register(this);
        nextPatientLabel.setVisible(false);
        roomLabel.setVisible(false);
        nextPatientTF.setVisible(false);
        roomTF.setVisible(false);

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            timeTF.setText(currentTime.getHour() + ":" + currentTime.getMinute());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
