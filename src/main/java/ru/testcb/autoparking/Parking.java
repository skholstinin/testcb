package ru.testcb.autoparking;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Parking implements Runnable {
    List<Automobil> generalParking = new ArrayList<Automobil>();
    List<Truck> truckParking = new ArrayList<Truck>();
    private int PARKING_SIZE_CAR = 0;
    private int PARKING_SIZE_TRUCK = 0;
    private int sizeFreePlacesTruckParking = 0;
    private int sizeFreePlacesGeneralParking = 0;
    private int indexAuto = 0;
    private static int delta;
    static final Semaphore SEMAPHORE = new Semaphore(1, true);

    public Parking(int PARKING_SIZE_CAR, int PARKING_SIZE_TRUCK) {
        this.PARKING_SIZE_CAR = PARKING_SIZE_CAR;
        this.PARKING_SIZE_TRUCK = PARKING_SIZE_TRUCK;
        sizeFreePlacesGeneralParking = PARKING_SIZE_CAR;
        sizeFreePlacesTruckParking = PARKING_SIZE_TRUCK;
    }

    private void incrementIndexAuto() {
        indexAuto++;//для генерации автомобилей
    }

    synchronized private Car generateOneSomeCar() {
        incrementIndexAuto();
        return new Car("car_" + String.valueOf(indexAuto), String.valueOf(indexAuto));
    }

    synchronized private Truck generateOneSomeTruck() {
        incrementIndexAuto();
        return new Truck("truck_" + String.valueOf(indexAuto), String.valueOf(indexAuto));
    }

    void addCarToGeneralParking(Automobil automobil) {
        if (automobil instanceof Car) {
            if (sizeFreePlacesGeneralParking > 0) {
                generalParking.add(automobil);
                sizeFreePlacesGeneralParking--;
                System.out.println("Added car with number №" + automobil.getNumber() + " and name = " + automobil.getNameAuto());

            } else {
                System.out.println("There aren't no one places for car on the general parking");
            }
        }
        if (automobil instanceof Truck) {//если грузовик и мест на парковке больше двух
            if (sizeFreePlacesGeneralParking > 2) {
                generalParking.add(automobil);
                sizeFreePlacesGeneralParking -= 2;
                System.out.println("Added truck with number №" + automobil.getNumber() + " and name = " + automobil.getNameAuto());
            } else {
                System.out.println("There aren't no one places for truck on the general parking");
            }
        }
    }

    void addTruckToParking(Truck truck) {
        if (sizeFreePlacesTruckParking > 0) {
            truckParking.add(truck);
            sizeFreePlacesTruckParking--;
            System.out.println("Added truck with number №" + truck.getNumber() + " and name = " + truck.getNameAuto());
        } else {
            System.out.println("There aren't no one places on the truck parking");
        }
    }

    private void unparkSomeAuto(Automobil automobil) {
        if (automobil instanceof Truck) {
            if (truckParking.indexOf(automobil) != -1) {
                truckParking.remove(automobil);
                sizeFreePlacesTruckParking++;//free 1 place because it's a truck on truck parking
                System.out.println("Unpark truck with number №" + automobil.getNumber() + " and name = " + automobil.getNameAuto() + " from truckParking");
            }
            if (generalParking.indexOf(automobil) != -1) {
                generalParking.remove(automobil);
                sizeFreePlacesGeneralParking += 2;//free 2 place because it's a truck on generalParking
                System.out.println("Unpark truck with number №" + automobil.getNumber() + " and name = " + automobil.getNameAuto() + " from generalParking");
            }
        }
        if (automobil instanceof Car) {
            if (generalParking.indexOf(automobil) != -1) {
                generalParking.remove(automobil);
                sizeFreePlacesGeneralParking++;//free 1 place because it's a car on car parking
                System.out.println("Unpark car with number №" + automobil.getNumber() + " and name = " + automobil.getNameAuto() + " from generalParking");
            }
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
        try {
            SEMAPHORE.acquire();
            if (sizeFreePlacesTruckParking == 0) {
                Thread.sleep(1000);
            } else {
                synchronized (truckParking) {
                    for (int i = truckParking.size(); i < PARKING_SIZE_TRUCK; i++) {
                        addTruckToParking(generateOneSomeTruck());
                    }
                }
            }
            if (sizeFreePlacesGeneralParking == 0) {
                Thread.sleep(1000);
            } else {
                synchronized (generalParking) {
                    if (getRandomNumberInRange(0, 1000) > 500) {
                        addCarToGeneralParking(generateOneSomeCar());
                    } else {
                        addCarToGeneralParking(generateOneSomeTruck());
                    }
                }
            }
            if (sizeFreePlacesGeneralParking == 0) {
                synchronized (generalParking) {
                    System.out.println("Wait until at least one place on general parking is being free");
                    Thread.sleep(1000);
                    List<Automobil> tempList = new ArrayList<Automobil>();
                    long currentTime = System.currentTimeMillis();
                    for (Automobil auto : generalParking) {
                        if (auto.getEndTimeParking() < currentTime) {
                            tempList.add(auto);//чтобы не удалять во время перебора списка
                        }
                    }
                    for (Automobil auto : tempList) {//чтобы не удалять во время перебора списка
                        unparkSomeAuto(auto);
                    }
                }
            }
            if (sizeFreePlacesTruckParking == 0) {
                synchronized (truckParking) {
                    System.out.println("Wait until at least one place on truck parking is being free");
                    Thread.sleep(1000);
                    List<Truck> tempList = new ArrayList<>();
                    long currentTime = System.currentTimeMillis();
                    for (Truck truck : truckParking) {
                        if (truck.getEndTimeParking() < currentTime) {
                            tempList.add(truck);//чтобы не удалять во время перебора списка
                        }
                    }
                    for (Truck truck : tempList) {//чтобы не удалять во время перебора списка
                        unparkSomeAuto(truck);
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SEMAPHORE.release();

    }
}
