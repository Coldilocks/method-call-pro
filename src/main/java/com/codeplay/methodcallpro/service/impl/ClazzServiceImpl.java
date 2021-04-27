package com.codeplay.methodcallpro.service.impl;

import com.codeplay.methodcallpro.model.Clazz;
import com.codeplay.methodcallpro.repository.ClazzRepository;
import com.codeplay.methodcallpro.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author coldilock
 */
@Service
public class ClazzServiceImpl implements ClazzService {
    
    @Autowired
    private ClazzRepository clazzRepository;
    
    @Override
    public Clazz addNewClazz(Clazz clazz) {
        return clazzRepository.save(clazz);
    }

    @Override
    public void addNewClazzList(List<Clazz> clazzList) {
        clazzRepository.saveAll(clazzList);
    }

    @Override
    public List<Clazz> getAllClazzs() {
        List<Clazz> clazzList = new ArrayList<>();
        clazzRepository.findAll().forEach(clazzList::add);
        return clazzList;
    }

    @Override
    public Clazz updateClazz(Clazz clazz) {
        Optional<Clazz> clazzData = this.getClazzById(clazz.getId());
        if(clazzData.isPresent()){
            Clazz _clazz = clazzData.get();
//            _clazz.setCallerName(clazz.getCallerName());
//            _clazz.setCallerSignature(clazz.getCallerSignature());
//            _clazz.setCalleeName(clazz.getCalleeName());
//            _clazz.setCalleeSignature(clazz.getCalleeSignature());
            return clazzRepository.save(_clazz);
        }
        return null;
    }

    @Override
    public void deleteClazzById(String clazzId) {
        clazzRepository.deleteById(clazzId);
    }

    @Override
    public Optional<Clazz> getClazzById(String clazzId) {
        return clazzRepository.findById(clazzId);
    }

    @Override
    public Clazz updateClazzById(String clazzId, Clazz clazz) {
        Optional<Clazz> clazzData = this.getClazzById(clazzId);
        if(clazzData.isPresent()){
            Clazz _clazz = clazzData.get();
//            _clazz.setCallerName(clazz.getCallerName());
//            _clazz.setCallerSignature(clazz.getCallerSignature());
//            _clazz.setCalleeName(clazz.getCalleeName());
//            _clazz.setCalleeSignature(clazz.getCalleeSignature());
            return clazzRepository.save(_clazz);
        }
        return null;
    }
}
