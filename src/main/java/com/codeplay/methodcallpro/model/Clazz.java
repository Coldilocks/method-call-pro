package com.codeplay.methodcallpro.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author coldilock
 */
@Entity
public class Clazz {
  @Id
  private String id;
  private String clazzName;
  private String packageName;
  private String qualifiedName;
  private String superClazzName;
  private String implementInterfaceList;
  private long isAbstract;
  private String projectName;

  public Clazz(){

  }

  public Clazz(String id, String clazzName, String packageName, String qualifiedName, String projectName){
    this.id = id;
    this.clazzName = clazzName;
    this.packageName = packageName;
    this.qualifiedName = qualifiedName;
    this.projectName = projectName;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getClazzName() {
    return clazzName;
  }

  public void setClazzName(String clazzName) {
    this.clazzName = clazzName;
  }


  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }


  public String getQualifiedName() {
    return qualifiedName;
  }

  public void setQualifiedName(String qualifiedName) {
    this.qualifiedName = qualifiedName;
  }


  public String getSuperClazzName() {
    return superClazzName;
  }

  public void setSuperClazzName(String superClazzName) {
    this.superClazzName = superClazzName;
  }


  public String getImplementInterfaceList() {
    return implementInterfaceList;
  }

  public void setImplementInterfaceList(String implementInterfaceList) {
    this.implementInterfaceList = implementInterfaceList;
  }


  public long getIsAbstract() {
    return isAbstract;
  }

  public void setIsAbstract(long isAbstract) {
    this.isAbstract = isAbstract;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
