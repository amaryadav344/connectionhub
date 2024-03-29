package com.business.utils.models.Entity;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class IMessageParameter {
    @JacksonXmlProperty(isAttribute = true)
    String label;
    @JacksonXmlProperty(isAttribute = true)
    String objectField;
    @JacksonXmlProperty(isAttribute = true)
    String dataFormat;

    public IMessageParameter(String label, String objectField, String dataFormat) {
        this.label = label;
        this.objectField = objectField;
        this.dataFormat = dataFormat;
    }

    public IMessageParameter() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getObjectField() {
        return objectField;
    }

    public void setObjectField(String objectField) {
        this.objectField = objectField;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }
}
