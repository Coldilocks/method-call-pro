package com.codeplay.methodcallpro.service.impl;

import com.codeplay.methodcallpro.container.DataContainer;
import com.codeplay.methodcallpro.service.*;
import com.codeplay.methodcallpro.visitors.CompleteVisitor;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author coldilock
 */
@Service
public class ClazzInfoServiceImpl implements ClazzInfoService {

    @Autowired
    private ClazzService clazzService;

    @Autowired
    private FieldService fieldService;

    @Autowired
    private MethodService methodService;

    @Autowired
    private MethodCallService methodCallService;

    @Override
    public void saveClazzInfo(String projectName, String filePath, boolean checkJdkAPI, boolean checkThirdPartyAPI, boolean checkUserDefinedAPI) throws IOException {

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

        DataContainer.setProjectName(projectName);

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser.getConfiguration().setSymbolResolver(symbolSolver);

        CompilationUnit cu = StaticJavaParser.parse(new File(filePath));

        CompleteVisitor visitor = new CompleteVisitor();
        cu.accept(visitor, null);

        clazzService.addNewClazz(DataContainer.currentClazz);
        fieldService.addFieldList(DataContainer.fieldList);
        methodService.addMethodList(DataContainer.methodList);
        methodCallService.addMethodCallList(DataContainer.methodCallList);
        // update callee's ID of method call
        methodCallService.updateCalleeIdAndIsCalleeUserDefined(DataContainer.getProjectName());
    }
}
