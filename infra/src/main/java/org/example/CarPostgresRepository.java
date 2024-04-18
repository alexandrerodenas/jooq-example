package org.example;

import org.example.database.model.tables.records.CarRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;

import java.util.Objects;

import static org.example.database.model.tables.CarTable.CAR_TABLE;

public class CarPostgresRepository implements CarRepository {

    private final DSLContext dslContext;

    public CarPostgresRepository(DSLContext dslContext) {
        this.dslContext = dslContext;
    }


    @Override
    public Car create(CreateCar createCar) {
        Record record = dslContext
                .insertInto(CAR_TABLE)
                .set(CAR_TABLE.BRAND, createCar.brand())
                .set(CAR_TABLE.PRICE, createCar.price())
                .returningResult()
                .fetchOne();
        if (record == null) {
            throw new RuntimeException(); // Custom Exception here
        }
        return Car
                .builder()
                .id(record.get(CAR_TABLE.ID))
                .brand(record.get(CAR_TABLE.BRAND))
                .price(record.get(CAR_TABLE.PRICE))
                .build();
    }

    @Override
    public Car fetch(long id) {
        CarRecord carRecord = dslContext.selectFrom(CAR_TABLE)
                .where(CAR_TABLE.ID.eq(id))
                .fetchOne();
        if (carRecord == null) {
            throw new RuntimeException(); // Custom Exception here
        }
        return Car
                .builder()
                .id(carRecord.get(CAR_TABLE.ID))
                .brand(carRecord.get(CAR_TABLE.BRAND))
                .price(carRecord.get(CAR_TABLE.PRICE))
                .build();
    }
}
