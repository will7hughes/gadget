package com.whughes.gadget.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// USE FOR SQLite
@Entity(tableName = "UserIndex")
public class UserEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_id")
    @SerializedName("user_id")
    @Expose
    private int userID;

    @NonNull
    @ColumnInfo(name = "username")
    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("reports")
    @Expose
    @ColumnInfo(name = "reports")
    private Integer reports;

    @SerializedName("inspector")
    @Expose
    @ColumnInfo(name = "inspector")
    private Integer inspector;

    @SerializedName("resident")
    @Expose
    @ColumnInfo(name = "resident")
    private Integer resident;

    @SerializedName("coordinator")
    @Expose
    @ColumnInfo(name = "coordinator")
    private Integer coordinator;

    @SerializedName("contract_admin")
    @Expose
    @ColumnInfo(name = "contract_admin")
    private Integer contractAdmin;

    @SerializedName("admin")
    @Expose
    @ColumnInfo(name = "admin")
    private Integer admin;

    @Ignore
    public UserEntity(@NonNull int userID, @NonNull String username) {
        this.userID = userID;
        this.username = username;
    }
    public UserEntity() {}

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    public Integer getReports() {
        return reports;
    }

    public void setReports(Integer reports) {
        this.reports = reports;
    }

    public Integer getInspector() {
        return inspector;
    }

    public void setInspector(Integer inspector) {
        this.inspector = inspector;
    }

    public Integer getResident() {
        return resident;
    }

    public void setResident(Integer resident) {
        this.resident = resident;
    }

    public Integer getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(Integer coordinator) {
        this.coordinator = coordinator;
    }

    public Integer getContractAdmin() {
        return contractAdmin;
    }

    public void setContractAdmin(Integer contractAdmin) {
        this.contractAdmin = contractAdmin;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}
