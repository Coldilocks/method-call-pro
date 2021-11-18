package com.codeplay.methodcallpro.controller;

import com.codeplay.methodcallpro.model.MethodCall;
import com.codeplay.methodcallpro.service.ClazzService;
import com.codeplay.methodcallpro.service.FieldService;
import com.codeplay.methodcallpro.service.MethodCallService;
import com.codeplay.methodcallpro.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author coldilock
 */

@RestController
@RequestMapping(path = "/api/v1")
public class MethodCallChainController {
    @Autowired
    private ClazzService clazzService;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private MethodService methodService;
    @Autowired
    private MethodCallService methodCallService;

    @RequestMapping(path="/methodcall/{projectName}", method = RequestMethod.GET)
    public ResponseEntity<List<MethodCall>> getAllMethodCalls(@PathVariable String projectName){
        try{
            List<MethodCall> methodCallList = methodCallService.getMethodCallsByProjectName(projectName);
            if(methodCallList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(methodCallList, HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
