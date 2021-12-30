package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import org.greenrobot.eventbus.EventBus;

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

		Message currMsg = ((Message) msg);

		if (currMsg.getAction().equals("ShowClinics")) {
			List<String> clinicList = currMsg.getClinicList();
			EventBus.getDefault().post(new ClinicListUpdateEvent(clinicList));
		}
		if(currMsg.getAction().equals("Chosen clinic")){
			EventBus.getDefault().post(new ChosenClinicEvent(currMsg.getClinic()));
		}
		if(currMsg.getAction().equals("saved new hours")){
			EventBus.getDefault().post(new ChangeHoursEvent(currMsg.getOpeningHour(),currMsg.getClosingHour()));
		}

		if(currMsg.getAction().equals("got opening hours")){
			EventBus.getDefault().post(new showHoursEvent(currMsg.getOpeningHour(),currMsg.getClosingHour()));
		}
		if(currMsg.getAction().equals("got contact info")){
			EventBus.getDefault().post(new showContactInfoEvent(currMsg.getAddress(), currMsg.getPhoneNum()));
		}
		if(currMsg.getAction().equals("saved new address")){
			EventBus.getDefault().post(new ChangeAddressEvent(currMsg.getAddress()));
		}
		if(currMsg.getAction().equals("saved new phone")){
			EventBus.getDefault().post(new ChangePhoneNumEvent(currMsg.getPhoneNum()));
		}
		if(currMsg.getAction().equals("login done")){
			EventBus.getDefault().post(new loginEvent(currMsg.getUserType(),currMsg.getStatus(),currMsg.getUsername()));
		}
		if(currMsg.getAction().equals("logged out")){
			EventBus.getDefault().post(new logoutEvent(currMsg.getStatus()));
		}
	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3004);
		}
		return client;
	}

	public static void setClientNull(){
		client = null;
	}

}
