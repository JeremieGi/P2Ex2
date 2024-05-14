package com.animals.safety.data;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.animals.safety.R;

public enum Breed {
    CAT, COW, DOG, LAMA;

    public @StringRes int getTranslatedName() {
        return switch (this) {
            case CAT -> R.string.breed_cat;
            case COW -> R.string.breed_cow;
            case DOG -> R.string.breed_dog;
            case LAMA -> R.string.breed_lama;
        };
    }

    public @DrawableRes int getCover() {
        return switch (this) {
            case CAT -> R.drawable.img_cat;
            case COW -> R.drawable.img_cow;
            case DOG -> R.drawable.img_dog;
            case LAMA -> R.drawable.img_lama;
        };
    }

    public int getValue() {
        return switch (this) {
            case CAT -> 0;
            case COW -> 1;
            case DOG -> 2;
            case LAMA -> 3;
        };
    }
}