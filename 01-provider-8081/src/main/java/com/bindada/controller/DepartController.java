package com.bindada.controller;

import com.bindada.pojo.Depart;
import com.bindada.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("/provider/depart")
@RestController
public class DepartController {
    @Autowired
    private DepartService service;

    @Autowired
    private DiscoveryClient client;

    @PostMapping("/save")
    public boolean saveHandle(@RequestBody  Depart depart){
        return service.saveDepart(depart);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteHandle(@PathVariable("id") int id){
        return service.removeDepartId(id);
    }

    @PutMapping("/update")
    public boolean updateHandle(@RequestBody Depart depart){
        return service.modifyDepart(depart);
    }

    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id){
        return service.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> listHandle(){
        return service.getAllDepart();
    }


    @GetMapping("/discovery")
    public List<String> discoveryHandle(){
        List<String> services = client.getServices();
        for (String serviceName : services) {
            List<ServiceInstance> serviceInstances = client.getInstances(serviceName);
            for (ServiceInstance instance : serviceInstances) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("serviceName",serviceName);
                map.put("instanceId",instance.getInstanceId());
                map.put("serviceId",instance.getServiceId());
                map.put("serviceHost",instance.getHost());
                map.put("serviceUrl",instance.getUri());
                System.out.println(map);
            }
        }
        return services;
    }
}
