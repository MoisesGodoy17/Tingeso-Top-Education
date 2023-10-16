package com.top_education.top_edutation;

import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.services.AlumnoService;
import com.top_education.top_edutation.repositories.AlumnoRepository;
import com.top_education.top_edutation.entities.CuotasEntity;
import com.top_education.top_edutation.repositories.CuotasRepository;
import com.top_education.top_edutation.services.CuotasService;
import com.top_education.top_edutation.entities.NotasEntity;
import com.top_education.top_edutation.repositories.NotaRepository;
import com.top_education.top_edutation.services.ReporteService;
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
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ReporteServiceTest {
    @InjectMocks
    private AlumnoService alumnoService;
    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private CuotasService cuotasService;
    @Mock
    private CuotasRepository cuotasRepository;

    @InjectMocks
    private ReporteService reporteService;

    //escribirNotas

    AlumnoEntity alumnoEntity = new AlumnoEntity("25100345-9","Godoy Carreño",
            "Moises Godoy","2000-04-17",
            "Municipal","Almendros",
            3, "Cuotas",0 );

    CuotasEntity cuota1 = new CuotasEntity(1L, 175000, LocalDate.now().withMonth(3).withDayOfMonth(1),
            LocalDate.now().withMonth(4).withDayOfMonth(5), LocalDate.now().withMonth(4).withDayOfMonth(11),
            "No Pagada", 0, 0, 0, 2, alumnoEntity);

    CuotasEntity cuota2 = new CuotasEntity(2L, 175000, LocalDate.now().withMonth(3).withDayOfMonth(1),
            LocalDate.now().withMonth(5).withDayOfMonth(5), LocalDate.now().withMonth(4).withDayOfMonth(11),
            "No Pagada", 0, 0, 0, 2, alumnoEntity);
    @Test
    void verificaEstudianteSer(){

        when(alumnoRepository.findByRut(alumnoEntity.getRut())).thenReturn(alumnoEntity);
        boolean resultado = reporteService.verificaEstudiante(alumnoEntity.getRut());
        assertTrue(resultado);
        verify(alumnoRepository, times(1)).findByRut(alumnoEntity.getRut());
    }

    @Test
    void ObtieneNombreEstudiante(){
        when(alumnoRepository.findByRut(alumnoEntity.getRut())).thenReturn(alumnoEntity);
        String resultado = reporteService.NombresEstudiante(alumnoEntity.getRut());
        assertEquals("Moises Godoy", resultado);
        verify(alumnoRepository, times(1)).findByRut(alumnoEntity.getRut());
    }

    @Test
    void montoPorPagar(){
        List<CuotasEntity> cuotasList = Arrays.asList(cuota1, cuota2);
        // Act
        when(cuotasRepository.findByAlumnoRut(alumnoEntity.getRut())).thenReturn(cuotasList);
        int montoTotal = reporteService.MontoTotalporPagar(alumnoEntity.getRut());
        // Assert
        assertEquals(350000, montoTotal);
    }
    @Test
    void testTipoDePago() {
        when(alumnoRepository.findByRut(alumnoEntity.getRut())).thenReturn(alumnoEntity);
        String tipoPago = reporteService.TipoDePago(alumnoEntity.getRut());

        assertEquals("Cuotas", tipoPago);
    }

    @Test
    void CuotasPactadas(){
        List<CuotasEntity> cuotas = Arrays.asList(cuota1, cuota2);
        when(alumnoRepository.findByRut(alumnoEntity.getRut())).thenReturn(alumnoEntity);
        when(cuotasRepository.findByAlumnoRut(alumnoEntity.getRut())).thenReturn(cuotas);
        int resultado = reporteService.cuotasPactadas(alumnoEntity.getRut());
        assertEquals(2, resultado);
    }

    @Test
    void CuotasPagadas(){
        CuotasEntity cuota11 = new CuotasEntity(11L, 175000, LocalDate.now().withMonth(3).withDayOfMonth(1),
                LocalDate.now().withMonth(4).withDayOfMonth(5), LocalDate.now().withMonth(4).withDayOfMonth(11),
                "Pagada", 0, 0, 0, 2, alumnoEntity);

        CuotasEntity cuota22 = new CuotasEntity(22L, 175000, LocalDate.now().withMonth(3).withDayOfMonth(1),
                LocalDate.now().withMonth(5).withDayOfMonth(5), LocalDate.now().withMonth(4).withDayOfMonth(11),
                "Pagada", 0, 0, 0, 2, alumnoEntity);


        List<CuotasEntity> cuotas = Arrays.asList(cuota11, cuota22);
        when(alumnoRepository.findByRut(alumnoEntity.getRut())).thenReturn(alumnoEntity);
        when(cuotasRepository.findByEstado("Pagada")).thenReturn(cuotas);
        int resultado = reporteService.cuotasPagadas(alumnoEntity.getRut());
        assertEquals(2, resultado);
    }

    @Test
    void CuotasAtrasadas(){
        List<CuotasEntity> cuotas = Arrays.asList(cuota1, cuota2);
        when(alumnoRepository.findByRut(alumnoEntity.getRut())).thenReturn(alumnoEntity);
        when(cuotasRepository.findByEstado("Pagada")).thenReturn(cuotas);
        int resultado = reporteService.cuotasAtrasadas(alumnoEntity.getRut());
        assertEquals(0, resultado);
    }

    @Test
    void testTotalPagado() {

        CuotasEntity cuota111 = new CuotasEntity(1L, 175000, LocalDate.now().withMonth(3).withDayOfMonth(1),
                LocalDate.now().withMonth(4).withDayOfMonth(5), LocalDate.now().withMonth(4).withDayOfMonth(11),
                "Pagada", 0, 0, 0, 2, alumnoEntity);

        CuotasEntity cuota222 = new CuotasEntity(2L, 175000, LocalDate.now().withMonth(3).withDayOfMonth(1),
                LocalDate.now().withMonth(5).withDayOfMonth(5), LocalDate.now().withMonth(4).withDayOfMonth(11),
                "Pagada", 0, 0, 0, 2, alumnoEntity);


        List<CuotasEntity> cuotas = Arrays.asList(cuota111, cuota222);

        // Mocking repository calls
        when(alumnoRepository.findByRut(alumnoEntity.getRut())).thenReturn(alumnoEntity);
        when(cuotasRepository.findByAlumnoRut(alumnoEntity.getRut())).thenReturn(cuotas);

        // Act
        int totalPagado = reporteService.totalPagado(alumnoEntity.getRut());

        // Assert
        assertEquals(350000, totalPagado); // Suma de las dos cuotas pagadas
    }

}
