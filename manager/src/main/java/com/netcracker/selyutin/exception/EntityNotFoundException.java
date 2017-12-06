package com.netcracker.selyutin.exception;

import com.netcracker.selyutin.constant.ExceptionMessage;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(Class<?> classType, int id) {
        super(classType.getSimpleName() + ExceptionMessage.ENTITY_NOT_FOUND + id);
    }
}
