package com.dreamteam.organizeyourday.dataOfCards;

public class CardsData {

    private String title ;
    private String description;

    private final int ID;
    private int priority;

    public  CardsData(int ID,String title, String description, int priority){
        this.title = title;
        this.ID = ID;
        this.description = description;
        this.priority = priority;
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
