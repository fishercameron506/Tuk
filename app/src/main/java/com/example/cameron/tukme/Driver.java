package com.example.cameron.tukme;

/**
 * Created by Cameron on 3/3/2017.
 */
public class Driver {

    public Driver()
    {
        this.name = "";
        this.surname = "";
        this.rating = 0;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    private String name;
    private String surname;
    private float rating;
}
