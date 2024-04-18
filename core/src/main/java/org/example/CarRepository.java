package org.example;

public interface CarRepository {
    Car create(CreateCar createCar);
    Car fetch(long id);
}
