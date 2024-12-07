package com.example.model;

public class Tank {
    private String name;
    private String country;
    private double gunCaliber;
    private int frontArmor;
    private double speed;
    private int crew;

    public Tank() {}

    public Tank(String name, String country, double gunCaliber, int frontArmor, double speed, int crew) {
        this.name = name;
        this.country = country;
        this.gunCaliber = gunCaliber;
        this.frontArmor = frontArmor;
        this.speed = speed;
        this.crew = crew;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getGunCaliber() {
        return gunCaliber;
    }

    public void setGunCaliber(double gunCaliber) {
        this.gunCaliber = gunCaliber;
    }

    public int getFrontArmor() {
        return frontArmor;
    }

    public void setFrontArmor(int frontArmor) {
        this.frontArmor = frontArmor;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }
}
