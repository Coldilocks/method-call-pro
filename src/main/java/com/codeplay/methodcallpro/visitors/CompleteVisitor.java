package com.codeplay.methodcallpro.visitors;

import com.codeplay.methodcallpro.container.DataContainer;
import com.codeplay.methodcallpro.util.StringUtils;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * @author coldilock
 */
public class CompleteVisitor extends VoidVisitorAdapter {

    private String packageName = "";

    private String currentMethodName = "";

    private String currentMethodSignature = "";

    @Override
    public void visit(PackageDeclaration n, Object arg) {

        packageName = n.getNameAsString();

        super.visit(n, arg);
    }

    @Override
    public void visit(ClassOrInterfaceDeclaration n, Object arg) {

        String clazzName = n.getNameAsString();

        if(StringUtils.checkNodeName(clazzName)){
            // System.out.printf("[Class]: %s%n", className);
            DataContainer.setClazz(clazzName, packageName);
        }

        super.visit(n, arg);
    }

    /**
     * Get fields
     * @param n
     * @param arg
     */
    @Override
    public void visit(FieldDeclaration n, Object arg) {
        super.visit(n, arg);

        // n.getVariables()

        String fieldName = "_";
        String fieldType;
        try{
            fieldType = n.resolve().getType().describe();
            fieldName = n.resolve().getName();
        } catch (Exception e){
            fieldType = "UnsolvedType.In.FieldDeclaration.field";
        }

        if(StringUtils.checkNodeName(fieldType)){
            // System.out.printf("\t[Field]: %s [filedType]: %s%n", fieldName, fieldType);
            DataContainer.addField(fieldName, fieldType);
        }
    }

    /**
     *
     * @param n
     * @param arg
     */
    @Override
    public void visit(ConstructorDeclaration n, Object arg) {
        super.visit(n, arg);
    }

    /**
     * Get methods
     * @param n
     * @param arg
     */
    @Override
    public void visit(MethodDeclaration n, Object arg) {
        String methodName = n.getNameAsString();
        String methodReturnType = n.getType().toString();
        String methodSignature;
        try{
            methodSignature = n.resolve().getQualifiedSignature();
            // System.out.println("methodReturnType: " + n.resolve().getReturnType().describe());
        } catch (Exception e){
            methodSignature = "UnsolvedType.In.MethodDeclaration.method()";
        }

        if(StringUtils.checkNodeName(methodSignature)){
            // System.out.printf("\t[caller]: %s [methodReturnType]: %s [methodSignature]: %s%n", methodName, methodReturnType, methodSignature);
            currentMethodName = methodName;
            currentMethodSignature = methodSignature;
            DataContainer.addMethod(currentMethodName, currentMethodSignature);
        }

        super.visit(n, arg);
    }

    /**
     * Get method calls
     * @param n
     * @param arg
     */
    @Override
    public void visit(MethodCallExpr n, Object arg) {
        super.visit(n, arg);

        String methodName = n.getNameAsString();

        String methodReturnType = "";
        String methodSignature;
        try{
            methodSignature = n.resolve().getQualifiedSignature();
            methodReturnType = n.resolve().getReturnType().describe();
        } catch (Exception e){
            methodSignature = "UnsolvedType.In.MethodCallExpr.method()";
        }

        if(StringUtils.checkNodeName(methodSignature)){
            // System.out.printf("\t[calleeMethodName]: %s [methodReturnType]: %s [methodSignature]: %s%n", methodName, methodReturnType, methodSignature);
            // System.out.printf("\t\t[callee]: %s %n", methodSignature);
            DataContainer.addMethodCall(currentMethodName, currentMethodSignature, methodName, methodSignature);
        }
    }

    @Override
    public void visit(NameExpr n, Object arg) {
        super.visit(n, arg);
    }
}

