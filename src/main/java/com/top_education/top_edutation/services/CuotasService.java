package com.top_education.top_edutation.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.entities.CuotasEntity;
import com.top_education.top_edutation.repositories.AlumnoRepository;
import com.top_education.top_edutation.repositories.CuotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class CuotasService {

    @Autowired
    private CuotasRepository cuotasRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    public  void crearCuota(String rut, String cant_cuotas, LocalDate fechaEmision){
        AlumnoEntity alumnoEntity = alumnoRepository.findByRut(rut);
        int cantCuotas = Integer.parseInt(cant_cuotas);
        float descuento = 0.0f;
        descuento = calcularDescuentoAno(alumnoEntity, descuento) + calcularDescuentoColegio(alumnoEntity, descuento);
        int montoInt = (int) Math.floor(((float) 1500000 /cantCuotas) - ((float) 1500000 /cantCuotas)*descuento);

        for (int i = 1; i < cantCuotas+1; i++){
            LocalDate fecha = LocalDate.now().withDayOfMonth(5).plusMonths(i);
            CuotasEntity cuotasEntity = new CuotasEntity();
            cuotasEntity.setMonto(montoInt);
            cuotasEntity.setFechaEmision(fechaEmision);
            cuotasEntity.setFechaPago(fecha);
            cuotasEntity.setEstado("No pagada");
            cuotasEntity.setCant_cuotas(cantCuotas);
            cuotasEntity.setAlumno(alumnoEntity);
            cuotasRepository.save(cuotasEntity);
        }
    }

    //funcion que recibe el alumno y otros datos para calcular el descuento por años de egreso.
    public float calcularDescuentoAno( AlumnoEntity alumnoEntity, float descuento){
        int anoEgreso = alumnoEntity.getAno_egreso();
        if (anoEgreso  <= 1){
            descuento = 0.15f;
        }
        if (anoEgreso >= 1 && anoEgreso <= 2) {
            descuento = 0.08f;
        }
        if (anoEgreso >= 3 && anoEgreso <= 4) {
            descuento = 0.04f;
        }
        return descuento;
    }

    public float calcularDescuentoColegio(AlumnoEntity alumnoEntity, float descuento){
        if (alumnoEntity.getTipo_colegio().equals("Municipal")){
            descuento = descuento + 0.20f;
        }
        if (alumnoEntity.getTipo_colegio().equals("Subvencionado")){
            descuento = descuento + 0.10f;
        }
        return descuento;
    }

    public List<CuotasEntity> obtenerCuotasPorRut(String rut) {
        return cuotasRepository.findByAlumnoRut(rut);
    }
}
