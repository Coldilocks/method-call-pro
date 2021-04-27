package com.codeplay.methodcallpro.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author coldilock
 */
@Entity
public class MethodCall {
  @Id
  private String id;
  private String callerId;
  private String calleeId;
  private String callerName;
  private String calleeName;
  private String callerSignature;
  private String calleeSignature;
  private long isCalleeGetterSetter;
  private long isCalleeJdkMethod;
  private long isCalleeThirdPartyMethod;
  private long isCalleeUserDefinedMethod;
  private String projectName;
  private String callerClazzId;
  private String calleeClazzId;

  public MethodCall(){}

  public MethodCall(String id,
                    String callerName, String callerSignature,
                    String calleeName, String calleeSignature,
                    String projectName){
    this.id = id;
    this.callerName = callerName;
    this.callerSignature = callerSignature;
    this.calleeName = calleeName;
    this.calleeSignature = calleeSignature;
    this.projectName = projectName;

  }

  public MethodCall(String id,
                    String callerName, String callerSignature,
                    String calleeName, String calleeSignature,
                    String projectName, String callerClazzId){
    this.id = id;
    this.callerName = callerName;
    this.callerSignature = callerSignature;
    this.calleeName = calleeName;
    this.calleeSignature = calleeSignature;
    this.projectName = projectName;
    this.callerClazzId = callerClazzId;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getCallerId() {
    return callerId;
  }

  public void setCallerId(String callerId) {
    this.callerId = callerId;
  }


  public String getCalleeId() {
    return calleeId;
  }

  public void setCalleeId(String calleeId) {
    this.calleeId = calleeId;
  }


  public String getCallerName() {
    return callerName;
  }

  public void setCallerName(String callerName) {
    this.callerName = callerName;
  }


  public String getCalleeName() {
    return calleeName;
  }

  public void setCalleeName(String calleeName) {
    this.calleeName = calleeName;
  }


  public String getCallerSignature() {
    return callerSignature;
  }

  public void setCallerSignature(String callerSignature) {
    this.callerSignature = callerSignature;
  }


  public String getCalleeSignature() {
    return calleeSignature;
  }

  public void setCalleeSignature(String calleeSignature) {
    this.calleeSignature = calleeSignature;
  }


  public long getIsCalleeGetterSetter() {
    return isCalleeGetterSetter;
  }

  public void setIsCalleeGetterSetter(long isCalleeGetterSetter) {
    this.isCalleeGetterSetter = isCalleeGetterSetter;
  }


  public long getIsCalleeJdkMethod() {
    return isCalleeJdkMethod;
  }

  public void setIsCalleeJdkMethod(long isCalleeJdkMethod) {
    this.isCalleeJdkMethod = isCalleeJdkMethod;
  }


  public long getIsCalleeThirdPartyMethod() {
    return isCalleeThirdPartyMethod;
  }

  public void setIsCalleeThirdPartyMethod(long isCalleeThirdPartyMethod) {
    this.isCalleeThirdPartyMethod = isCalleeThirdPartyMethod;
  }


  public long getIsCalleeUserDefinedMethod() {
    return isCalleeUserDefinedMethod;
  }

  public void setIsCalleeUserDefinedMethod(long idCalleeUserDefinedMethod) {
    this.isCalleeUserDefinedMethod = idCalleeUserDefinedMethod;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getCallerClazzId() {
    return callerClazzId;
  }

  public void setCallerClazzId(String callerClassId) {
    this.callerClazzId = callerClassId;
  }

  public String getCalleeClazzId() {
    return calleeClazzId;
  }

  public void setCalleeClazzId(String calleeClassId) {
    this.calleeClazzId = calleeClassId;
  }

}
