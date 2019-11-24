package ru.testcb.autoparking;

public interface AutoFabric<T> {
    T createNew(T t, String number, String name);
}
