package com.tom.curso.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    //attributes

    private List<FieldMessage> fieldMessages =  new ArrayList<>();

    // constructors

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
        //TODO Auto-generated constructor stub
    }

    //methods

    public void addErrors(String fieldMessage, String defaultMessage){
        fieldMessages.add(new FieldMessage(fieldMessage, defaultMessage));
    }

    public List<FieldMessage> getFieldMessages() {
        return fieldMessages;
    }

    public void setFieldMessages(List<FieldMessage> fieldMessages) {
        this.fieldMessages = fieldMessages;
    }

    
}
