package com.animals.safety.data;

import androidx.annotation.DrawableRes;

import java.io.Serializable;

public class Animal implements Serializable {

    private String name;
    private Breed breed;
    private int age;
    private float weight;
    private float height;

    public Animal(String name, Breed breed, int age, float weight, float height) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}

