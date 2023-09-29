package com.top_education.top_edutation.controllers;

import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.services.AlumnoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.top_education.top_edutation.entities.CuotasEntity;
import com.top_education.top_edutation.services.CuotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CuotasController {
    @Autowired
    CuotasService cuotasService;

    @Autowired
    AlumnoService alumnoService;


    @GetMapping("/cuotas")
    public String verCuotasForm(Model model) {
        model.addAttribute("rut", ""); // Inicialmente, no se muestra ninguna cuota
        return "cuotas";
    }
    @PostMapping("/cuotas")
    public String verCuotas(@RequestParam String rut, Model model) {
        List<CuotasEntity> cuotas = cuotasService.obtenerCuotasPorRut(rut);
        model.addAttribute("rut", rut);
        model.addAttribute("cuotas", cuotas);
        return "cuotas";
    }

    @GetMapping("/alumnos/cuotas/crear")
    public String mostrarFormularioCuotas(Model model) {
        model.addAttribute("cuotas", model);
        return "formulario_cuotas";
    }

    @PostMapping("/alumnos/cuotas/crear")
    public String crearCuotas(
            @RequestParam("rut") String rut,
            @RequestParam("cant_cuotas") String cant_cuotas,
            @RequestParam("fechaEmision") LocalDate fechaEmision) {
        cuotasService.crearCuota(rut, cant_cuotas, fechaEmision);
        return "redirect:/cuotas";
    }

    @GetMapping("/alumnos/cuotas/pagar")
    public String mostrarFormularioPagarCuotas(Model model) {
        model.addAttribute("cuotas", model);
        return "formulario_pagar_cuota";
    }

    @PostMapping("/alumnos/cuotas/pagar")
    public String crearCuotas(
            @RequestParam("idCuota") Long idCuota,
            @RequestParam("estado") String estado) {
        cuotasService.pagarCuotas(estado, idCuota);
        return "redirect:/cuotas";
    }
}
