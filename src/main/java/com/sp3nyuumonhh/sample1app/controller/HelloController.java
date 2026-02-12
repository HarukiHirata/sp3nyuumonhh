package com.sp3nyuumonhh.sample1app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sp3nyuumonhh.sample1app.repositories.PersonRepository;
import com.sp3nyuumonhh.sample1app.entity.Person;

@Controller
public class HelloController {

    @Autowired
    PersonRepository repository;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("title", "Hello page");
        mav.addObject("msg", "this is JPA sample data.");
        Iterable<Person> list = repository.findAll();
        mav.addObject("data", list);
        return mav;
    }
}
