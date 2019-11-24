package ru.testcb.autoparking;

import java.util.Objects;

public class Track implements Automobil {
    private final String nameAuto;
    private final String number;

    public Track(String nameAuto, String number) {
        this.nameAuto = nameAuto;
        this.number = number;
    }


    @Override
    public String getNumber() {
        return number;
    }

    @Override
    public void sayWhoAmI() {
        System.out.println("Hello, I'm a Track and my name is " + nameAuto);
    }

    @Override
    public String getNameAuto() {
        return nameAuto;
    }

    @Override
    public String toString() {
        return "Track{" +
                "nameAuto='" + nameAuto + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Objects.equals(nameAuto, track.nameAuto) &&
                Objects.equals(number, track.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameAuto, number);
    }
}
