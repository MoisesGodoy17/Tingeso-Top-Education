package com.top_education.top_edutation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.top_education.top_edutation.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping
public class NotasController {

    @Autowired
    private NotaService notaService;

    @GetMapping("/subir-archivo")
    public String  subirExamenForm(){
        return "subir_notas";
    }

    @PostMapping("/subir-archivo")
    public String handleFileUpload(@RequestParam("archivo") MultipartFile file, RedirectAttributes ms) {
        if (notaService.verificaPrimerMesDelMes() == 1){
            notaService.guardarArchivo(file);
            ms.addAttribute("mensaje", "¡Archivo Guardado de forma correcta!");
            notaService.leerCsv("Prueba.csv");
            return "redirect:/subir-archivo";
        }
        ms.addAttribute("mensaje", "¡Fuera de plazo para ingresar notas!");
        return "redirect:/subir-archivo";
    }
}
