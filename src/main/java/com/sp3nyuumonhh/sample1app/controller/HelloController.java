package com.sp3nyuumonhh.sample1app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sp3nyuumonhh.sample1app.repositories.PeopleRepository;
import com.sp3nyuumonhh.sample1app.dao.PeopleDAOPeopleImpl;
import com.sp3nyuumonhh.sample1app.dao.PeopleDAO;
import com.sp3nyuumonhh.sample1app.entity.People;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Controller
public class HelloController {

    @Autowired
    PeopleRepository repository;

    @Autowired
    private PeopleDAO<People> dao;

    @RequestMapping("/")
    public ModelAndView index(
            @ModelAttribute("formModel") People People,
            ModelAndView mav) {
        mav.setViewName("index");
        mav.addObject("title", "Hello Page");
        mav.addObject("msg", "this is JPA sample page.");
        // List<People> list = repository.findAll();
        List<People> list = repository.findAllOrderByName();
        mav.addObject("data", list);
        return mav;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Transactional
    public ModelAndView form(
            @ModelAttribute("formModel") People People,
            ModelAndView mav) {
        repository.saveAndFlush(People);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(
            @ModelAttribute People People,
            @PathVariable int id,
            ModelAndView mav) {
        mav.setViewName("edit");
        mav.addObject("title", "edit Person.");
        Optional<People> data = repository.findById((long) id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView update(
            @ModelAttribute People People,
            ModelAndView mav) {
        repository.saveAndFlush(People);
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(
            @PathVariable int id,
            ModelAndView mav) {
        mav.setViewName("delete");
        mav.addObject("title", "delete person.");
        mav.addObject("msg", "Can I delete this record?");
        Optional<People> data = repository.findById((long) id);
        mav.addObject("formModel", data.get());
        return mav;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @Transactional
    public ModelAndView remove(
            @RequestParam long id,
            ModelAndView mav) {
        repository.deleteById(id);
        return new ModelAndView("redirect:/");
    }

    // @RequestMapping(value = "/find", method = RequestMethod.GET)
    // public ModelAndView index(ModelAndView mav) {
    // mav.setViewName("find");
    // mav.addObject("msg", "Peopleのサンプルです。");
    // Iterable<People> list = dao.getAll();
    // mav.addObject("data", list);
    // return mav;
    // }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    public ModelAndView search(HttpServletRequest request,
            ModelAndView mav) {
        mav.setViewName("find");
        String param = request.getParameter("find_str");
        if (param == "") {
            mav = new ModelAndView("redirect:/find");
        } else {
            String[] params = param.split(",");
            mav.addObject("title", "find result");
            mav.addObject("msg", "「" + param + "の検索結果");
            mav.addObject("value", param);
            List<People> list = repository.findByAge(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
            mav.addObject("data", list);
        }
        return mav;
    }
}
