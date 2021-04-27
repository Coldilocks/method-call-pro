package com.codeplay.methodcallpro.repository;

import com.codeplay.methodcallpro.model.Clazz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author coldilock
 */
@Repository
public interface ClazzRepository extends CrudRepository<Clazz, String> {
}
