/*
 * This file is generated by jOOQ.
 */
package org.example.database.model.tables;


import java.util.function.Function;

import org.example.database.model.Keys;
import org.example.database.model.Public;
import org.example.database.model.tables.records.CarRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CarTable extends TableImpl<CarRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.car</code>
     */
    public static final CarTable CAR_TABLE = new CarTable();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CarRecord> getRecordType() {
        return CarRecord.class;
    }

    /**
     * The column <code>public.car.id</code>.
     */
    public final TableField<CarRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.car.brand</code>.
     */
    public final TableField<CarRecord, String> BRAND = createField(DSL.name("brand"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>public.car.price</code>.
     */
    public final TableField<CarRecord, Double> PRICE = createField(DSL.name("price"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>public.car.user_id</code>.
     */
    public final TableField<CarRecord, Long> USER_ID = createField(DSL.name("user_id"), SQLDataType.BIGINT, this, "");

    private CarTable(Name alias, Table<CarRecord> aliased) {
        this(alias, aliased, null);
    }

    private CarTable(Name alias, Table<CarRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.car</code> table reference
     */
    public CarTable(String alias) {
        this(DSL.name(alias), CAR_TABLE);
    }

    /**
     * Create an aliased <code>public.car</code> table reference
     */
    public CarTable(Name alias) {
        this(alias, CAR_TABLE);
    }

    /**
     * Create a <code>public.car</code> table reference
     */
    public CarTable() {
        this(DSL.name("car"), null);
    }

    public <O extends Record> CarTable(Table<O> child, ForeignKey<O, CarRecord> key) {
        super(child, key, CAR_TABLE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<CarRecord, Long> getIdentity() {
        return (Identity<CarRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<CarRecord> getPrimaryKey() {
        return Keys.CAR_PKEY;
    }

    @Override
    public CarTable as(String alias) {
        return new CarTable(DSL.name(alias), this);
    }

    @Override
    public CarTable as(Name alias) {
        return new CarTable(alias, this);
    }

    @Override
    public CarTable as(Table<?> alias) {
        return new CarTable(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public CarTable rename(String name) {
        return new CarTable(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CarTable rename(Name name) {
        return new CarTable(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public CarTable rename(Table<?> name) {
        return new CarTable(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, String, Double, Long> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Long, ? super String, ? super Double, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Long, ? super String, ? super Double, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
