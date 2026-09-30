package com.example.chimatchaversijava;

public class Matcha {
    public String name;
    public String description;
    public int img;

    public Matcha(String name, String description, int img) {
        this.name = name;
        this.description = description;
        this.img = img;
    }

    public String getName(){ return this.name;}
    public String getDescription(){ return this.description;}
    public int getImg(){ return this.img;}

}
