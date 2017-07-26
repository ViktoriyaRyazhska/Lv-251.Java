package com.softserve.edu.lv251.controllers;

import com.softserve.edu.lv251.entity.Doctors;
import com.softserve.edu.lv251.service.DoctorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Admin on 23.07.2017.
 */
@Controller
public class AllDoctorsController {
    @Autowired
    private DoctorsService doctorsService;
    @RequestMapping(value = "/allDoctors",method = RequestMethod.GET)
    public  String allDoctors(Model model){
        model.addAttribute("doctors",doctorsService.getAll());
        return "allDoctors";
    }
    @ResponseBody
    @RequestMapping(value = "/all/doc")
    public List<Doctors> searchDoctors(@RequestParam String name){
        return doctorsService.searchByLetters(name);

    }
    @RequestMapping(value = "/searchResult")
    public String searchDoctor(@RequestParam("search")String name){
        System.out.println(name);
        return "searchDoctor";
    }
    @ResponseBody
    @RequestMapping(value = "/searchResult/{id}")
    public Doctors s(@PathVariable Long id){
        return  doctorsService.find(id);
    }

}
