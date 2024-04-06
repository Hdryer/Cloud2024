package com.bindada.controller;

import com.bindada.pojo.Depart;
import com.bindada.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/provider/depart")
@RestController
public class DepartController {
    @Autowired
    private DepartService service;

    @PostMapping("/")
    public boolean saveHandle(@RequestBody  Depart depart){
        return service.saveDepart(depart);
    }

    @DeleteMapping("/{id}")
    public boolean deleteHandle(@PathVariable("id") int id){
        return service.removeDepartId(id);
    }

    @PutMapping("/")
    public boolean updateHandle(@RequestBody Depart depart){
        return service.modifyDepart(depart);
    }

    @GetMapping("/{id}")
    public Depart getHandle(@PathVariable("id") int id){
        return service.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> listHandle(){
        return service.getAllDepart();
    }
}
