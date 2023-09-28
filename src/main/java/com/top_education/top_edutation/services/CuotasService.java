package com.top_education.top_edutation.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.entities.CuotasEntity;
import com.top_education.top_edutation.repositories.AlumnoRepository;
import com.top_education.top_edutation.repositories.CuotasRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
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
            CuotasEntity cuotasEntity = new CuotasEntity();
            cuotasEntity.setMonto(montoInt);
            cuotasEntity.setFechaEmision(fechaEmision);
            cuotasEntity.setFechaPago(fechaEmision.withDayOfMonth(10).plusMonths(i));
            cuotasEntity.setEstado("No pagada");
            cuotasEntity.setCant_cuotas(cantCuotas);
            cuotasEntity.setAlumno(alumnoEntity);
            cuotasRepository.save(cuotasEntity);
        }
    }

    //funcion que recibe el alumno y otros datos para calcular el descuento por años de egreso.
    public float calcularDescuentoAno(AlumnoEntity alumnoEntity, float descuento){
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
    @Scheduled(fixedRate = 60000)
    public void generaCuotasAtrasadas(){
        List<CuotasEntity> cutasAtrasadas = cuotasRepository.findByEstado("No pagada");
        LocalDate fechaLocal = LocalDate.now();
        int multa;
        for(CuotasEntity cuotas : cutasAtrasadas){
            LocalDate fechaPago = cuotas.getFechaPago();
            if (fechaLocal.isAfter(fechaPago)){
                multa = (int) Math.floor((cuotas.getMonto()*calculaIntereAtraso(fechaLocal, fechaPago)) + cuotas.getInteres());
                System.out.println("diferencia" + multa);
                cuotas.setEstado("Atrasada");
                cuotas.setInteres(multa);
                cuotas.setMonto(multa + cuotas.getMonto());
                cuotasRepository.save(cuotas);
            }
        }
    }

    public float calculaIntereAtraso(LocalDate fechaLocal, LocalDate fechaPago){
        int diferencia = fechaLocal.getMonthValue() - fechaPago.getMonthValue();
        // se resta la fecha de pago de cuota menos la fecha de facturacion para asi saber la diferencia y saber el retraso
        float deuda = 0.0f;

        if (diferencia == 1){
            deuda = 0.03f;
        }
        if (diferencia == 2){
            deuda = 0.06f;
        }
        if (diferencia == 3){
            deuda = 0.09f;
        }
        if (diferencia > 3){
            deuda = 0.12f;
        }
        return deuda;
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
