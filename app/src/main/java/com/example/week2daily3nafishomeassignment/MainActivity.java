package com.example.week2daily3nafishomeassignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.week2daily3nafishomeassignment.Helper.RecyclerItemTouchHelper;
import com.example.week2daily3nafishomeassignment.Helper.RecyclerItemTouchHelperListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    RecyclerView recyclerView;
    static RecyclerViewAdapater recyclerViewAdapater;
    ArrayList<Animal> animalArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.animalRecyclerView);

        onResume();

        recyclerViewAdapater = new RecyclerViewAdapater(animalArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapater);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
                new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        animalArrayList = listOfAnimal();
    }

    public void onClick(View view) {

        Intent intent = new Intent(this, NewAnimal.class);
        startActivity(intent);
    }

    public ArrayList<Animal> listOfAnimal(){

        ArrayList<Animal> animalArrayList = new ArrayList<>();
        MySQLDatabaseHelper mySQLDatabaseHelper = new MySQLDatabaseHelper(this);
        animalArrayList = mySQLDatabaseHelper.getAllAnimal();

        return animalArrayList;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {


        if(viewHolder instanceof RecyclerViewAdapater.ViewHolder) {

//            String name = animalArrayList.get(viewHolder.getAdapterPosition()).getAnimalName();

            Animal deletedAnimal = animalArrayList.get(viewHolder.getAdapterPosition());
            int deleteIndex = viewHolder.getAdapterPosition();

            recyclerViewAdapater.removeAnimal(deleteIndex);

            MySQLDatabaseHelper mySQLDatabaseHelper = new MySQLDatabaseHelper(this);
            int isDeleted = mySQLDatabaseHelper.deleteAnimal(deletedAnimal.getAnimalName());

            if(isDeleted == 1){
                Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
