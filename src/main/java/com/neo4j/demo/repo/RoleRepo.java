package com.neo4j.demo.repo;

import com.neo4j.demo.node.Role;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RoleRepo extends Neo4jRepository<Role, Long> {
}
