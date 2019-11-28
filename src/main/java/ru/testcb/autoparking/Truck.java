package ru.testcb.autoparking;

import java.util.Objects;
import java.util.Random;

public class Truck implements Automobil {
    private final String nameAuto;
    private final String number;
    private int timeParking = 0;

    public Truck(String nameAuto, String number) {
        this.nameAuto = nameAuto;
        this.number = number;
        timeParking = getRandomNumberInRange(1, 5);
    }

    @Override
    public int getTimeParking() {
        return timeParking;
    }

    @Override
    public void decrementTimeParking() {
        if (timeParking > 0) {
            timeParking--;
        }
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void sayWhoAmI() {
        System.out.println("Hello, I'm a Track and my name is " + nameAuto);
    }

    @Override
    public String getNameAuto() {
        return nameAuto;
    }

    @Override
    public String toString() {
        return "Track{" +
                "nameAuto='" + nameAuto + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck track = (Truck) o;
        return Objects.equals(nameAuto, track.nameAuto) &&
                Objects.equals(number, track.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameAuto, number);
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be higher Min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
