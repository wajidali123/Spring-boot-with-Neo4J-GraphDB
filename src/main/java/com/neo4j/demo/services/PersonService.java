package com.neo4j.demo.services;

import com.neo4j.demo.node.Movie;
import com.neo4j.demo.node.Person;
import com.neo4j.demo.node.Role;
import com.neo4j.demo.repo.MovieRepository;
import com.neo4j.demo.repo.PersonRepository;
import com.neo4j.demo.repo.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private MovieRepository movieRepository;
    private RoleRepo roleRepo;

    public PersonService(PersonRepository personRepository, MovieRepository movieRepository, RoleRepo roleRepo){
        this.personRepository = personRepository;
        this.movieRepository = movieRepository;
        this.roleRepo = roleRepo;
    }

    public List<Person> findAllPersons(){
        return (List<Person>) this.personRepository.findAll();
    }

    public Person insertPerson(){

        //Optional<Movie> movie = this.movieRepository.findById((long) 1);
        Role role = new Role();
        String roleName1 = "Actor 3";
        String roleName2 = "Actress";
        List<String> roles = new ArrayList<>();
        roles.add(roleName1);
        roles.add(roleName2);
        role.setRoles(roles);

        Movie movie1 = new Movie();
        movie1.setTitle("Movie_3");
        movie1.setReleased(2019);
        movie1.setTagline("Comedy Movie");
        List<Role> roles1 = new ArrayList<>();
        roles1.add(role);
        movie1.setRoles(roles1);
        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);

        Person person = new Person("Person_3", 2018, movies);
        //Person person2 = new Person("Person_3", 2019, movies);
        role.setPerson(person);
        role.setMovie(movie1);
        this.roleRepo.save(role);
        this.movieRepository.save(movie1);
        return this.personRepository.save(person);
    }

    public Optional<Person> findById(long id){
        return this.personRepository.findById(id);
    }

    public String deleteAllPersons(){
        this.personRepository.deleteAll();
        return "All Persons deleted!!!";
    }
}
