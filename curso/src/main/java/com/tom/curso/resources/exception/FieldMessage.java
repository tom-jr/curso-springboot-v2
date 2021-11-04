package com.tom.curso.resources.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

    // attributes

    private String fieldMessage;

    private String defaultMessage;


    public FieldMessage() {
    }

    // constructors

    public FieldMessage(String fieldMessage, String defaultMessage) {
        this.fieldMessage = fieldMessage;
        this.defaultMessage = defaultMessage;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }

    // getters and setters

    public String getDefaulMessage() {
        return defaultMessage;
    }

    public void setDefaulMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    
    
}
