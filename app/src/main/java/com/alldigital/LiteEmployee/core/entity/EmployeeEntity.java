package com.alldigital.LiteEmployee.core.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "employee_table")
public class EmployeeEntity implements Serializable {
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "avatar", typeAffinity = ColumnInfo.BLOB)
    private byte[] avatar;
    @ColumnInfo(name = "fullName")
    private String fullName;
    @ColumnInfo(name = "phoneNumber")
    private String phoneNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
