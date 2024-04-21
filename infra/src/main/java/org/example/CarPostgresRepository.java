package org.example;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Records;

import static org.example.database.model.tables.CarTable.CAR_TABLE;

import java.util.Optional;

public class CarPostgresRepository implements CarRepository {

    private final DSLContext dslContext;

    public CarPostgresRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }


    @Override
    public Car create(CreateCarInput createCar) {
        return dslContext
                .insertInto(CAR_TABLE)
                .set(CAR_TABLE.BRAND, createCar.brand())
                .set(CAR_TABLE.PRICE, createCar.price())
                .returningResult(CAR_TABLE.ID, CAR_TABLE.BRAND, CAR_TABLE.PRICE)
                .fetchOne(Records.mapping(Car::new));
    }

    @Override
    public Optional<Car> fetch(long id) {
        return dslContext.selectFrom(CAR_TABLE)
                .where(CAR_TABLE.ID.eq(id))
                .fetchOptional(it -> Car.builder()
                    .id(it.get(CAR_TABLE.ID))
                    .brand(it.get(CAR_TABLE.BRAND))
                    .price(it.get(CAR_TABLE.PRICE))
                    .build()
                );
    }
}
