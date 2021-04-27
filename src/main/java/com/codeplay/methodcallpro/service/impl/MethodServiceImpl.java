package com.codeplay.methodcallpro.service.impl;

import com.codeplay.methodcallpro.model.Method;
import com.codeplay.methodcallpro.repository.MethodRepository;
import com.codeplay.methodcallpro.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author coldilock
 */
@Service
public class MethodServiceImpl implements MethodService {
    @Autowired
    private MethodRepository methodRepository;

    @Override
    public Method addNewMethod(Method method) {
        return methodRepository.save(method);
    }

    @Override
    public void addMethodList(List<Method> methodList) {
        methodRepository.saveAll(methodList);
    }

    @Override
    public List<Method> getAllMethods() {
        List<Method> methodList = new ArrayList<>();
        methodRepository.findAll().forEach(methodList::add);
        return methodList;
    }

    @Override
    public List<Method> getMethodsByProjectName(String projectName) {
        return methodRepository.findMethodsByProjectName(projectName);
    }

    @Override
    public Method updateMethod(Method method) {
        Optional<Method> methodData = this.getMethodById(method.getId());
        if(methodData.isPresent()){
            Method _method = methodData.get();
//            _method.setCallerName(method.getCallerName());
//            _method.setCallerSignature(method.getCallerSignature());
//            _method.setCalleeName(method.getCalleeName());
//            _method.setCalleeSignature(method.getCalleeSignature());
            return methodRepository.save(_method);
        }
        return null;
    }

    @Override
    public void deleteMethodById(String methodId) {
        methodRepository.deleteById(methodId);
    }

    @Override
    public Optional<Method> getMethodById(String methodId) {
        return methodRepository.findById(methodId);
    }

    @Override
    public Method updateMethodById(String methodId, Method method) {
        Optional<Method> methodData = this.getMethodById(methodId);
        if(methodData.isPresent()){
            Method _method = methodData.get();
//            _method.setCallerName(method.getCallerName());
//            _method.setCallerSignature(method.getCallerSignature());
//            _method.setCalleeName(method.getCalleeName());
//            _method.setCalleeSignature(method.getCalleeSignature());
            return methodRepository.save(_method);
        }
        return null;
    }
}
