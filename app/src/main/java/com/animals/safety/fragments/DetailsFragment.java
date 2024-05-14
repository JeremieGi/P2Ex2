package com.animals.safety.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.animals.safety.R;
import com.animals.safety.data.Animal;
import com.animals.safety.data.AnimalData;
import com.animals.safety.databinding.FragmentDetailsBinding;

public class DetailsFragment extends Fragment {

    public static final String KEY_ANIMAL = "ANIMAL";

    private FragmentDetailsBinding binding;

    private Animal getAnimal() {
        return (Animal) getArguments().getSerializable(KEY_ANIMAL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toFill();


        binding.fabDelete.setOnClickListener(view1 -> {
            removeAnimal();
        });

        binding.fabEdit.setOnClickListener(view1 -> {

            // Ouverture du fragment

            Bundle bundle = new Bundle();
            Animal animalParam = getAnimal();
            bundle.putSerializable(DetailsFragment.KEY_ANIMAL, animalParam);

            NavHostFragment.findNavController(DetailsFragment.this)
                    .navigate(R.id.action_DetailsFragment_to_CreateFragment, bundle);

        });

    }

    private void removeAnimal() {
        Animal animalToDelete = getAnimal();
        // Normalement animalToDelete ne sera jamais null
        if (animalToDelete!=null){
            AnimalData.animals.remove(getAnimal()); // retire l'animal du tableau
            back();                                 // Revient au RecyclerView
        }
    }

    private void toFill() {
        binding.imageViewAvatar.setImageDrawable(AppCompatResources.getDrawable(getContext(), getAnimal().getBreed().getCover()));
        binding.textViewName.setText(getAnimal().getName());
        binding.textViewAge.setText(getString(R.string.value_age, String.valueOf(getAnimal().getAge())));
        binding.textViewWeight.setText(getString(R.string.value_weight, String.valueOf(getAnimal().getWeight())));
        binding.textViewHeight.setText(getString(R.string.value_height, String.valueOf(getAnimal().getHeight())));
    }

    private void navigateToEdition() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailsFragment.KEY_ANIMAL, getAnimal());

        NavHostFragment.findNavController(DetailsFragment.this)
                .navigate(R.id.action_DetailsFragment_to_CreateFragment, bundle);
    }

    private void back() {
        NavHostFragment.findNavController(DetailsFragment.this).navigateUp();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}