package com.top_education.top_edutation.services;


import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.entities.CuotasEntity;
import com.top_education.top_edutation.entities.NotasEntity;
import com.top_education.top_edutation.repositories.NotaRepository;
import com.top_education.top_edutation.repositories.AlumnoRepository;
import com.top_education.top_edutation.repositories.CuotasRepository;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.top_education.top_edutation.repositories.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CuotasRepository cuotasRepository;

    public AlumnoEntity getRutEstudiante(String rut){

    }





}
