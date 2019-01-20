package com.example.week2daily3nafishomeassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapater extends RecyclerView.Adapter<RecyclerViewAdapater.ViewHolder> {

    ArrayList<Animal> animalArrayList;


    public RecyclerViewAdapater(ArrayList<Animal> animalArrayList) {
        this.animalArrayList = animalArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.animal, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapater.ViewHolder viewHolder, int position) {

        Animal animal = animalArrayList.get(position);

        if(animal != null){
            viewHolder.setItemAnimal(animal);

            viewHolder.animalNameTextView.setText(animal.getAnimalName());
            viewHolder.animalTypeTextView.setText(animal.getAnimalType());
            viewHolder.animalSoundTextView.setText(animal.getAnimalSound());

            Glide.with(viewHolder.imageView)
                    .load("" + animal.getAnimalImage())
                    .into(viewHolder.imageView);

        }
    }

    @Override
    public int getItemCount() {
        return animalArrayList != null ? animalArrayList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView animalNameTextView, animalTypeTextView, animalSoundTextView;
        Animal itemAnimal;

        public void setItemAnimal(Animal itemAnimal) {
            this.itemAnimal = itemAnimal;
        }

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.animalImageViewId);
            animalNameTextView = itemView.findViewById(R.id.animalNameTextViewId);
            animalTypeTextView = itemView.findViewById(R.id.animalTypeTextViewId);
            animalSoundTextView = itemView.findViewById(R.id.animalSoundTextViewId);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(itemView.getContext(), AnimalDetails.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("name", itemAnimal.getAnimalName());
                    bundle.putString("type", itemAnimal.getAnimalType());
                    bundle.putString("sound", itemAnimal.getAnimalSound());
                    bundle.putString("image", itemAnimal.getAnimalImage());
                    intent.putExtras(bundle);
                    itemView.getContext().startActivity(intent);

                }
            });
        }
    }

    public void addAnimal(Animal animal){
        if(animalArrayList == null){
            animalArrayList = new ArrayList<>();
        }
        animalArrayList.add(animal);
        notifyDataSetChanged();
    }

    public void removeAnimal(int position){
        if(animalArrayList == null){
            animalArrayList = new ArrayList<>();
        }
        animalArrayList.remove(position);
        notifyItemRemoved(position);
    }

}
