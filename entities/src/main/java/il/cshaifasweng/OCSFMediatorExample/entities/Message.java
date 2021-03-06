package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.time.LocalDate;
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
    private Patient patient;
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
    private String patientName;
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
    private LocalDate appDate;
    private LocalTime appTime;
    private boolean saved;
    private boolean removed;
    private List<specialDoctorApp> specialDoctorAppList;
    private Covid19VaccineApp covid19VaccineApp;
    private InfluenzaVaccineApp influenzaVaccineApp;
    /*-------Handling Employees-------*/
    private int employee_id;
    private String role;
    private Employee employee;
    private String employeeName;
    private List<Doctor> employeeList;
    private List<LabWorker> labWorkerList;
    private List<SpecialDoctor> specialDoctorList;
    private SpecialDoctor specialDoctor;
    private Manager manager;
    private HMO_Manager hmo_manager;
    /*-------Handling questionnaires-------*/
    private boolean met;
    private boolean fever;
    private boolean cough;
    private boolean tired;
    private boolean taste;
    private boolean smell;
    /*------Handling Reports--------*/
    private List<AwaitingTimeRep> awaitingTimeRep;
    private List<MissedAppRep> missedAppRep;
    private List<ServicesTypeRep> servicesTypeRep;
    /*-------Handling updates-------*/
    private String service_name;
    private List<String> services;
    private List<String> doctors;
    private String doctor;
    private GreenPass greenPass;

    public List<AwaitingTimeRep> getAwaitingTimeRep() {
        return awaitingTimeRep;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public HMO_Manager getHmo_manager() {
        return hmo_manager;
    }

    public void setHmo_manager(HMO_Manager hmo_manager) {
        this.hmo_manager = hmo_manager;
    }

    public void setAwaitingTimeRep(List<AwaitingTimeRep> awaitingTimeRep) {
        this.awaitingTimeRep = awaitingTimeRep;
    }

    public List<MissedAppRep> getMissedAppRep() {
        return missedAppRep;
    }

    public void setMissedAppRep(List<MissedAppRep> missedAppRep) {
        this.missedAppRep = missedAppRep;
    }

    public List<ServicesTypeRep> getServicesTypeRep() {
        return servicesTypeRep;
    }

    public void setServicesTypeRep(List<ServicesTypeRep> servicesTypeRep) {
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

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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

    public GreenPass getGreenPass() {
        return greenPass;
    }

    public void setGreenPass(GreenPass greenPass) {
        this.greenPass = greenPass;
    }

    public SpecialDoctor getSpecialDoctor() {
        return specialDoctor;
    }

    public void setSpecialDoctor(SpecialDoctor specialDoctor) {
        this.specialDoctor = specialDoctor;
    }

    public Covid19VaccineApp getCovid19VaccineApp() {
        return covid19VaccineApp;
    }

    public void setCovid19VaccineApp(Covid19VaccineApp covid19VaccineApp) {
        this.covid19VaccineApp = covid19VaccineApp;
    }

    public InfluenzaVaccineApp getInfluenzaVaccineApp() {
        return influenzaVaccineApp;
    }

    public void setInfluenzaVaccineApp(InfluenzaVaccineApp influenzaVaccineApp) {
        this.influenzaVaccineApp = influenzaVaccineApp;
    }
    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }
}
