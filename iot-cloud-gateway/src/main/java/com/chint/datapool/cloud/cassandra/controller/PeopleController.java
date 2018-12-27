package com.chint.datapool.cloud.cassandra.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.chint.datapool.cloud.cassandra.model.People;
import com.chint.datapool.cloud.cassandra.service.PeopleService;

 
@RestController
public class PeopleController {

//    @Autowired
//    private PeopleService peopleService;
//
//
//    @RequestMapping("/save")
//    public String save(@RequestParam("name") String name) {
//        People people = new People(name);
//        peopleService.save(people);
//        return "success";
//    }
//
//    @RequestMapping("/find")
//    public List<People> find() {
//        return peopleService.findall();
//    }
//
//    @RequestMapping("/delete")
//    public String delete() {
//        peopleService.delete();
//        return "success";
//    }
}
