package com.codeplay.methodcallpro.controller;

import com.codeplay.methodcallpro.service.ClazzInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author coldilock
 */
@RestController
@RequestMapping(path = "/api/v1")
public class ClazzInfoController {

    @Autowired
    private ClazzInfoService clazzInfoService;

    /**
     * get clazz information(fields, methods, method calls) and save to database
     * @return
     */
    @RequestMapping(path="/clazzinfo", method = RequestMethod.POST)
    public ResponseEntity<String> getAllMethodCalls(){
        String projectName = "SingleFile";
        String filePath = "/Users/coldilock/Documents/Code/Github/CodeRecPro/src/main/java/dataset/HoleCreator.java";
        boolean checkJdkAPI = true;
        boolean checkThirdPartyAPI = true;
        boolean checkUserDefinedAPI = true;

        try{
            clazzInfoService.saveClazzInfo(projectName, filePath, checkJdkAPI, checkThirdPartyAPI, checkUserDefinedAPI);
            return ResponseEntity.status(HttpStatus.CREATED).body("添加成功！");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加失败！");
        }
    }

    //todo: project scope clazz info (multi clazz)
    //todo: single file scope clazz info (single clazz)


}
