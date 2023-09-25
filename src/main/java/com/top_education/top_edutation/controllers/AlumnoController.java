package com.top_education.top_edutation.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class AlumnoController {
    @Autowired
    AlumnoService alumnoService;

    @GetMapping("/alumnos/")
    public String todos(Model model){
        model.addAttribute("alumno", alumnoService.todos());
        return "alumnos"; //retorna el html
    }

    @GetMapping("/nuevo/")
    public String formulario(Model modelo) {
        AlumnoEntity alumnoEntity = new AlumnoEntity();
        modelo.addAttribute("alumno", alumnoEntity);
        return "crear_alumno";
    }
    @PostMapping("/alumnos/")
    public String guardar(@ModelAttribute("alumno") AlumnoEntity alumnoEntity ){
        alumnoService.guardar(alumnoEntity);
        return "redirect:/alumnos/";
    }
}
