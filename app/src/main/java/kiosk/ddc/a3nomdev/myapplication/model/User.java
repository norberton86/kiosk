package kiosk.ddc.a3nomdev.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by 3nomdev on 10/17/17.
 */

public class User implements Serializable{

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("companyID")
    @Expose
    private Integer companyID;
    @SerializedName("scan")
    @Expose
    private String scan;
    @SerializedName("checkIn")
    @Expose
    private Boolean checkIn;
    @SerializedName("guestAmount")
    @Expose
    private Integer guestAmount;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("resevationId")
    @Expose
    private Integer resevationId;
    @SerializedName("tableNumber")
    @Expose
    private Integer tableNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getScan() {
        return scan;
    }

    public void setScan(String scan) {
        this.scan = scan;
    }

    public Boolean getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Boolean checkIn) {
        this.checkIn = checkIn;
    }

    public Integer getGuestAmount() {
        return guestAmount;
    }

    public void setGuestAmount(Integer guestAmount) {
        this.guestAmount = guestAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getResevationId() {
        return resevationId;
    }

    public void setResevationId(Integer resevationId) {
        this.resevationId = resevationId;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

}
