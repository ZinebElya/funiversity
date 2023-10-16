package com.example.domain;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class ProfessorRepository {

    private final ConcurrentHashMap<String, Professor> professorsById;

    public ProfessorRepository() {
        this.professorsById = new ConcurrentHashMap<>();
    }

    public Professor saveProfessor(Professor professor){
        professorsById.put(professor.getId(), professor);
        return professor;
    }

    public Professor getProfessorById(String id){
        Professor foundProfessor = professorsById.get(id);
        if (foundProfessor == null){
            throw new WebApplicationException("No Professor found for id " + id);
        }
        return foundProfessor;
    }

    public Collection<Professor> getAllProfessors(){
        return professorsById.values();
    }

    public void deleteProfessor(String id){
        professorsById.remove(id);

    }
}
