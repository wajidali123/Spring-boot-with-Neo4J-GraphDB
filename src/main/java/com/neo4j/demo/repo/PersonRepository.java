package com.neo4j.demo.repo;

import com.neo4j.demo.node.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);

}
