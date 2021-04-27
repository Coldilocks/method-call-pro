package com.codeplay.methodcallpro.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author coldilock
 */
@Entity
public class MethodCallChain {

  @Id
  private String id;
  private String callerId;
  private String calleeId;
  private String callerName;
  private String calleeName;
  private String callerSignature;
  private String calleeSignature;
  private String projectName;

  public MethodCallChain() {
  }

  public MethodCallChain(String id, String callerId, String calleeId, String callerName, String calleeName, String callerSignature, String calleeSignature, String projectName) {
    this.id = id;
    this.callerId = callerId;
    this.calleeId = calleeId;
    this.callerName = callerName;
    this.calleeName = calleeName;
    this.callerSignature = callerSignature;
    this.calleeSignature = calleeSignature;
    this.projectName = projectName;
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


  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

}
