package com.example.afinal;
public class Event {
    public String name;
    public String date;
    public String time;
    public String adress;

    public Event(String name, String date,String time,String adress) {
        this.name=name;
        this.date=date;
        this.time=time;
        this.adress=adress;
    }
    public Event() {
        // empty constructor
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
}
