package ru.testcb.autoparking;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class AutoParking {
    public static void main(String[] args) {
        System.out.println("Hello it's autoparking App");
        CarFabric carFabric = new CarFabric();
        Map<String, Object> parking = new HashMap<String, Object>();
        parking.put("1", new Car("1", "bmw"));
        parking.put("2", new Car("2", "audi"));
        parking.put("3", new Car("3", "mers"));
        parking.put("4", new Car("4", "vaz"));
        parking.put("5", new Car("5", "zaz"));
        parking.put("6", new Car("6", "lada"));

        parking.put("11", new Track("1", "bmw"));
        parking.put("12", new Track("2", "audi"));
        parking.put("13", new Track("3", "mers"));
        parking.put("14", new Track("4", "vaz"));
        parking.put("15", new Track("5", "zaz"));
        parking.put("16", new Track("6", "lada"));

        System.out.println(parking);

        synchronized ()
    }
}
