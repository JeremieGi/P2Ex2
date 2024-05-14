package com.animals.safety.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.animals.safety.R;
import com.animals.safety.data.Animal;
import com.animals.safety.data.AnimalData;
import com.animals.safety.data.Breed;
import com.animals.safety.databinding.FragmentCreateBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CreateFragment extends Fragment {

    private FragmentCreateBinding binding;

    private Animal getAnimal() {
        try {
            return (Animal) getArguments().getSerializable(DetailsFragment.KEY_ANIMAL);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.numberpickerBreed.setMinValue(1);
        binding.numberpickerBreed.setMaxValue(getBreeds().size());
        binding.numberpickerBreed.setValue(1);
        binding.numberpickerBreed.setWrapSelectorWheel(false);
        binding.numberpickerBreed.setDisplayedValues(getBreeds().toArray(new String[0]));

        binding.fab.setOnClickListener(view1 -> {
            if (verifyAndCreateAnimal()) {
                NavHostFragment.findNavController(CreateFragment.this).navigateUp();
            }
        });

        // Chargement de l'animal passé en paramètre
        if (getAnimal() != null) {
            fillWithAnimal(getAnimal());
        }
    }

    private void fillWithAnimal(Animal animal) {
        binding.fieldName.setText(animal.getName());

         // TODO : Voir mentor, il doit y avoir un autre moyen
        int nBreed = animal.getBreed().ordinal();
        binding.numberpickerBreed.setValue(nBreed+1);

        binding.fieldAge.setText(String.valueOf(animal.getAge()));
        binding.fieldHeight.setText(String.valueOf(animal.getHeight()));
        binding.fieldWeight.setText(String.valueOf(animal.getWeight()));

    }

    private List<String> getBreeds() {
        ArrayList<String> result = new ArrayList<>();
        for (Breed breed : Breed.values()) {
            result.add(breed.name());
        }
        return result;
    }

    private boolean verifyAndCreateAnimal() {
        String name = binding.fieldName.getText().toString();
        if (name.isBlank()) {
            Snackbar.make(
                    getView(),
                    "The name must not be empty",
                    Snackbar.LENGTH_LONG
            ).setAnchorView(R.id.fab).show();
            return false;
        }

        Breed breed = Breed.values()[binding.numberpickerBreed.getValue() - 1];

        int age;
        try {
            age = Integer.parseInt(binding.fieldAge.getText().toString());
        } catch (NumberFormatException e) {
            Snackbar.make(
                    getView(),
                    "The age is not valid",
                    Snackbar.LENGTH_LONG
            ).setAnchorView(R.id.fab).show();
            return false;
        }

        float weight;
        try {
            weight = Float.parseFloat(binding.fieldWeight.getText().toString());
        } catch (NumberFormatException e) {
            Snackbar.make(
                    getView(),
                    "The weight is not valid",
                    Snackbar.LENGTH_LONG
            ).setAnchorView(R.id.fab).show();
            return false;
        }

        float height;
        try {
            height = Float.parseFloat(binding.fieldHeight.getText().toString());
        } catch (NumberFormatException e) {
            Snackbar.make(
                    getView(),
                    "The height is not valid",
                    Snackbar.LENGTH_LONG
            ).setAnchorView(R.id.fab).show();
            return false;
        }

        AnimalData.animals.remove(getAnimal());
        AnimalData.animals.add(new Animal(
                name,
                breed,
                age,
                weight,
                height
        ));

        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}