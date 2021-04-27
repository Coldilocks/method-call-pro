package com.codeplay.methodcallpro;

import com.codeplay.methodcallpro.container.DataContainer;
import com.codeplay.methodcallpro.model.Method;
import com.codeplay.methodcallpro.model.MethodCall;
import com.codeplay.methodcallpro.service.ClazzService;
import com.codeplay.methodcallpro.service.FieldService;
import com.codeplay.methodcallpro.service.MethodCallService;
import com.codeplay.methodcallpro.service.MethodService;
import com.codeplay.methodcallpro.visitors.CompleteVisitor;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author coldilock
 */
@SpringBootTest
public class MethodCallTests {

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private MethodCallService methodCallService;

    @Autowired
    private MethodService methodService;

    private static boolean checkJdkAPI = true;
    // 不添加第三方类型会造成识别的自定义方法调用结果变少
    private static boolean checkThirdPartyAPI = true;
    private static boolean checkUserDefinedAPI = true;

    private static String filePath = "/Users/coldilock/Documents/Code/Github/CodeRecPro/src/main/java/dataset/HoleCreator.java";

    @Test
    void getMethodListByProjectName() {
        String projectName = "SingleFile";
        methodService.getMethodsByProjectName(projectName).forEach(method -> System.out.println(method.getMethodSignature()));
    }

    @Test
    void getMethodCallListByProjectName(){
        String projectName = "SingleFile";
        methodCallService.getMethodCallsByProjectName(projectName).forEach(methodCall -> System.out.println(methodCall.getCallerName() + "\t->\t" + methodCall.getCalleeName()));
    }

    @Test
    void saveMethodCall() throws IOException {
        runVisitor();
        clazzService.addNewClazz(DataContainer.currentClazz);
        DataContainer.methodList.forEach(method -> methodService.addNewMethod(method));
        DataContainer.fieldList.forEach(field -> fieldService.addNewField(field));
        DataContainer.methodCallList.forEach(methodCall -> methodCallService.addNewMethodCall(methodCall));

    }

    @Test
    void updateIsCalleeUserDefinedForMethodCall() {

        String projectName = "SingleFile";

        List<Method> methodList = methodService.getMethodsByProjectName(projectName);

        Map<String, String> methodSignature2Id = methodList.stream()
                .collect(Collectors.toMap(Method::getMethodSignature, Method::getId));

//        List<MethodCall> methodCallList = methodCallRepository.findMethodCallsByProjectName(projectName);
        List<MethodCall> methodCallList = methodCallService.getMethodCallsByProjectName(projectName);


        for(MethodCall methodCall : methodCallList) {
            if(methodSignature2Id.containsKey(methodCall.getCalleeSignature())){
                methodCall.setCalleeId(methodSignature2Id.get(methodCall.getCalleeSignature()));
                methodCall.setIsCalleeUserDefinedMethod(1);
            }
        }

//        methodCallRepository.saveAll(methodCallList);
        methodCallService.addMethodCallList(methodCallList);

    }

    @Test
    void getMethodCallsInsideProject(){
        String projectName = "SingleFile";
        methodCallService.getMethodCallsInsideProject(projectName).forEach(methodCall ->
                System.out.printf("%s:%s -> %s:%s\n", methodCall.getCallerId(), methodCall.getCallerName(),
                        methodCall.getCalleeId(), methodCall.getCalleeName()));
    }

    void runVisitor() throws IOException {
        CombinedTypeSolver typeSolver = new CombinedTypeSolver();

        // solve qualified name from jdk api
        if(checkJdkAPI){
            typeSolver.add(new ReflectionTypeSolver());
        }
        // solve qualified name from third party api
        if(checkThirdPartyAPI){
            // add the jar file path
            String firstJarFile = "/Users/coldilock/Documents/Code/Github/CodeRecPro/src/main/resources/input/jarfiles/javaparser-core-3.16.1.jar";
            String secondJarFile = "/Users/coldilock/Documents/Code/Github/CodeRecPro/src/main/resources/input/jarfiles/commons-collections4-4.4.jar";

            List<String> jarFileList = new ArrayList<>();
            jarFileList.add(firstJarFile);
            jarFileList.add(secondJarFile);

            for(String jarFilePath : jarFileList){
                typeSolver.add(JarTypeSolver.getJarTypeSolver(jarFilePath));
            }
        }

        // solve qualified name from user-defined class and method
        if(checkUserDefinedAPI){
            // add the source root path of filePath
            String projectSrcPath = "/Users/coldilock/Documents/Code/Github/CodeRecPro/src/main/java";

            typeSolver.add(new JavaParserTypeSolver(new File(projectSrcPath)));
        }

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);

        CompilationUnit cu = StaticJavaParser.parse(new File(filePath));

//        cu.getTypes().forEach(type -> {
//            type.getMethods().forEach(method -> {
//                MethodVisitor visitor = new MethodVisitor();
//                method.accept(visitor, null);
//            });
//        });

        CompleteVisitor visitor = new CompleteVisitor();
        cu.accept(visitor, null);
    }
}
