package com.example.week2daily3nafishomeassignment;

public class Animal {

    private String animalName;
    private String animalType;
    private String animalSound;
    private String animalImage;

    public Animal() {
    }

    public Animal(String animalName, String animalType, String animalSound, String animalImage) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalSound = animalSound;
        this.animalImage = animalImage;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAnimalSound() {
        return animalSound;
    }

    public void setAnimalSound(String animalSound) {
        this.animalSound = animalSound;
    }

    public String getAnimalImage() {
        return animalImage;
    }

    public void setAnimalImage(String animalImage) {
        this.animalImage = animalImage;
    }
}
