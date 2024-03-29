package com.neo4j.demo.services;

import java.util.*;

import com.neo4j.demo.exceptions.CustomException;
import com.neo4j.demo.node.Movie;
import com.neo4j.demo.node.Person;
import com.neo4j.demo.node.Role;
import com.neo4j.demo.repo.MovieRepository;
import com.neo4j.demo.repo.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    private final static Logger LOG = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;

    public MovieService(MovieRepository movieRepository, PersonRepository personRepository) {
        this.movieRepository = movieRepository;
        this.personRepository = personRepository;
    }

    private Map<String, Object> toD3Format(Collection<Movie> movies) {
        List<Map<String, Object>> nodes = new ArrayList<>();
        List<Map<String, Object>> rels = new ArrayList<>();
        int i = 0;
        Iterator<Movie> result = movies.iterator();
        while (result.hasNext()) {
            Movie movie = result.next();
            nodes.add(map("title", movie.getTitle(), "label", "movie"));
            int target = i;
            i++;
            for (Role role : movie.getRoles()) {
                Map<String, Object> actor = map("title", role.getPerson().getName(), "label", "actor");
                int source = nodes.indexOf(actor);
                if (source == -1) {
                    nodes.add(actor);
                    source = i++;
                }
                rels.add(map("source", source, "target", target));
            }
        }
        return map("nodes", nodes, "links", rels);
    }

    private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
        Map<String, Object> result = new HashMap<String, Object>(2);
        result.put(key1, value1);
        result.put(key2, value2);
        return result;
    }

    @Transactional(readOnly = true)
    public Movie findByTitle(String title) {
        Movie result = movieRepository.findByTitle(title);
        return result;
    }

    @Transactional(readOnly = true)
    public Collection<Movie> findByTitleLike(String title) {
        Collection<Movie> result = movieRepository.findByTitleLike(title);
        return result;
    }

    @Transactional(readOnly = true)
    public Map<String, Object>  graph(int limit) {
        Collection<Movie> result = movieRepository.graph(limit);
        return toD3Format(result);
    }

    public Movie insertMovie(){
        Optional<Person> person = this.personRepository.findById((long) 1);
        Movie movie = new Movie("Movie_2",2019,"Metrix Movie");
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies(){
        return (List<Movie>) this.movieRepository.findAll();
    }

    public Optional<Movie> deleteById(long id){
        Optional<Movie> movie = this.movieRepository.findById(id);
        if(movie != null){
            this.movieRepository.deleteById(id);
            return movie;
        }
        else{
            throw new CustomException("Can not find Movie with ID: "+ id);
        }
    }

    public String deleteAllMovies(){
        this.movieRepository.deleteAll();
        return "All Movies Deleted";
    }
}
