package com.example.domain;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ProfessorRepositoryTest {

    @Test
    void givenProfessor_whenSaveProfessor_thenSaveThisProfessor(){
        ProfessorRepository professorRepository = new ProfessorRepository();

        Professor newProfessor = new Professor("William", "Allegria");

        Professor savedProfessor = professorRepository.saveProfessor(newProfessor);

        assertEquals(newProfessor,savedProfessor);

    }

    @Test
    void givenProfessors_whenGetById_thenGetTheProfessorWithTheCorrespondingId(){
        ProfessorRepository professorRepository = new ProfessorRepository();
        Professor professor1 = new Professor("William", "Allegria");
        Professor professorToFind = new Professor("Will", "Mauclet");

        professorRepository.saveProfessor(professor1);
        professorRepository.saveProfessor(professorToFind);

        Professor actualProfessor = professorRepository.getProfessorById(professorToFind.getId());

        assertEquals(professorToFind, actualProfessor);

    }

    @Test
    void givenProfessors_whenGetAll_thenGetAllProfessors(){
        ProfessorRepository professorRepository = new ProfessorRepository();
        Professor professor1 = new Professor("William", "Allegria");
        Professor professor2 = new Professor("Will", "Mauclet");

        professorRepository.saveProfessor(professor1);
        professorRepository.saveProfessor(professor2);

        Collection<Professor> allProfessors = professorRepository.getAllProfessors();


        assertThat(allProfessors).containsExactlyInAnyOrder(professor1,professor2);

    }

}