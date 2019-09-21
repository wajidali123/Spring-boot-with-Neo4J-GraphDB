package com.neo4j.demo.controller;

import com.neo4j.demo.node.Person;
import com.neo4j.demo.services.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {
    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @RequestMapping("/all")
    public List<Person> getAll(){
        return this.personService.findAllPersons();
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public Person savePerson(){
        return this.personService.insertPerson();
    }

    @RequestMapping("/id/{id}")
    public Optional<Person> personById(@PathVariable long id){
        return this.personService.findById(id);
    }

    @GetMapping("/delete/all")
    public String deleteAllPersons(){
        return this.personService.deleteAllPersons();
    }
}
