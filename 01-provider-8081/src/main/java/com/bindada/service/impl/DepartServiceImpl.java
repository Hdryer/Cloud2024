package com.bindada.service.impl;

import com.bindada.pojo.Depart;
import com.bindada.repository.DepartRepository;
import com.bindada.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartRepository repository;
    @Override
    public boolean saveDepart(Depart depart) {
        Object obj = repository.save(depart);
        if (obj!=null)
            return true;
        return false;
    }

    @Override
    public boolean removeDepartId(int id) {
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean modifyDepart(Depart depart) {
        Object obj = repository.save(depart);
        if (obj!=null)
            return true;
        return false;
    }

    @Override
    public Depart getDepartById(int id) {
        if(repository.existsById(id)){
            return repository.getReferenceById(id);
        }
        return new Depart(null,"no this depart");
    }

    @Override
    public List<Depart> getAllDepart() {
        return repository.findAll();
    }
}
