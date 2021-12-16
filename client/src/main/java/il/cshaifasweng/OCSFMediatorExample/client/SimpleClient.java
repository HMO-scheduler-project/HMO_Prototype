package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import org.greenrobot.eventbus.EventBus;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SimpleClient extends AbstractClient {

	private static SimpleClient client = null;
	private static final Logger LOGGER = Logger.getLogger(SimpleClient.class.getName());

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void connectionEstablished() {
		super.connectionEstablished();
		LOGGER.info("Connected to server.");
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
//		if (msg.getClass().equals(Warning.class)) {
//			EventBus.getDefault().post(new WarningEvent((Warning) msg));
//		}
		Message currMsg = ((Message) msg);
//		EventBus.getDefault().post(currMsg);
//
		if (currMsg.getAction().equals("ShowClinics")) {
			ArrayList<String> clinicList = currMsg.getClinicList();
			EventBus.getDefault().post(new ClinicListUpdateEvent(clinicList));
		}
	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3002);
		}
		return client;
	}

	public static void setClientNull(){
		client = null;
	}

}
