package ru.testcb.autoparking;

import com.sun.java.swing.plaf.windows.WindowsDesktopIconUI;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Parking implements Runnable {
    Map<String, Automobil> parking = new HashMap<String, Automobil>();
    static int PARKING_SIZE = 0;
    private int indexAuto = 0;
    private static int delta;
    static final Semaphore SEMAPHORE = new Semaphore(1, true);

    public Parking(int PARKING_SIZE) {
        this.PARKING_SIZE = PARKING_SIZE;
    }

    void fillParking() {
        delta = PARKING_SIZE - indexAuto;
        for (indexAuto = parking.size(); indexAuto < PARKING_SIZE; indexAuto++) {
            if (indexAuto < (PARKING_SIZE - delta / 2)) {
                Truck truck = new Truck("truck_" + String.valueOf(indexAuto), String.valueOf(indexAuto));
                parking.put(truck.getNumber(), truck);
                indexAuto++;
                System.out.println("Added Truck with number №" + truck.getNumber() + " end name = " + truck.getNameAuto());
            } else {
                Car car = new Car("car_" + String.valueOf(indexAuto), String.valueOf(indexAuto));
                parking.put(car.getNumber(), car);
                indexAuto++;
                System.out.println("Added Car with number №" + car.getNumber() + " end name = " + car.getNameAuto());
            }
        }
    }

    private void unparkSomeAuto() {
        int autoId = getRandomNumberInRange(0, indexAuto);
        while (!parking.containsKey(String.valueOf(autoId))) {
            autoId = getRandomNumberInRange(0, indexAuto);
        }
        parking.get(String.valueOf(autoId));
        try {
            System.out.println("Try unparking one auto name " + parking.get(String.valueOf(autoId)).getNameAuto() + " and number " + parking.get(String.valueOf(autoId)).getNumber());
            parking.remove(String.valueOf(autoId));
            indexAuto = parking.size();
            System.out.println("Unparking successfully");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Max must be higher Min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public void run() {
        while (!parking.isEmpty()) {
            try {
                SEMAPHORE.acquire();
                synchronized (parking) {
                    unparkSomeAuto();
                }
                if (parking.size() >= PARKING_SIZE) {
                    System.out.println("There aren't any places in the parking");
                } else {
                    Thread.sleep(1000);
                    synchronized (parking) {
                        fillParking();
                    }
                }
                SEMAPHORE.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
