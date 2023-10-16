package com.example.school_management_system.exceptions;


public class AssignmentNotFoundException extends RuntimeException{
    public AssignmentNotFoundException(String message){
        super(message);
    }
}
