package com.codeplay.methodcallpro.service.impl;

import com.codeplay.methodcallpro.model.Field;
import com.codeplay.methodcallpro.repository.FieldRepository;
import com.codeplay.methodcallpro.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author coldilock
 */
@Service
public class FieldServiceImpl implements FieldService {
    @Autowired
    private FieldRepository fieldRepository;

    @Override
    public Field addNewField(Field field) {
        return fieldRepository.save(field);
    }

    @Override
    public void addFieldList(List<Field> fieldList) {
        fieldRepository.saveAll(fieldList);
    }

    @Override
    public List<Field> getAllFields() {
        List<Field> fieldList = new ArrayList<>();
        fieldRepository.findAll().forEach(fieldList::add);
        return fieldList;
    }

    @Override
    public Field updateField(Field field) {
        Optional<Field> fieldData = this.getFieldById(field.getId());
        if(fieldData.isPresent()){
            Field _field = fieldData.get();
//            _field.setCallerName(field.getCallerName());
//            _field.setCallerSignature(field.getCallerSignature());
//            _field.setCalleeName(field.getCalleeName());
//            _field.setCalleeSignature(field.getCalleeSignature());
            return fieldRepository.save(_field);
        }

        return null;

    }

    @Override
    public void deleteFieldById(String fieldId) {
        fieldRepository.deleteById(fieldId);
    }

    @Override
    public Optional<Field> getFieldById(String fieldId) {
        return fieldRepository.findById(fieldId);
    }

    @Override
    public Field updateFieldById(String fieldId, Field field) {
        Optional<Field> fieldData = this.getFieldById(fieldId);
        if(fieldData.isPresent()){
            Field _field = fieldData.get();
//            _field.setCallerName(field.getCallerName());
//            _field.setCallerSignature(field.getCallerSignature());
//            _field.setCalleeName(field.getCalleeName());
//            _field.setCalleeSignature(field.getCalleeSignature());
            return fieldRepository.save(_field);
        }
        return null;
    }
}
