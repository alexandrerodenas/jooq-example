package org.example;

import java.util.Optional;

public interface CarRepository {
    Car create(CreateCarInput createCar);
    Optional<Car> fetch(long id);
}
