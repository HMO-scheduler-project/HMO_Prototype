package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import org.greenrobot.eventbus.EventBus;

import java.time.LocalTime;
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
		if(currMsg.getAction().equals("clinicNameFromUserName")){
			EventBus.getDefault().post(new ClinicNameUpdateEvent(currMsg.getClinicName()));
		}
		if(currMsg.getAction().equals("MissedAppRepToRep")){
			EventBus.getDefault().post(new MissedAppRepEvent(currMsg.getMissedAppRep()));
		}
		if(currMsg.getAction().equals("AwaitingTimeRepToRep")){
			EventBus.getDefault().post(new AwaitingTimeRepEvent(currMsg.getAwaitingTimeRep()));

		}if(currMsg.getAction().equals("ServicesTypeRepToRep")){
			EventBus.getDefault().post(new ServicesTypeRepEvent(currMsg.getServicesTypeRep()));
		}
		if(currMsg.getAction().equals("login done")){
			EventBus.getDefault().post(new loginEvent(currMsg.getUserType(),currMsg.getStatus(),currMsg.getUsername(),currMsg.getFirst_name()));
		}

		if(currMsg.getAction().equals("loginByCard done")){
			EventBus.getDefault().post(new stationLoginEvent(currMsg.getUserType(),currMsg.getStatus(),currMsg.getUsername(),currMsg.getUserCardNumber()));
		}

		if(currMsg.getAction().equals("logged out")){
			EventBus.getDefault().post(new logoutEvent(currMsg.getStatus()));
		}
		if(currMsg.getAction().equals("got nearest apps")){
			EventBus.getDefault().post(new nearestAppsEvent(currMsg.getNearest_apps()));
		}
		if(currMsg.getAction().equals("got patient apps")){
			EventBus.getDefault().post(new nearestAppsEvent(currMsg.getNearest_apps()));
		}
		if(currMsg.getAction().equals("updated arrival time")){
			EventBus.getDefault().post(new updateArrivalTimeEvent());
		}
		if(currMsg.getAction().equals("ShowManagedClinics")){
			EventBus.getDefault().post(new ManagedClinicListUpdateEvent(currMsg.getClinicList()));
		}
		if(currMsg.getAction().equals("ShowServices")){
			EventBus.getDefault().post(new ServiceListUpdateEvent(currMsg.getServices()));
		}
		if(currMsg.getAction().equals("ShowDoctors")){
			EventBus.getDefault().post(new DoctorListUpdateEvent(currMsg.getDoctors()));
		}
		if(currMsg.getAction().equals("ShowHours")){
			EventBus.getDefault().post(new showServiceHoursEvent(currMsg.getOpeningHour(),currMsg.getClosingHour(), currMsg.getRoom()));
		}
		if(currMsg.getAction().equals("saved new room")){
			EventBus.getDefault().post(new ChangeRoomEvent(currMsg.getRoom()));
		}
		if(currMsg.getAction().equals("loginByCard done")){
			EventBus.getDefault().post(new stationLoginEvent(currMsg.getUserType(),currMsg.getStatus(),currMsg.getUsername(),currMsg.getUserCardNumber()));
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
