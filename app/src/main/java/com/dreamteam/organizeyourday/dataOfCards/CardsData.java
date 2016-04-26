package com.dreamteam.organizeyourday.dataOfCards;

public class CardsData {

    private String title ;
    private String description;

    private final int ID;
    private int priority;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String time;
    private String date;

    public  CardsData(int ID,String title, String description, int priority, String time, String date){
        this.title = title;
        this.ID = ID;
        this.description = description;
        this.priority = priority;
        this.time = time;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
