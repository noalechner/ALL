package com.example.recycleview;

public class Animals {
    private String AnimalNames;
    private int AnimalPic;
    private int AnimalRate;

    public Animals( String animalNames, int animalPic, int animalRate) {
        this.AnimalPic = animalPic;
        this.AnimalNames = animalNames;
        this.AnimalRate = animalRate;

    }

    public String getAnimalNames() {
        return AnimalNames;
    }

    public int getAnimalPics() {
        return AnimalPic;
    }

    public int getAnimalRates() {
        return AnimalRate;
    }

    public void setAnimalNames(String animalNames) {
        AnimalNames = animalNames;
    }

    public void setAnimalPics(int animalPic) {
        AnimalPic = animalPic;
    }

    public void setAnimalRates(int animalRate) {
        AnimalRate = animalRate;
    }
}
