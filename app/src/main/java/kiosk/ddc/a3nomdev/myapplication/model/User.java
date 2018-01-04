package kiosk.ddc.a3nomdev.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 3nomdev on 10/17/17.
 */

public class User implements Serializable{
    @SerializedName("reservationId")
    @Expose
    private String reservationId;
    @SerializedName("reservationCount")
    @Expose
    private Integer reservationCount;
    @SerializedName("tableType")
    @Expose
    private String tableType;
    @SerializedName("table")
    @Expose
    private String table;
    @SerializedName("attended")
    @Expose
    private Boolean attended;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("familyId")
    @Expose
    private Integer familyId;
    @SerializedName("personID")
    @Expose
    private Integer personID;
    @SerializedName("fullAddress")
    @Expose
    private String fullAddress;
    @SerializedName("isSarElef")
    @Expose
    private Integer isSarElef;
    @SerializedName("alumnus")
    @Expose
    private String alumnus;

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(Integer reservationCount) {
        this.reservationCount = reservationCount;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Integer getIsSarElef() {
        return isSarElef;
    }

    public void setIsSarElef(Integer isSarElef) {
        this.isSarElef = isSarElef;
    }

    public String getAlumnus() {
        return alumnus;
    }

    public void setAlumnus(String alumnus) {
        this.alumnus = alumnus;
    }
}
