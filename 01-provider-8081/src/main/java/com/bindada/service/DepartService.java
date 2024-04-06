package com.bindada.service;

import com.bindada.pojo.Depart;

import java.util.List;

public interface DepartService {

    public boolean saveDepart(Depart depart);

    public boolean removeDepartId(int id);

    public boolean modifyDepart(Depart depart);

    public  Depart getDepartById(int id);

    public List<Depart> getAllDepart();
}
