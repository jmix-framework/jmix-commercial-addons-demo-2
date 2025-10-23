package com.company.groupdatagrid.entity;

import io.jmix.core.metamodel.datatype.EnumClass;
import org.springframework.lang.Nullable;


public enum ClientType implements EnumClass<String> {

    BUSINESS("B"),
    INDIVIDUAL("I");

    private final String id;

    ClientType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ClientType fromId(String id) {
        for (ClientType at : ClientType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}