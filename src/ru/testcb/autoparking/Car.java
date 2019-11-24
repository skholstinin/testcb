package ru.testcb.autoparking;

import java.util.Objects;

public class Car implements Automobil {
    private String number;
    private String nameAuto;

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

    public Car(String number, String nameAuto) {
        this.number = number;
        this.nameAuto = nameAuto;
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
}
