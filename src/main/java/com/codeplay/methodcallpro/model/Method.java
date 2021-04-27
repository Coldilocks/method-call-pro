package com.codeplay.methodcallpro.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author coldilock
 */
@Entity
public class Method {
  @Id
  private String id;
  private String methodName;
  private String methodSignature;
  private String parameters;
  private String returnType;
  private String accessType;
  private long isStatic;
  private long isAbstract;
  private String clazzId;
  private String clazzQualifiedName;
  private String projectName;

  public Method(){

  }

  public Method(String id, String methodName, String methodSignature,
                String returnType, String accessType,
                boolean isStatic, boolean isAbstract,
                String clazzId, String clazzQualifiedName,
                String projectName){
    this.id = id;
    this.methodName = methodName;
    this.methodSignature = methodSignature;
    this.returnType = returnType;
    this.accessType = accessType;
    this.isStatic = (isStatic ? 1 : 0);
    this.isAbstract = (isAbstract? 1 : 0);
    this.clazzId = clazzId;
    this.clazzQualifiedName = clazzQualifiedName;
    this.projectName = projectName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }


  public String getMethodSignature() {
    return methodSignature;
  }

  public void setMethodSignature(String methodSignature) {
    this.methodSignature = methodSignature;
  }


  public String getParameters() {
    return parameters;
  }

  public void setParameters(String parameters) {
    this.parameters = parameters;
  }


  public String getReturnType() {
    return returnType;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }


  public String getAccessType() {
    return accessType;
  }

  public void setAccessType(String accessType) {
    this.accessType = accessType;
  }


  public long getIsStatic() {
    return isStatic;
  }

  public void setIsStatic(long isStatic) {
    this.isStatic = isStatic;
  }


  public long getIsAbstract() {
    return isAbstract;
  }

  public void setIsAbstract(long isAbstract) {
    this.isAbstract = isAbstract;
  }


  public String getClazzId() {
    return clazzId;
  }

  public void setClazzId(String clazzId) {
    this.clazzId = clazzId;
  }


  public String getClazzQualifiedName() {
    return clazzQualifiedName;
  }

  public void setClazzQualifiedName(String clazzQualifiedName) {
    this.clazzQualifiedName = clazzQualifiedName;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

}
