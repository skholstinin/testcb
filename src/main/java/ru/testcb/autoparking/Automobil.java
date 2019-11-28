package ru.testcb.autoparking;

public interface Automobil {
    String getNumber();

    void sayWhoAmI();

    String getNameAuto();

    int getTimeParking();

    void decrementTimeParking();
}
