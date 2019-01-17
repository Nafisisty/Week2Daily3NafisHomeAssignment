package com.example.week2daily3nafishomeassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.example.week2daily3nafishomeassignment.DatabaseConstants.DATABASE_NAME;
import static com.example.week2daily3nafishomeassignment.DatabaseConstants.DATABASE_VERSION;
import static com.example.week2daily3nafishomeassignment.DatabaseConstants.FIELD_IMAGE;
import static com.example.week2daily3nafishomeassignment.DatabaseConstants.FIELD_NAME;
import static com.example.week2daily3nafishomeassignment.DatabaseConstants.FIELD_SOUND;
import static com.example.week2daily3nafishomeassignment.DatabaseConstants.FIELD_TYPE;
import static com.example.week2daily3nafishomeassignment.DatabaseConstants.TABLE_NAME;

public class MySQLDatabaseHelper extends SQLiteOpenHelper {

    public MySQLDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createQuery = "CREATE TABLE " + TABLE_NAME + " ("
                + FIELD_NAME + " TEXT PRIMARY KEY, "
                + FIELD_TYPE + " TEXT, "
                + FIELD_SOUND + " TEXT, "
                + FIELD_IMAGE + " TEXT)";

        db.execSQL(createQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void addAnimal(Animal animal) {

        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (animal != null) {

            contentValues.put(FIELD_NAME, animal.getAnimalName());
            contentValues.put(FIELD_TYPE, animal.getAnimalType());
            contentValues.put(FIELD_SOUND, animal.getAnimalSound());
            contentValues.put(FIELD_IMAGE, animal.getAnimalImage());

            database.insert(TABLE_NAME, null, contentValues);

        }

    }

    public ArrayList<Animal> getAllAnimal() {

            SQLiteDatabase database = getReadableDatabase();
            String query = "SELECT *FROM " + TABLE_NAME;
            Cursor cursor = database.rawQuery(query, null);

            if(cursor.moveToFirst()){
                ArrayList<Animal> animalArrayList = new ArrayList<>();
                do{

                   String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                   String type = cursor.getString(cursor.getColumnIndex(FIELD_TYPE));
                   String sound = cursor.getString(cursor.getColumnIndex(FIELD_SOUND));
                   String image = cursor.getString(cursor.getColumnIndex(FIELD_IMAGE));

                   animalArrayList.add(new Animal(name, type, sound, image));

                } while (cursor.moveToNext());
                return animalArrayList;
            }else {
                return null;
            }
    }

    public Animal getAnimal(String passsedName) {

        Animal animal = null;

        if(passsedName != null && !passsedName.isEmpty()){
            SQLiteDatabase database = getReadableDatabase();
            String query = "SELECT * FROM " + TABLE_NAME
                    + " WHERE " + FIELD_NAME + " = \"" + passsedName + "\"";

            Cursor cursor = database.rawQuery(query, null);

            if(cursor.moveToFirst()){

                String name = cursor.getString(cursor.getColumnIndex(FIELD_NAME));
                String type = cursor.getString(cursor.getColumnIndex(FIELD_TYPE));
                String sound = cursor.getString(cursor.getColumnIndex(FIELD_SOUND));
                String image = cursor.getString(cursor.getColumnIndex(FIELD_IMAGE));

                animal = new Animal(name, type, sound, image);

            }
            cursor.close();
        }
        return animal;
    }

    public int deleteAnimal(String passedName){

        String whereClause = FIELD_NAME + " = \"" + passedName + "\"";
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME, whereClause, null);

    }

}
