package com.codeplay.methodcallpro.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author coldilock
 */
@Entity
public class Field {
  @Id
  private String id;
  private String fieldName;
  private String fieldType;
  private String accessType;
  private long isStatic;
  private long isFinal;
  private String clazzId;
  private String clazzQualifiedName;
  private String projectName;

  public Field(){}

  public Field(String id, String fieldName, String fieldType,
               String accessType, boolean isStatic, boolean isFinal,
               String clazzId, String clazzQualifiedName, String projectName){
    this.id = id;
    this.fieldName = fieldName;
    this.fieldType = fieldType;
    this.accessType = accessType;
    this.isStatic = (isStatic ? 1 : 0);
    this.isFinal = (isFinal ? 1 : 0);
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


  public String getFieldName() {
    return fieldName;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }


  public String getFieldType() {
    return fieldType;
  }

  public void setFieldType(String fieldType) {
    this.fieldType = fieldType;
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


  public long getIsFinal() {
    return isFinal;
  }

  public void setIsFinal(long isFinal) {
    this.isFinal = isFinal;
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
