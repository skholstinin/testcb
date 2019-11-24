package ru.testcb.autoparking;

public class CarFabric implements AutoFabric {
    @Override
    public Object createNew(Object o, String name, String number) {
        if (o instanceof Car) {
            return new Car(number, name);
        }
        if (o instanceof Track) {
            return new Track(number, name);
        }
        return new Car(number, name);
    }
}
