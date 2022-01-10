package il.cshaifasweng.OCSFMediatorExample.entities;
import Reports.AwaitingTimeRep;
import Reports.MissedAppRep;
import Reports.ServicesTypeRep;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;


public class Message implements Serializable {
    /* ---------- Message Necessary Info ---------- */
    private int id;
    private String action;
    private String type;
    private String error;
    /* ---------- Handling users ---------- */
    private User user;
    private String username;
    private String first_name;
    private String password;
    private String userType;
    private String status;
    private String sessionID;
    private String UserCardNumber;
    /*----------Handling clinics----*/
    private Clinic clinic;
    public List<String> ClinicList;
    private String clinic_name;
    private LocalTime opening_hour;
    private LocalTime closing_hour;
    private String address;
    private String phone_num;
    /*-------Handling appointments---*/
    private Appointment appointment;
    private List<Appointment> nearest_apps;
    private boolean arrived;
    private int card_num;
    private long app_count;
    /*-------Handling updates-------*/
    private String service_name;
    private List<String> services;
    private String doctor_name;

    public Message() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getUserCardNumber() {
        return UserCardNumber;
    }

    public void setUserCardNumber(String userCardNumber) {
        UserCardNumber = userCardNumber;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public List<String> getClinicList() {
        return ClinicList;
    }

    public void setClinicList(List<String> clinicList) {
        ClinicList = clinicList;
    }

    public String getClinicName(){
        return clinic_name;
    }

    public void setClinicName(String name){
        this.clinic_name = name;
    }

    public void setOpeningHour (LocalTime opening_hour){
        this.opening_hour = opening_hour;
    }

    public LocalTime getOpeningHour(){
        return opening_hour;
    }

    public void setClosingHour (LocalTime closing_hour){
        this.closing_hour = closing_hour;
    }

    public LocalTime getClosingHour(){
        return closing_hour;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phone_num;
    }

    public void setPhoneNum(String phone_num) {
        this.phone_num = phone_num;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public long getAppCount() {
        return app_count;
    }

    public void setAppCount(long app_count) {
        this.app_count = app_count;
    }

    public List<Appointment> getNearest_apps() {
        return nearest_apps;
    }

    public void setNearest_apps(List<Appointment> nearest_apps) {
        this.nearest_apps = nearest_apps;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }

    public boolean patientArrived() {
        return arrived;
    }

    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    public int getCardNum() {
        return card_num;
    }

    public void setCardNum(int card_num) {
        this.card_num = card_num;
    }

}
