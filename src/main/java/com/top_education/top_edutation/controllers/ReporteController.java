package com.top_education.top_edutation.controllers;

import com.top_education.top_edutation.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @RequestMapping("/reporte")
    public String mostrarFormulario() {
        return "reporte";
    }

    @PostMapping("/reporte")
    public String verReporte(@RequestParam("rut") String rut, Model model) {
        if (reporteService.verificaEstudiante(rut)) {
            model.addAttribute("rut", rut);
            model.addAttribute("nombres", reporteService.NombresEstudiante(rut));
            model.addAttribute("montoTotal", reporteService.MontoTotalporPagar(rut));
            model.addAttribute("tipoPago", reporteService.TipoDePago(rut));
            model.addAttribute("pruebasRendidas", reporteService.nroPruebasRendidas(rut));
            model.addAttribute("cuotasPactadas", reporteService.cuotasPactadas(rut));
            model.addAttribute("cuotasPagadas", reporteService.cuotasPagadas(rut));
            model.addAttribute("cuotasAtrasadas", reporteService.cuotasAtrasadas(rut));
            model.addAttribute("totalPagado", reporteService.totalPagado(rut));
            return "reporte";
        } else {
            model.addAttribute("error", "Estudiante no encontrado");
            return "error"; // Crea una página error.html en tu carpeta de templates
        }
    }
}

