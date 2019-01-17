package com.example.week2daily3nafishomeassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class NewAnimal extends AppCompatActivity {

    ListView animalTypeListView;
    EditText nameEditText, typeEditText, soundEditText, imageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_animal);

        animalTypeListView = findViewById(R.id.animalTypeListView);
        nameEditText = findViewById(R.id.nameEditTextViewId);
        typeEditText = findViewById(R.id.typeEditTextViewId);
        soundEditText = findViewById(R.id.soundEditTextViewId);
        imageEditText = findViewById(R.id.imageEditTextViewId);

        final String[] animalTypeList = {"Mammal", "Reptile", "Birds", "Fish"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, animalTypeList);

        animalTypeListView.setAdapter(adapter);

        animalTypeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                typeEditText.setText(animalTypeList[position]);
            }
        });

    }

    public void onClick(View view) {

        if(nameEditText.getText() != null && !nameEditText.getText().toString().isEmpty()){

            MySQLDatabaseHelper mySQLDatabaseHelper = new MySQLDatabaseHelper(this);

            String name = nameEditText.getText().toString();
            String type = typeEditText.getText().toString();
            String sound = soundEditText.getText().toString();
            String image = imageEditText.getText().toString();

            Animal animal = new Animal(name, type, sound, image);
            mySQLDatabaseHelper.addAnimal(animal);

            MainActivity.recyclerViewAdapater.addAnimal(animal);

            Toast.makeText(this, "Successfully Added New Animal", Toast.LENGTH_SHORT).show();
        }

    }
}
