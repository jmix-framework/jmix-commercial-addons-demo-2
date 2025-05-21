package com.company.webdav.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.webdav.entity.WebdavDocument;
import jakarta.persistence.*;

import java.util.UUID;

@JmixEntity
@Table(name = "ARTICLE", indexes = {
        @Index(name = "IDX_ARTICLE_WEBDAV_DOCUMENT", columnList = "WEBDAV_DOCUMENT_ID"),
        @Index(name = "IDX_ARTICLE_DEPARTMENT", columnList = "DEPARTMENT_ID")
})
@Entity
public class Article {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "DEPARTMENT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @InstanceName
    @Column(name = "DESCRIPTION")
    private String description;

    @JoinColumn(name = "WEBDAV_DOCUMENT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private WebdavDocument webdavDocument;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public WebdavDocument getWebdavDocument() {
        return webdavDocument;
    }

    public void setWebdavDocument(WebdavDocument webdavDocument) {
        this.webdavDocument = webdavDocument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}