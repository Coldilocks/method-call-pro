package com.codeplay.methodcallpro.repository;

import com.codeplay.methodcallpro.model.Field;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author coldilock
 */
@Repository
public interface FieldRepository extends CrudRepository<Field, String> {
}
