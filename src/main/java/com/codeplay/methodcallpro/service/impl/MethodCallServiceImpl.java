package com.codeplay.methodcallpro.service.impl;

import com.codeplay.methodcallpro.model.Method;
import com.codeplay.methodcallpro.model.MethodCall;
import com.codeplay.methodcallpro.repository.MethodCallRepository;
import com.codeplay.methodcallpro.service.MethodCallService;
import com.codeplay.methodcallpro.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author coldilock
 */
@Service
public class MethodCallServiceImpl implements MethodCallService {

    @Autowired
    private MethodCallRepository methodCallRepository;

    @Autowired
    private MethodService methodService;

    @Override
    public MethodCall addNewMethodCall(MethodCall methodCall) {
        return methodCallRepository.save(methodCall);
    }

    @Override
    public void addMethodCallList(List<MethodCall> methodCallList) {
        methodCallRepository.saveAll(methodCallList);
    }

    @Override
    public List<MethodCall> getAllMethodCalls() {
        List<MethodCall> methodCallList = new ArrayList<>();
        methodCallRepository.findAll().forEach(methodCallList::add);
        return methodCallList;
    }

    @Override
    public List<MethodCall> getMethodCallsByProjectName(String projectName) {
        return methodCallRepository.findMethodCallsByProjectName(projectName);
    }

    @Override
    public MethodCall updateMethodCall(MethodCall methodCall) {
        Optional<MethodCall> methodCallData = this.getMethodCallById(methodCall.getId());
        if(methodCallData.isPresent()){
            MethodCall _methodCall = methodCallData.get();
            _methodCall.setCallerName(methodCall.getCallerName());
            _methodCall.setCallerSignature(methodCall.getCallerSignature());
            _methodCall.setCalleeName(methodCall.getCalleeName());
            _methodCall.setCalleeSignature(methodCall.getCalleeSignature());
            return methodCallRepository.save(_methodCall);
        }
        return null;
    }

    @Override
    public void deleteMethodCallById(String methodCallId) {
        methodCallRepository.deleteById(methodCallId);
    }

    @Override
    public Optional<MethodCall> getMethodCallById(String methodCallId) {
        return methodCallRepository.findById(methodCallId);
    }

    @Override
    public MethodCall updateMethodCallById(String methodCallId, MethodCall methodCall) {
        Optional<MethodCall> methodCallData = this.getMethodCallById(methodCallId);
        if(methodCallData.isPresent()){
            MethodCall _methodCall = methodCallData.get();
            _methodCall.setCallerName(methodCall.getCallerName());
            _methodCall.setCallerSignature(methodCall.getCallerSignature());
            _methodCall.setCalleeName(methodCall.getCalleeName());
            _methodCall.setCalleeSignature(methodCall.getCalleeSignature());
            return methodCallRepository.save(_methodCall);
        }
        return null;
    }

    @Override
    public void updateCalleeIdAndIsCalleeUserDefined(String projectName) {
        List<Method> methodList = methodService.getMethodsByProjectName(projectName);

        // key: method_signature value: id
        Map<String, String> methodSignature2Id = methodList.stream()
                .collect(Collectors.toMap(Method::getMethodSignature, Method::getId));

        // key: method_signature value: clazz_id
        Map<String, String> methodSignature2ClazzId = methodList.stream()
                .collect(Collectors.toMap(Method::getMethodSignature, Method::getClazzId));

        List<MethodCall> methodCallList = methodCallRepository.findMethodCallsByProjectName(projectName);

        for(MethodCall methodCall : methodCallList) {
            // update caller_id
            if(methodSignature2Id.containsKey(methodCall.getCallerSignature())){
                methodCall.setCallerId(methodSignature2Id.get(methodCall.getCallerSignature()));
            }

            // update callee_id, callee_clazz_id,  is_callee_user_defined_method
            if(methodSignature2Id.containsKey(methodCall.getCalleeSignature())){
                methodCall.setCalleeId(methodSignature2Id.get(methodCall.getCalleeSignature()));
                methodCall.setCalleeClazzId(methodSignature2ClazzId.get(methodCall.getCalleeSignature()));
                methodCall.setIsCalleeUserDefinedMethod(1);
            }
        }
        methodCallRepository.saveAll(methodCallList);
    }

    @Override
    public List<MethodCall> getMethodCallsInsideProject(String projectName) {
        return methodCallRepository.findMethodCallsByProjectNameAndCalleeIdIsNotNull(projectName);
    }
}
