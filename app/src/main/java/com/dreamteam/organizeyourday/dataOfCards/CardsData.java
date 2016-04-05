package com.dreamteam.organizeyourday.dataOfCards;

public class CardsData {
    private String title ;
    private final int ID;

    public  CardsData(int ID,String title){
        this.title = title;
        this.ID = ID;
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
}
