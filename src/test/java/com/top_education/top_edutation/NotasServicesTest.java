package com.top_education.top_edutation;

import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.repositories.NotaRepository;
import com.top_education.top_edutation.services.AlumnoService;
import com.top_education.top_edutation.repositories.AlumnoRepository;
import com.top_education.top_edutation.entities.CuotasEntity;
import com.top_education.top_edutation.repositories.CuotasRepository;
import com.top_education.top_edutation.services.CuotasService;
import com.top_education.top_edutation.services.NotaService;
import com.top_education.top_edutation.repositories.NotaRepository;
import com.top_education.top_edutation.entities.NotasEntity;
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
public class NotasServicesTest {

    @InjectMocks
    private AlumnoService alumnoService;
    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private CuotasService cuotasService;
    @Mock
    private CuotasRepository cuotasRepository;

    @InjectMocks
    private NotaService notaService;
    @Mock
    private NotaRepository notaRepository;

    @Test
    public void VerificaSiElPrimerLunesMes(){
        int noEsDia = notaService.verificaPrimerMesDelMes();
        assertEquals(noEsDia, 0);
    }

    @Test
    public void escribirNotas(){
        notaService.escribirDatos("29-09-2023","900","25100346-7");
        verify(alumnoRepository, times(1));
    }
    /*
    @Test
    void calculaDescuentoNotas(){
        float desc1 = notaService.calcularDescuentoNotas(949);
        System.out.println("" + desc1);
        assertEquals(desc1, 0.05f);
    }

     */
}
