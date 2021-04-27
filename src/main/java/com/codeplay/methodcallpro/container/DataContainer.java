package com.codeplay.methodcallpro.container;

import com.codeplay.methodcallpro.model.Clazz;
import com.codeplay.methodcallpro.model.Field;
import com.codeplay.methodcallpro.model.Method;
import com.codeplay.methodcallpro.model.MethodCall;
import com.codeplay.methodcallpro.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coldilock
 * todo: change to singleton instead of static
 */
public class DataContainer {

    public static String projectName;

    public static Clazz currentClazz;

    public static List<Field> fieldList = new ArrayList<>();

    public static List<Method> methodList = new ArrayList<>();

    public static List<MethodCall> methodCallList = new ArrayList<>();

    // private static Map<String, String> methodSignature2Id = new HashMap<>();

    // public static Map<String, List<String>> methodCallMap = new HashMap<>();

    public static String getProjectName() {
        return projectName;
    }

    // todo: random projectName for single file
    public static void setProjectName(String projectName) {
        DataContainer.projectName = projectName;
    }

    public static void setClazz(String clazzName, String packageName){
        currentClazz = new Clazz(StringUtils.createId(), clazzName, packageName, packageName.concat(".").concat(clazzName), projectName);
    }

    public static void addField(String fieldName, String fieldType){
        Field field = new Field(StringUtils.createId(), fieldName, fieldType, currentClazz.getId(), currentClazz.getQualifiedName(), projectName);
        fieldList.add(field);
    }

    public static void addMethod(String methodName, String methodSignature){
        Method method = new Method(StringUtils.createId(), methodName, methodSignature, currentClazz.getId(), currentClazz.getQualifiedName(), projectName);
        methodList.add(method);
    }

    public static void addMethodCall(String callerName, String callerSignature, String calleeName, String calleeSignature){
        // methodCallMap.computeIfAbsent(callerSignature, k -> new ArrayList<>()).add(calleeSignature);

        MethodCall methodCall = new MethodCall(
                StringUtils.createId(),
                callerName, callerSignature,
                calleeName, calleeSignature,
                projectName, currentClazz.getId());

        methodCallList.add(methodCall);
    }

}
