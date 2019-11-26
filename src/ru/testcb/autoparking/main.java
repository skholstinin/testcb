package ru.testcb.autoparking;

public class main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello it's autoparking App");
        Parking parking = new Parking(10);
        parking.fillParking();
        for (int i = 1; i < 2; i++) {
            new Thread(parking).start();
            Thread.sleep(1000);
        }

    }
}
