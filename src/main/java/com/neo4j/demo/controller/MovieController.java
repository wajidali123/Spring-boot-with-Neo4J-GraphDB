package com.neo4j.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.neo4j.demo.node.Movie;
import com.neo4j.demo.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author John
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/graph")
    public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
        return movieService.graph(limit == null ? 100 : limit);
    }

    @GetMapping("/save")
    public Movie insert(){
        return this.movieService.insertMovie();
    }

    @GetMapping("/all")
    public List<Movie> getAllMovies(){
        return this.movieService.getAllMovies();
    }

    @GetMapping("/delete/id/{id}")
    public ResponseEntity deleteById(@PathVariable long id){
        Optional<Movie> movie = this.movieService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/delete/all")
    public String deleteAllMovies(){
        String message = this.movieService.deleteAllMovies();
        return message;
    }
}