package com.codeplay.methodcallpro.repository;

import com.codeplay.methodcallpro.model.Method;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author coldilock
 */
@Repository
public interface MethodRepository extends CrudRepository<Method, String> {
    List<Method> findMethodsByProjectName(String projectName);
}
