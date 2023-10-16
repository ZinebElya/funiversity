package com.example.service;

import com.example.api.ProfessorMapper;
import com.example.domain.Professor;
import com.example.domain.ProfessorRepository;
import com.example.dtos.CreateProfessorDto;
import com.example.dtos.ProfessorDto;
import com.example.dtos.UpdateProfessorDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;

@ApplicationScoped
public class ProfessorService {
    ProfessorRepository professorRepository= new ProfessorRepository();
    ProfessorMapper professorMapper= new ProfessorMapper();

    public ProfessorService(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    public Collection<ProfessorDto> getAllProfessors(){
        return professorMapper.toDTO(professorRepository.getAllProfessors());
    }

    public ProfessorDto getProfessorById(String id){
        return professorMapper.toDTO(professorRepository.getProfessorById(id));

    }

    public Professor createProfessor(CreateProfessorDto createProfessorDto){
        Professor newProfessor = professorMapper.toEntity(createProfessorDto);
        return professorRepository.saveProfessor(newProfessor);

    }

    public ProfessorDto updateProfessor(String id, UpdateProfessorDto updateProfessorDto){
        Professor initialProfessor = professorRepository.getProfessorById(id);
        ProfessorDto initialProfessorDto = professorMapper.toDTO(initialProfessor);
        ProfessorDto updatedProfessorDto = professorMapper.updateDto(initialProfessorDto, updateProfessorDto);
        professorRepository.saveProfessor(professorMapper.toEntity(updatedProfessorDto));
        return updatedProfessorDto;

    }

    public void deleteProfessor(String id){
        professorRepository.deleteProfessor(id);
    }














}
