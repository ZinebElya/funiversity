package com.example.api;

import com.example.domain.Professor;
import com.example.dtos.CreateProfessorDto;
import com.example.dtos.ProfessorDto;
import com.example.dtos.UpdateProfessorDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProfessorMapper {
    public ProfessorDto toDTO (Professor professor){
        ProfessorDto professorDto = new ProfessorDto();

        professorDto.setId(professor.getId());
        professorDto.setFirstname(professor.getFirstname());
        professorDto.setLastname(professor.getLastname());

        return professorDto;

    }

    public Collection<ProfessorDto> toDTO(Collection<Professor> professors){
        return professors.stream().map(this::toDTO).collect(Collectors.toList());

    }

    public Professor toEntity(ProfessorDto professorDto){
        return new Professor(professorDto.getFirstname(), professorDto.getLastname());
    }

    public Professor toEntity (CreateProfessorDto createProfessorDto){
        return new Professor(createProfessorDto.getFirstname(), createProfessorDto.getLastname());
    }

    public ProfessorDto updateDto(ProfessorDto professorDto, UpdateProfessorDto updateProfessorDto){
        professorDto.setFirstname(updateProfessorDto.getFirstname());
        professorDto.setLastname(updateProfessorDto.getLastname());
        return professorDto;
    }






}
