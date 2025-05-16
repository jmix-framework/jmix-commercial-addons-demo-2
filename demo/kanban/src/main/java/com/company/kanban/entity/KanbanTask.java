package com.company.kanban.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

@JmixEntity
@Table(name = "KANBAN_TASK", indexes = {
        @Index(name = "IDX_KANBAN_TASK_ASSIGNEE", columnList = "ASSIGNEE_ID")
})
@Entity
public class KanbanTask {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "PROGRESS")
    private Integer progress;

    @Column(name = "TAGS")
    private String tags;

    @InstanceName
    @Column(name = "TEXT", nullable = false)
    @NotNull
    private String text;

    @Column(name = "STATUS", nullable = false)
    @NotNull
    private String status;

    @JoinColumn(name = "ASSIGNEE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private User assignee;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public TaskStatus getStatus() {
        return status == null ? null : TaskStatus.fromId(status);
    }

    public void setStatus(TaskStatus status) {
        this.status = status == null ? null : status.getId();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}