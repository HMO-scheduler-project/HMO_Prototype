package il.cshaifasweng.OCSFMediatorExample.entities;
import Reports.AwaitingTimeRep;
import Reports.MissedAppRep;
import Reports.ServicesTypeRep;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private LocalDate BirthDate=null;
    private boolean covid_vaccine;
    private boolean influenza_vaccine;
    private int age;
    /*----------Handling clinics----*/
    private Clinic clinic;
    public List<String> ClinicList;
    private String clinic_name;
    private LocalTime opening_hour;
    private LocalTime closing_hour;
    private String address;
    private String phone_num;
    private int room;
    List<Clinic> clinics;
    /*-------Handling appointments---*/
    private Appointment appointment;
    private List<Appointment> nearest_apps;
    private boolean arrived;
    private long app_count;
    private AwaitingTimeRep awaitingTimeRep;
    private MissedAppRep missedAppRep;
    private ServicesTypeRep servicesTypeRep;
    private LocalDate appDate;
    private LocalTime appTime;
    private boolean saved;
    private List<specialDoctorApp> specialDoctorAppList;
    /*-------Handling Employees-------*/
    private int employee_id;
    private String role;
    private Employee employee;
    private List<Doctor> employeeList;
    private List<LabWorker> labWorkerList;
    private List<SpecialDoctor> specialDoctorList;
    private SpecialDoctor specialDoctor;
    /*-------Handling questionnaires-------*/
    private boolean met;
    private boolean fever;
    private boolean cough;
    private boolean tired;
    private boolean taste;
    private boolean smell;

    /*-------Handling updates-------*/
    private String service_name;
    private List<String> services;
    private List<String> doctors;
    private String doctor;
    public AwaitingTimeRep getAwaitingTimeRep() {
        return awaitingTimeRep;
    }

    public void setAwaitingTimeRep(AwaitingTimeRep awaitingTimeRep) {
        this.awaitingTimeRep = awaitingTimeRep;
    }

    public MissedAppRep getMissedAppRep() {
        return missedAppRep;
    }

    public void setMissedAppRep(MissedAppRep missedAppRep) {
        this.missedAppRep = missedAppRep;
    }

    public ServicesTypeRep getServicesTypeRep() {
        return servicesTypeRep;
    }

    public void setServicesTypeRep(ServicesTypeRep servicesTypeRep) {
        this.servicesTypeRep = servicesTypeRep;
    }

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

    public List<specialDoctorApp> getSpecialDoctorAppList() {
        return specialDoctorAppList;
    }

    public void setSpecialDoctorAppList(List<specialDoctorApp> specialDoctorAppList) {
        this.specialDoctorAppList = specialDoctorAppList;
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

    public List<String> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<String> doctors) {
        this.doctors = doctors;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
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

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public LocalDate getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        BirthDate = birthDate;
    }

    public boolean isCovid_vaccine() {
        return covid_vaccine;
    }

    public void setCovid_vaccine(boolean covid_vaccine) {
        this.covid_vaccine = covid_vaccine;
    }

    public boolean isInfluenza_vaccine() {
        return influenza_vaccine;
    }

    public void setInfluenza_vaccine(boolean influenza_vaccine) {
        this.influenza_vaccine = influenza_vaccine;
    }

    public String getClinic_name() {
        return clinic_name;
    }

    public void setClinic_name(String clinic_name) {
        this.clinic_name = clinic_name;
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

    public LocalDate getAppDate() {
        return appDate;
    }

    public void setAppDate(LocalDate appDate) {
        this.appDate = appDate;
    }

    public LocalTime getAppTime() {
        return appTime;
    }

    public void setAppTime(LocalTime appTime) {
        this.appTime = appTime;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Doctor> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Doctor> employeeList) {
        this.employeeList = employeeList;
    }

    public List<LabWorker> getLabWorkerList() {
        return labWorkerList;
    }

    public void setLabWorkerList(List<LabWorker> labWorkerList) {
        this.labWorkerList = labWorkerList;
    }

    public List<SpecialDoctor> getSpecialDoctorList() {
        return specialDoctorList;
    }

    public void setSpecialDoctorList(List<SpecialDoctor> specialDoctorList) {
        this.specialDoctorList = specialDoctorList;
    }

    public boolean isMet() {
        return met;
    }

    public void setMet(boolean met) {
        this.met = met;
    }

    public boolean isFever() {
        return fever;
    }

    public void setFever(boolean fever) {
        this.fever = fever;
    }

    public boolean isCough() {
        return cough;
    }

    public void setCough(boolean cough) {
        this.cough = cough;
    }

    public boolean isTired() {
        return tired;
    }

    public void setTired(boolean tired) {
        this.tired = tired;
    }

    public boolean isTaste() {
        return taste;
    }

    public void setTaste(boolean taste) {
        this.taste = taste;
    }

    public boolean isSmell() {
        return smell;
    }

    public void setSmell(boolean smell) {
        this.smell = smell;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SpecialDoctor getSpecialDoctor() {
        return specialDoctor;
    }

    public void setSpecialDoctor(SpecialDoctor specialDoctor) {
        this.specialDoctor = specialDoctor;
    }
}
