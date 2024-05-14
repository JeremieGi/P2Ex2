package com.animals.safety.data;

import java.util.ArrayList;

public class AnimalData {

    private static final Animal milou = new Animal("Milou", Breed.DOG, 6, 23.2f, 42.4f);
    private static final Animal rantanplan = new Animal("Rantanplan", Breed.DOG, 8, 28.1f, 38.4f);
    private static final Animal garfield = new Animal("Garfield", Breed.CAT, 12, 5.4f, 21.3f);
    private static final Animal marguerite = new Animal("Marguerite", Breed.COW, 3, 668.3f, 148f);

    public static ArrayList<Animal> animals = new ArrayList<>() {
        {
            add(milou);
            add(rantanplan);
            add(garfield);
            add(marguerite);
        }
    };
}

