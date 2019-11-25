package ru.testcb.autoparking;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class main {
    public static void main(String[] args) {
        System.out.println("Hello it's autoparking App");
        JoinParking parking = new JoinParking();
        parking.addCarToParking(new Car("1", "bmw"));
        parking.removeCarFromParking("1");
        parking.run();

    }
}
