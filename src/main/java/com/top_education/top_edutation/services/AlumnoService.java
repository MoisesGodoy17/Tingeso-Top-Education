package com.top_education.top_edutation.services;

import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.repositories.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoService {
    @Autowired
    AlumnoRepository alumnoRepository;

    public AlumnoEntity guardar(AlumnoEntity newAlumno){
        return alumnoRepository.save(newAlumno);
    }

    public Iterable<AlumnoEntity> todos(){
        return alumnoRepository.findAll();
    }
}
