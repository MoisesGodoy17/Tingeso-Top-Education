package com.top_education.top_edutation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
public class AlumnoController {
    @Autowired
    AlumnoService alumnoService;

    @GetMapping("/alumnos/")
    public String todos(){
        return "alumnos";
    }

    @PostMapping("/alumno/nuevo")
    public String guardar(Model modelo) {
        AlumnoEntity alumno = new AlumnoEntity();
        modelo.addAttribute("alumno", alumno);
        return "crear_alumno";
    }
}
