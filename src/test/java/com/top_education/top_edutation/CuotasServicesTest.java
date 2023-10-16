package com.top_education.top_edutation;

import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.services.AlumnoService;
import com.top_education.top_edutation.repositories.AlumnoRepository;
import com.top_education.top_edutation.entities.CuotasEntity;
import com.top_education.top_edutation.repositories.CuotasRepository;
import com.top_education.top_edutation.services.CuotasService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest

public class CuotasServicesTest {
    @InjectMocks
    private AlumnoService alumnoService;
    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private CuotasService cuotasService;
    @Mock
    private CuotasRepository cuotasRepository;

    AlumnoEntity alumnoEntity = new AlumnoEntity("25100345-9","Godoy Carreño",
            "Moises Godoy","2000-04-17",
            "Municipal","Almendros",
            3, "Cuotas",0 );
    AlumnoEntity alumnoEntity1 = new AlumnoEntity("25100346-7","Godoy Carreño",
            "Pepe Ramon","2000-8-17",
            "Privado","Almendros",
            3, "Contado",0 );


    CuotasEntity cuota1 = new CuotasEntity(1L, 175000, LocalDate.now().withMonth(3).withDayOfMonth(1),
            LocalDate.now().withMonth(4).withDayOfMonth(5), LocalDate.now().withMonth(4).withDayOfMonth(11),
            "No pagada", 0, 0, 0, 2, alumnoEntity1);

    CuotasEntity cuota2 = new CuotasEntity(2L, 175000, LocalDate.now().withMonth(3).withDayOfMonth(1),
            LocalDate.now().withMonth(5).withDayOfMonth(5), LocalDate.now().withMonth(4).withDayOfMonth(11),
            "No pagada", 0, 0, 0, 2, alumnoEntity1);

    @Test
    public void verificaSiTieneCuotas(){
        boolean coutas = cuotasService.alumnoConCuotas(alumnoEntity);
        assertTrue(coutas);
        boolean contado = cuotasService.alumnoConCuotas(alumnoEntity1);
        assertFalse(contado);
    }

    @Test
    public void testCrearCuota() {
        when(alumnoRepository.findByRut(anyString())).thenReturn(new AlumnoEntity
                        ("25100345-9", "Godoy Carreño",
                        "Moises Godoy", "2000-04-17",
                        "Municipal", "Almendros",
                        3, "Cuotas", 0));
        cuotasService.crearCuota("25100345-9", "3", LocalDate.now());
        verify(cuotasRepository, times(3)).save(any(CuotasEntity.class));
    }

    @Test
    public void vefiricaTipoColegio(){
        boolean masCuotas = cuotasService.verificaColegio(alumnoEntity, 11);
        assertFalse(masCuotas);
        boolean igualCuotas = cuotasService.verificaColegio(alumnoEntity, 10);
        assertTrue(igualCuotas);
    }

    @Test
    public void descuentoPorAno(){

        float descuento = cuotasService.calcularDescuentoAno(alumnoEntity, 0.0f);
        //0.04f;
        assertEquals(descuento, 0.04f);
    }

    /*
    @Test
    public void testGeneraCuotasAtrasadas() {

        CuotasEntity cuota11 = new CuotasEntity(11L, 175000, LocalDate.now().withMonth(3).withDayOfMonth(1),
                LocalDate.now().withMonth(4).withDayOfMonth(5), LocalDate.now().withMonth(4).withDayOfMonth(11),
                "No pagada", 0, 0, 0, 2, alumnoEntity1);

        CuotasEntity cuota22 = new CuotasEntity(22L, 175000, LocalDate.now().withMonth(3).withDayOfMonth(1),
                LocalDate.now().withMonth(5).withDayOfMonth(5), LocalDate.now().withMonth(4).withDayOfMonth(11),
                "Atrasada", 0, 0, 0, 2, alumnoEntity1);

        List<CuotasEntity> cuotasNoPagadas = Arrays.asList(cuota11);
        List<CuotasEntity> cuotasAtrasadas = Arrays.asList(cuota22);

        when(cuotasRepository.findByEstado("No pagada")).thenReturn(cuotasNoPagadas);
        when(cuotasRepository.findByEstado("Atrasada")).thenReturn(cuotasAtrasadas);

        // Llamar al método que estás probando
        cuotasService.generaCuotasAtrasadas();

        // Verificar que las interacciones del repositorio se han llamado correctamente
        verify(cuotasRepository, times(1)).findByEstado("No pagada");
        verify(cuotasRepository, times(1)).findByEstado("Atrasada");
        verify(cuotasRepository, times(1)).save(cuotaAtrasada);;
    }
    */

    @Test
    void calcularInteresAtraso(){
        float interes = cuotasService.calculaIntereAtraso(LocalDate.now(), cuota1.getFechaVencimiento());
        assertEquals(interes, 0.12f);
    }

    @Test
    void calcularDescuentoColegio(){
        float interes = cuotasService.calcularDescuentoColegio(alumnoEntity1, 0.0f);
        assertEquals(interes, 0.0f);
    }

    @Test
    void pagarCuota(){
        Long idCuota = cuota1.getIdCuota();
        when(cuotasRepository.findByIdCuota(idCuota)).thenReturn(cuota1);

        cuotasService.pagarCuotas(idCuota);

        verify(cuotasRepository, times(1)).findByIdCuota(idCuota);
        verify(cuotasRepository, times(1)).save(cuota1);
    }

    @Test
    void testBuscarCuotaPorId() {
        Long idCuota = cuota1.getIdCuota();
        when(cuotasRepository.findByIdCuota(idCuota)).thenReturn(cuota1);
        CuotasEntity result = cuotasService.buscarCuotaPorId(idCuota);
        assertEquals(cuota1, result); // La cuota devuelta debe ser la misma que la cuota mockeada
        verify(cuotasRepository, times(1)).findByIdCuota(idCuota); // Se llamó una vez a findByIdCuota con el ID proporcionado
    }

    @Test
    void testObtenerCuotasPorRut() {
        String rut = alumnoEntity.getRut();
        List<CuotasEntity> cuotasList = new ArrayList<>(); // Lista vacía
        when(cuotasRepository.findByAlumnoRut(rut)).thenReturn(cuotasList);
        List<CuotasEntity> result = cuotasService.obtenerCuotasPorRut(rut);
        assertTrue(result.isEmpty()); // La lista de cuotas devuelta debe estar vacía
        verify(cuotasRepository, times(1)).findByAlumnoRut(rut); // Se llamó una vez a findByAlumnoRut con el rut proporcionado
    }


}
