package com.example.week2daily3nafishomeassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Random;

public class AnimalDetails extends AppCompatActivity {

    TextView nameTextView, typeTextView, soundTextView, populationTextView;
    ImageView animalImageView;
    static String population;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details);

        Intent passedIntent = getIntent();
        Bundle passedBundle = passedIntent.getExtras();

        Animal animal = new Animal();

        if(passedBundle != null) {

            animal.setAnimalName(passedBundle.getString("name"));
            animal.setAnimalType(passedBundle.getString("type"));
            animal.setAnimalSound(passedBundle.getString("sound"));
            animal.setAnimalImage(passedBundle.getString("image"));

        }

        nameTextView = findViewById(R.id.nameTextViewId);
        typeTextView = findViewById(R.id.typeTextViewId);
        soundTextView = findViewById(R.id.soundTextViewId);
        populationTextView = findViewById(R.id.populationTextViewId);

        animalImageView = findViewById(R.id.animalImageViewId);


        nameTextView.setText("Name : " + animal.getAnimalName());
        typeTextView.setText("Type : " + animal.getAnimalType());
        soundTextView.setText("Sound : " + animal.getAnimalSound());

        generateRandomPopulation();
        populationTextView.setText("Population : " + population);


        Glide.with(animalImageView)
                .load("" + animal.getAnimalImage())
                .into(animalImageView);
    }

    public void generateRandomPopulation(){

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {

                Random rand = new Random();
                int randInt = rand.nextInt(100) + 1;
                population = String.valueOf(randInt);
                System.out.println("number generated: " + population);

            }
        });
        thread.start();
    }
}
