package com.example.dtos;

public class ProfessorDto {
    private String id;
    private String firstname;
    private String lastname;

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public ProfessorDto setId(String id) {
        this.id = id;
        return this;
    }

    public ProfessorDto setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ProfessorDto setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }
}
