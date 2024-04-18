package com.bindada.controller;

import com.bindada.pojo.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/consumer/depart")
public class DepartController {
    @Autowired
    private RestTemplate template;

    @Autowired
    private DiscoveryClient client;

    //直连方式
    //private  static final String SERVICE_PROCIER="http://localhost:8081/provider/depart";

    //微服务方式
    private  static final String SERVICE_PROCIER="http://depart-provider/provider/depart";

    @PostMapping("/save")
    public boolean saveHandle(@RequestBody Depart depart){
        String url = SERVICE_PROCIER+"/save";
        return template.postForObject(url,depart,Boolean.class);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHandle(@PathVariable("id") int id){
        template.delete(SERVICE_PROCIER+"/delete/"+id);
    }
    @PutMapping("/update")
    public void updateHandle(@RequestBody Depart depart){
        String url = SERVICE_PROCIER+"/update";
        template.put(url,depart);
    }
    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id){
        String url = SERVICE_PROCIER+"/get/"+id;
        return template.getForObject(url, Depart.class);
    }

    @GetMapping("/list")
    public List<Depart> listHandle(){
        String url = SERVICE_PROCIER+"/list";
        return template.getForObject(url,List.class);
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
