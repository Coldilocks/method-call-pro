package com.codeplay.methodcallpro.service;

import com.codeplay.methodcallpro.model.MethodCall;

import java.util.List;
import java.util.Optional;

/**
 * @author coldilock
 */
public interface MethodCallService {
    MethodCall addNewMethodCall(MethodCall methodCall);

    void addMethodCallList(List<MethodCall> methodCallList);

    List<MethodCall> getAllMethodCalls();

    List<MethodCall> getMethodCallsByProjectName(String projectName);

    MethodCall updateMethodCall(MethodCall methodCall);

    void deleteMethodCallById(String methodCallId);

    Optional<MethodCall> getMethodCallById(String methodCallId);

    MethodCall updateMethodCallById(String methodCallId, MethodCall methodCall);

    void updateCalleeIdAndIsCalleeUserDefined(String projectName);

    List<MethodCall> getMethodCallsInsideProject(String projectName);

}
