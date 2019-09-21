package com.neo4j.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories("com.neo4j.demo.repo")
public class Neo4jdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(Neo4jdemoApplication.class, args);
    }
}
