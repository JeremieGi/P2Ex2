package com.animals.safety.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.animals.safety.R;
import com.animals.safety.data.Animal;
import com.animals.safety.databinding.ItemAnimalBinding;
import com.animals.safety.utils.StringUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AnimalCardAdapter extends RefreshableAdapter<AnimalCardAdapter.AnimalViewHolder> {

    OnAnimalClickListener clickListener;

    public AnimalCardAdapter(ArrayList<Animal> data, OnAnimalClickListener clickListener) {
        this.clickListener = clickListener;
        refresh(data);
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnimalViewHolder(ItemAnimalBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = data.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(animal);
            }
        });

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        holder.binding.avatar.setImageDrawable(AppCompatResources.getDrawable(holder.itemView.getContext(), animal.getBreed().getCover()));
        holder.binding.name.setText(animal.getName());
        String breedName = StringUtils.capitalize(holder.itemView.getContext().getString(animal.getBreed().getTranslatedName()));
        holder.binding.information.setText(holder.itemView
                .getContext()
                .getString(
                        R.string.information,
                        breedName,
                        animal.getAge(),
                        df.format(animal.getWeight()),
                        df.format(animal.getHeight())
                ));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder {

        ItemAnimalBinding binding;

        public AnimalViewHolder(ItemAnimalBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
