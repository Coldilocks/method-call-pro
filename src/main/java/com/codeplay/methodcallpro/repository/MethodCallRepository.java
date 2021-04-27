package com.codeplay.methodcallpro.repository;

import com.codeplay.methodcallpro.model.MethodCall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author coldilock
 */
@Repository
public interface MethodCallRepository extends CrudRepository<MethodCall, String> {
    List<MethodCall> findMethodCallsByProjectName(String projectName);

    List<MethodCall> findMethodCallsByProjectNameAndCalleeIdIsNotNull(String projectName);
}
