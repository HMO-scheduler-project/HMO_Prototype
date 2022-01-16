package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.events.*;
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
		if (currMsg.getAction().equals("ShowClinicsForStation")) {
			List<String> clinicList = currMsg.getClinicList();
			EventBus.getDefault().post(new ClinicListStationUpdateEvent(clinicList));
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

	if(currMsg.getAction().equals("AllRepToRep")){
		EventBus.getDefault().post(new AllTypeRepEvent(currMsg.getAwaitingTimeRep(),currMsg.getServicesTypeRep(),currMsg.getMissedAppRep()));
	}




		if(currMsg.getAction().equals("login done")){
			EventBus.getDefault().post(new loginEvent(currMsg.getUserType(),currMsg.getStatus(),currMsg.getUsername(),currMsg.getFirst_name()));
		}

		if(currMsg.getAction().equals("loginByCard done")){
			System.out.println(currMsg.getUser().getUsername());
			EventBus.getDefault().post(new stationLoginEvent(currMsg.getUserType(),currMsg.getStatus(),currMsg.getUser()));
		}

		if(currMsg.getAction().equals("logged out")){
			EventBus.getDefault().post(new logoutEvent(currMsg.getStatus()));
		}
		if(currMsg.getAction().equals("logged out from station")){
			EventBus.getDefault().post(new logoutFromStationEvent(currMsg.getStatus()));
		}
		if(currMsg.getAction().equals("got nearest apps")){
			EventBus.getDefault().post(new nearestAppsEvent(currMsg.getNearest_apps()));
		}
		if(currMsg.getAction().equals("got appointment")){
			EventBus.getDefault().post(new appointmentTicketEvent(currMsg.getAppointment(),currMsg.getAppCount()));
		}
		if(currMsg.getAction().equals("got nurse app")){
			EventBus.getDefault().post(new nurseAppEvent(currMsg.getAppointment()));
		}
		if(currMsg.getAction().equals("got nurseAppCounter")){
			EventBus.getDefault().post(new nurseAppCounterEvent(currMsg.getAppointment(),currMsg.getAppCount()));
		}
		if(currMsg.getAction().equals("got lab app")){
			EventBus.getDefault().post(new labAppEvent(currMsg.getAppointment()));
		}
		if(currMsg.getAction().equals("got labAppCounter")){
			EventBus.getDefault().post(new labAppCounterEvent(currMsg.getAppointment(),currMsg.getAppCount()));
		}
		if(currMsg.getAction().equals("got Appointment")){
			EventBus.getDefault().post(new appointmentTicketEvent(currMsg.getAppointment(),currMsg.getAppCount()));
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
		if(currMsg.getAction().equals("got employee")){
			EventBus.getDefault().post(new EmployeeEvent(currMsg.getEmployee()));
		}
		if(currMsg.getAction().equals("print message to screen")){
			EventBus.getDefault().post(new printMessageToScreenEvent(currMsg.getRoom(),currMsg.getPatientName()));
		}
		if (currMsg.getAction().equals("got patient appointments")) {
			EventBus.getDefault().post(new ViewAppsEvent(currMsg.getNearest_apps()));
		}
		if(currMsg.getAction().equals("removed app"))
		{
			EventBus.getDefault().post(new RemoveAppEvent(currMsg.isRemoved()));
		}
		if (currMsg.getAction().equals("got employees")) {
			EventBus.getDefault().post(new GotDoctorsEvent(currMsg.getEmployeeList(),currMsg.getNearest_apps()));
		}
		if (currMsg.getAction().equals("Doctor appointment added")) {
			EventBus.getDefault().post(new SavedAppEvent(currMsg.isSaved()));
		}
		if (currMsg.getAction().equals("Influenza vaccine appointment added")) {
			EventBus.getDefault().post(new SavedAppEvent(currMsg.isSaved()));
		}
		if (currMsg.getAction().equals("Got LabWorkers and clinic apps")) {
			EventBus.getDefault().post(new GotLabWorkersVaccineEven(currMsg.getLabWorkerList(), currMsg.getNearest_apps(), currMsg.getClinic()));
		}
		if (currMsg.getAction().equals("Questionnaire added")) {
			EventBus.getDefault().post(new QuestionnaireEvent(currMsg.isSaved()));
		}
		if (currMsg.getAction().equals("Covid test appointment added")) {
			EventBus.getDefault().post(new SavedAppEvent(currMsg.isSaved()));
		}
		if (currMsg.getAction().equals("Covid vaccine appointment added")) {
			EventBus.getDefault().post(new SavedAppEvent(currMsg.isSaved()));
		}
		if (currMsg.getAction().equals("Special doctor appointment added")) {
			EventBus.getDefault().post(new SavedAppEvent(currMsg.isSaved()));
		}
		if (currMsg.getAction().equals("got special doctors")) {
			EventBus.getDefault().post(new GotSpecialDocEvent(currMsg.getSpecialDoctorList()));
		}

//		if(currMsg.getAction().equals("Got vaccines")){
//			EventBus.getDefault().post(new GotVaccineEvent(currMsg.isCovid_vaccine(),currMsg.isInfluenza_vaccine(),currMsg.getCovid19VaccineApp(),currMsg.getInfluenzaVaccineApp()));
//		}
		if(currMsg.getAction().equals("GetAllClinicsWithCovidTest")){
			EventBus.getDefault().post(new GotClinicsWithCovidTest(currMsg.getClinicList()));
		}
		if(currMsg.getAction().equals("GetAllClinicsWithVaccine")){
			EventBus.getDefault().post(new GotClinicsWithVaccine(currMsg.getClinicList()));
		}
		if(currMsg.getAction().equals("Got special doctor appointments and clinics")){
			EventBus.getDefault().post(new SpecialDocClinicsEvent(currMsg.getSpecialDoctorAppList(),currMsg.getClinics(),currMsg.getSpecialDoctor()));
		}
		if(currMsg.getAction().equals("Got birthdate and clinic"))
		{
			EventBus.getDefault().post(new BirthDateClinicEvent(currMsg.getBirthDate(),currMsg.getClinic(),currMsg.getAge()));
		}
		if(currMsg.getAction().equals("Got green pass"))
		{
			EventBus.getDefault().post(new GreenPassEvent(currMsg.getGreenPass()));
		}
		if(currMsg.getAction().equals("Is_HMO_ManagerEvent")){
			EventBus.getDefault().post(new HMO_ManagerEvent());
		}
		if(currMsg.getAction().equals("Is_ManagerEvent")){
			EventBus.getDefault().post(new ManagerEvent());
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
