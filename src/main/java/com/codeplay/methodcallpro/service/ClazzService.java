package com.codeplay.methodcallpro.service;

import com.codeplay.methodcallpro.model.Clazz;

import java.util.List;
import java.util.Optional;

/**
 * @author coldilock
 */
public interface ClazzService {
    Clazz addNewClazz(Clazz clazz);

    void addNewClazzList(List<Clazz> clazzList);

    List<Clazz> getAllClazzs();

    Clazz updateClazz(Clazz clazz);

    void deleteClazzById(String clazzId);

    Optional<Clazz> getClazzById(String clazzId);

    Clazz updateClazzById(String clazzId, Clazz clazz);
}
