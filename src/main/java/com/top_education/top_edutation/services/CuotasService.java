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
        int cantCuotas = Integer.parseInt(cant_cuotas);
        AlumnoEntity alumnoEntity = alumnoRepository.findByRut(rut);
        float descuento = 0.0f;
        descuento = calcularDescuentoAno(alumnoEntity, descuento);
        descuento = descuento + calcularDescuentoColegio(alumnoEntity, descuento);
        float monto = ((float) 1500000 /cantCuotas)*descuento;
        int montoInt = (int) Math.floor(monto);


            for (int i = 1; i > cantCuotas+1; i++){
                LocalDate fecha = fechaEmision.plusMonths(1);
                CuotasEntity cuotasEntity = new CuotasEntity();
                cuotasEntity.setMonto(montoInt);
                cuotasEntity.setFechaEmision(fechaEmision);
                cuotasEntity.setFechaPago(fecha);
                cuotasEntity.setEstado("No pagada");
                cuotasEntity.setAlumno(alumnoEntity);
                cuotasRepository.save(cuotasEntity);
                System.out.println("hola bueno"); //ahora no esta entrando a crear las cuotas

        }
        System.out.println("hola malo");
    }

    //funcion que recibe el alumno y otros datos para calcular el descuento por años de egreso.
    public float calcularDescuentoAno( AlumnoEntity alumnoEntity, float descuento){
        int anoEgreso = Integer.parseInt(alumnoEntity.getAno_egreso());

        if (anoEgreso  <= 1){
            descuento = 0.15f;
        }
        if (2 == anoEgreso){
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
