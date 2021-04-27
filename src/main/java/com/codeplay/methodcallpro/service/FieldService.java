package com.codeplay.methodcallpro.service;

import com.codeplay.methodcallpro.model.Field;

import java.util.List;
import java.util.Optional;

/**
 * @author coldilock
 */
public interface FieldService {
    Field addNewField(Field field);

    void addFieldList(List<Field> fieldList);

    List<Field> getAllFields();

    Field updateField(Field field);

    void deleteFieldById(String fieldId);

    Optional<Field> getFieldById(String fieldId);

    Field updateFieldById(String fieldId, Field field);
}
