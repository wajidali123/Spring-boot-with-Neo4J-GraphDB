package com.neo4j.demo.exceptions;

public class CustomException extends RuntimeException {
    public CustomException(String message){
        super(message);
    }
}
