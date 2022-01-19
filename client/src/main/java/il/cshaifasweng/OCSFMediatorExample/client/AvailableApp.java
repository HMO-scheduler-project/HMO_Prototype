package il.cshaifasweng.OCSFMediatorExample.client;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * AvailableApp
 * class of available appointment. Used to display available appointments of the desired service/doctor.
 * Contains fields -
 * date
 * time
 * employeeName
 * clinic
 * employee_id
 * role
 *
 * with get and set for each field.
 * 
 */
public class AvailableApp {
    private LocalDate dateCol;
    private LocalTime timeCol;
    private String employeeName;
    private String clinicCol;
    private int employee_id;
    private String role;

    public AvailableApp(LocalDate dateCol, LocalTime timeCol, String employeeName, String clinicCol,int employee_id,String role) {
        this.dateCol = dateCol;
        this.timeCol = timeCol;
        this.employeeName = employeeName;
        this.clinicCol=clinicCol;
        this.employee_id=employee_id;
        this.role=role;
    }

    public LocalDate getDateCol() {
        return dateCol;
    }

    public void setDateCol(LocalDate dateCol) {
        this.dateCol = dateCol;
    }

    public LocalTime getTimeCol() {
        return timeCol;
    }

    public void setTimeCol(LocalTime timeCol) {
        this.timeCol = timeCol;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getClinicCol() {
        return clinicCol;
    }

    public void setClinicCol(String clinicCol) {
        this.clinicCol = clinicCol;
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
}