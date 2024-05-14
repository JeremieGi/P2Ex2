package com.animals.safety.adapter;

import androidx.recyclerview.widget.RecyclerView;

import com.animals.safety.data.Animal;

import java.util.ArrayList;

public abstract class RefreshableAdapter<T extends AnimalCardAdapter.AnimalViewHolder> extends RecyclerView.Adapter<T> {
    protected ArrayList<Animal> data = new ArrayList<>();

    public void refresh(ArrayList<Animal> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
