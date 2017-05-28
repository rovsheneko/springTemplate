package com.rov.help.rest;

import com.rov.help.domain.Visitor;
import com.rov.help.service.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rov on 5/12/2017.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    VisitorRepository visitorRepository;

    @Value("${app.name}")
    private String name;

    @RequestMapping(method=RequestMethod.GET)
    public List<Visitor> getUsers() {
        List<Visitor> visitors = new ArrayList<>();
        visitorRepository.findAll().iterator().forEachRemaining(v -> visitors.add(v));
        return visitors;
    }

    @RequestMapping(method=RequestMethod.POST)
    public Visitor addUser(@RequestBody Visitor v, @RequestParam Map<String,String> params) {
        System.out.println("POST for request body: " + v + " and the request param: " + params);
        visitorRepository.save(v);
        return v;
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public boolean deleteUser(@RequestParam Map<String,String> params) {
        System.out.println("DELETE for the request param: " + params);
        visitorRepository.delete(Long.valueOf(params.get("id")));
        return true;
    }
}
