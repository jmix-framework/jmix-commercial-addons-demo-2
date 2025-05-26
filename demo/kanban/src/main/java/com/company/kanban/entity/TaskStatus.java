package com.company.kanban.entity;

import io.jmix.core.metamodel.datatype.EnumClass;
import org.springframework.lang.Nullable;


public enum TaskStatus implements EnumClass<String> {

    TODO("todo"),
    IN_PROGRESS("in-progress"),
    VERIFICATION("verification"),
    DONE("done");

    private final String id;

    TaskStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static TaskStatus fromId(String id) {
        for (TaskStatus at : TaskStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}