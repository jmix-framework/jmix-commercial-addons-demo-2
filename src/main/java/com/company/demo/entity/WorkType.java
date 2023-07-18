package com.company.demo.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import jakarta.annotation.Nullable;


public enum WorkType implements EnumClass<String> {

    OFFICE("O"),
    REMOTE("R");

    private final String id;

    WorkType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static WorkType fromId(String id) {
        for (WorkType at : WorkType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}