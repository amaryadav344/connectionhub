package com.webstudio.connectionhub.Models;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ICollection {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String entity;
    @JacksonXmlProperty(isAttribute = true)
    String objectField;
    @JacksonXmlProperty(isAttribute = true)
    String dataType;

    public ICollection(String name, String entity, String objectField, String dataType) {
        this.name = name;
        this.entity = entity;
        this.objectField = objectField;
        this.dataType = dataType;
    }

    public ICollection() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getObjectField() {
        return objectField;
    }

    public void setObjectField(String objectField) {
        this.objectField = objectField;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
