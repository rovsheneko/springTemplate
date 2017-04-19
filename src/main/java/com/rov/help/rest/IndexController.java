package com.rov.help.rest;

/**
 * Created by rov on 3/15/2017.
 */

import com.rov.help.domain.Visitor;
import com.rov.help.service.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    VisitorRepository visitorRepository;

    @Value("${app.name}")
    private String name;

    @RequestMapping("/")
    public String index() {
        return "hi there Tiffanichka from app" + name;
    }

    @RequestMapping("/user")
    public String user(@RequestParam("name") String uName) {
        Visitor v = new Visitor(uName, uName);
        visitorRepository.save(v);
        return "hi there Tiffanichka from app" + uName;
    }

    @RequestMapping("/greet")
    public String greet(@RequestParam("name") String uName) {
        List<Visitor> visitors = visitorRepository.findByFirstName(uName);
        if(visitors.isEmpty()) return "could not find the user " + uName;
        Visitor v = visitors.get(0);
        return "hi there Tiffanichka from app" + v.toString();
    }
}