package com.example.afinal;

import com.google.firebase.Timestamp;

public class HostEvents {

    private final String name;
    private final String date;
    private final String time;
    private final String address;
    private final String userId;


    public HostEvents(String name,  String date, String time, String address, String userId){
    this.name=name;
    this.date=date;
    this.time=time;
    this.address=address;
    this.userId=userId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAddress() {
        return address;
    }
    public String getUserId() {
        return userId;
    }

}
