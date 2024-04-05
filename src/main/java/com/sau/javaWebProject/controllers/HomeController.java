package com.sau.javaWebProject.controllers;

import com.sau.javaWebProject.models.Person;
import com.sau.javaWebProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public String getUsers(Model model){
        List<Person> plist = personRepository.findAll();
        model.addAttribute("plist", plist);
        return "index";
    }
    @GetMapping("/addPerson")
    public String addPersonPage(Model model) {
        model.addAttribute("person", new Person());
        return "addPerson";
    }

    @PostMapping("/addPerson")
    public String addPerson(@ModelAttribute Person person) {
        personRepository.save(person);
        return "redirect:/";
    }

    @GetMapping("/updatePerson/{id}")
    public String updatePersonPage(@PathVariable Integer id, Model model) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            model.addAttribute("person", optionalPerson.get());
            return "updatePerson";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/updatePerson")
    public String updatePerson(@ModelAttribute Person person) {
        personRepository.save(person);
        return "redirect:/";
    }

    @GetMapping("/deletePerson/{id}")
    public String deletePersonPage(@PathVariable Integer id, Model model) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            model.addAttribute("person", optionalPerson.get());
            return "deletePerson";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/deletePerson")
    public String deletePerson(@RequestParam Integer id) {
        personRepository.deleteById(id);
        return "redirect:/";
    }
}
