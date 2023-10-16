package com.top_education.top_edutation.services;


import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.entities.CuotasEntity;
import com.top_education.top_edutation.entities.NotasEntity;
import com.top_education.top_edutation.repositories.NotaRepository;
import com.top_education.top_edutation.repositories.AlumnoRepository;
import com.top_education.top_edutation.repositories.CuotasRepository;


import java.util.List;

import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ReporteService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private CuotasRepository cuotasRepository;

    public boolean verificaEstudiante(String rut){
        AlumnoEntity alumno = alumnoRepository.findByRut(rut);
        if (alumno == null){
            return false;
        }
        return true;
    }

    /*
    public String RutEstudiante(String rut){
        AlumnoEntity alumnoEntity = alumnoRepository.findByRut(rut);
        return alumnoEntity.getRut();
    }
    */
    public String NombresEstudiante(String rut){
        AlumnoEntity alumnoEntity = alumnoRepository.findByRut(rut);
        return alumnoEntity.getNombres();
    }

    public int MontoTotalporPagar(String rut) {
        List<CuotasEntity> cuotas = cuotasRepository.findByAlumnoRut(rut);
        int montoTotal = 0;
        for (CuotasEntity cuotasEntity : cuotas) {
            montoTotal = cuotasEntity.getMonto() + montoTotal;
        }
        return montoTotal;
    }

    public String TipoDePago(String rut){
        AlumnoEntity alumno = alumnoRepository.findByRut(rut);
        return alumno.getTipoPago();
    }

    @Generated
    public int nroPruebasRendidas(String rut){
        List<NotasEntity> notas = notaRepository.findByAlumnoRut(rut);
        if (notas.isEmpty()){
            return 0;
        }
        return notas.size();
    }

    public int cuotasPactadas(String rut){
        AlumnoEntity alumno = alumnoRepository.findByRut(rut);
        if (alumno.getTipoPago().equals("Contado")){
            return 0;
        }
        List<CuotasEntity> cuotas = cuotasRepository.findByAlumnoRut(rut);
        System.out.println("Cant Cuotas" + cuotas.size());
        return cuotas.size();
    }

    public int cuotasPagadas(String rut){
        AlumnoEntity alumno = alumnoRepository.findByRut(rut);
        if (alumno.getTipoPago().equals("Contado")){
            return 0;
        }
        List<CuotasEntity> cuotas = cuotasRepository.findByEstado("Pagada");
        return cuotas.size();
    }

    public int cuotasAtrasadas(String rut){
        AlumnoEntity alumno = alumnoRepository.findByRut(rut);
        if (alumno.getTipoPago().equals("Contado")){
            return 0;
        }
        List<CuotasEntity> cuotas = cuotasRepository.findByEstado("Atrasada");
        return cuotas.size();
    }

    public int totalPagado(String rut){
        AlumnoEntity alumno = alumnoRepository.findByRut(rut);
        int total = 0;
        if (alumno.getTipoPago().equals("Contado")){
            return alumno.getArancel(); //retornar lo que pago al contado
        }
        List<CuotasEntity> cuotas = cuotasRepository.findByAlumnoRut(rut);
        for (CuotasEntity cuotasEntity : cuotas){
            if (cuotasEntity.getEstado().equals("Pagada")){
                total = cuotasEntity.getMonto() + total;
            }
        }
        return total;
    }
}
