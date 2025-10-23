package com.company.groupdatagrid.entity;

import io.jmix.core.metamodel.datatype.EnumClass;
import org.springframework.lang.Nullable;


public enum OrderStatus implements EnumClass<Integer> {

    NEW(10),
    ACCEPTED(20),
    IN_PROGRESS(30),
    DONE(40);

    private final Integer id;

    OrderStatus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static OrderStatus fromId(Integer id) {
        for (OrderStatus at : OrderStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}