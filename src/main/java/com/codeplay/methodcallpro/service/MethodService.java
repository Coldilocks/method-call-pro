package com.codeplay.methodcallpro.service;

import com.codeplay.methodcallpro.model.Method;

import java.util.List;
import java.util.Optional;

/**
 * @author coldilock
 */
public interface MethodService {
    Method addNewMethod(Method method);

    void addMethodList(List<Method> methodList);

    List<Method> getAllMethods();

    List<Method> getMethodsByProjectName(String projectName);

    Method updateMethod(Method method);

    void deleteMethodById(String methodId);

    Optional<Method> getMethodById(String methodId);

    Method updateMethodById(String methodId, Method method);
}
