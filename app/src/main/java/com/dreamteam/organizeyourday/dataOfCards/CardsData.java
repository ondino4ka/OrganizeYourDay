package com.dreamteam.organizeyourday.dataOfCards;

import org.w3c.dom.Text;

public class CardsData {
    private String title ;

    public  CardsData(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
