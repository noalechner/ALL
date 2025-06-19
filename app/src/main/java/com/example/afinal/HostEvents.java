package com.example.afinal;
import com.google.firebase.Timestamp;
public class HostEvents {
    private final String topic;
    private final String ownerId;
    private final String name;
    private final String date;
    private final String time;
    private final String address;

    public HostEvents(String topic, String ownerId,String name,  String date, String time, String address){
        this.topic=topic;
        this.ownerId=ownerId;
        this.name=name;
        this.date=date;
        this.time=time;
        this.address=address;
    }
    public String getTopic() {
        return topic;
    }
    public String getOwnerId() {
        return ownerId;
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
}
