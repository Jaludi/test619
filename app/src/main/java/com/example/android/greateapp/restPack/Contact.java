package com.example.android.greateapp.restPack;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android on 6/12/2017.
 */

public class Contact implements Parcelable {
    private String location;
    private String name;
    private String gender;
    private String email;
    private String login;
    private String dob;
    private String registered;
    private String phone;
    private String cell;
    private String id;
    private String nat;
    private String imageURL;


    protected Contact(Parcel in) {
        location = in.readString();
        name = in.readString();
        gender = in.readString();
        email = in.readString();
        login = in.readString();
        dob = in.readString();
        registered = in.readString();
        phone = in.readString();
        cell = in.readString();
        id = in.readString();
        nat = in.readString();
        imageURL = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override

    public int describeContents() {
        return 0;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Contact(){

    }
    public Contact(String location, String name, String gender, String email, String login, String dob, String registered, String phone, String cell, String id, String nat, String URL) {
        this.location = location;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.login = login;
        this.dob = dob;
        this.registered = registered;
        this.phone = phone;
        this.cell = cell;
        this.id = id;
        this.nat = nat;
        this.imageURL = URL;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(location);
        dest.writeString(name);
        dest.writeString(gender);
        dest.writeString(email);
        dest.writeString(login);
        dest.writeString(dob);
        dest.writeString(registered);
        dest.writeString(phone);
        dest.writeString(cell);
        dest.writeString(id);
        dest.writeString(nat);
        dest.writeString(imageURL);
    }
}
