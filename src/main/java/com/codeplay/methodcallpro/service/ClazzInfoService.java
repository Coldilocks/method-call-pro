package com.codeplay.methodcallpro.service;

import java.io.IOException;

/**
 * @author coldilock
 */
public interface ClazzInfoService {

    /**
     * get clazz information (including fields, methods and method calls)
     */
    void saveClazzInfo(String projectName, String filePath, boolean checkJdkAPI, boolean checkThirdPartyAPI, boolean checkUserDefinedAPI) throws IOException;
}
