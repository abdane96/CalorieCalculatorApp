package com.example.abdal.caloriescalculator;

/**
 * Created by abdal on 12/30/2017.
 */

public class Profile {
    private String name;
    private String gender;
    private double weight;
    private double height;
    private String weightUnit;
    private String heightUnit;
    private double heightInches;
    private int age;
    private String activityLevel;

    public Profile(String name, String gender, double weight, double height, String weightUnit, String heightUnit, double heightInches, int age, String activityLevel){
        this.name = name;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.weightUnit = weightUnit;
        this.heightUnit = heightUnit;
        this.heightInches = heightInches;
        this.age = age;
        this.activityLevel = activityLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(double heightInches) {
        this.heightInches = heightInches;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

}
