package ru.testcb.autoparking;

import java.util.HashMap;
import java.util.Map;

public class JoinParking implements Runnable {
    Map<String, Object> parking = new HashMap<String, Object>();

    void addCarToParking(Car car) {
        parking.put(car.getNameAuto(), car);
        System.out.println("Added one car to parking");
    }

    ;

    void removeCarFromParking(String name) {
        parking.remove(name);
        System.out.println("Remove one car from parking");
    }

    @Override
    public void run() {
        System.out.println("Parking is run!");
    }
}
