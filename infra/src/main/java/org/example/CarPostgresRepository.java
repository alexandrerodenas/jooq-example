package org.example;

import org.jooq.DSLContext;

import java.util.Objects;

import static org.example.database.model.tables.CarTable.CAR_TABLE;

public class CarPostgresRepository implements CarRepository {

    private final DSLContext dslContext;

    public CarPostgresRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }


    @Override
    public Car create(CreateCar createCar) {
        return Objects.requireNonNull(dslContext.insertInto(CAR_TABLE)
                        .set(CAR_TABLE.BRAND, createCar.brand())
                        .set(CAR_TABLE.PRICE, createCar.price())
                        .returningResult(CAR_TABLE)
                        .fetchOne())
                .into(Car.class);
    }

    @Override
    public Car fetch(long id) {
        return Objects.requireNonNull(dslContext.selectFrom(CAR_TABLE)
                        .where(CAR_TABLE.ID.eq(id))
                        .fetchOne())
                .into(Car.class);
    }
}
