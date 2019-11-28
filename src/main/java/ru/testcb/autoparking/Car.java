package ru.testcb.autoparking;

import java.util.Objects;
import java.util.Random;

public class Car implements Automobil {
    private String number;
    private String nameAuto;
    private int timeParking = 0;

    public Car(String nameAuto, String number) {
        this.number = number;
        this.nameAuto = nameAuto;
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
    public String toString() {
        return "Car{" +
                "number='" + number + '\'' +
                ", nameAuto='" + nameAuto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(number, car.number) &&
                Objects.equals(nameAuto, car.nameAuto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, nameAuto);
    }

    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void sayWhoAmI() {
        System.out.println("Hello, I'm a Car and my name is " + nameAuto);
    }


    @Override
    public String getNameAuto() {
        return nameAuto;
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be higher Min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
