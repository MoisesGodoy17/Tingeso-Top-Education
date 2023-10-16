package com.top_education.top_edutation;

import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.services.AlumnoService;
import com.top_education.top_edutation.repositories.AlumnoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class AlumnoServiceTest {

    @InjectMocks
    private AlumnoService alumnoService;
    @Mock
    private AlumnoRepository alumnoRepository;

    @Test
    void crearAlumno() {
        AlumnoEntity alumnoEntity = new AlumnoEntity();
        alumnoEntity.setRut("25100345-9");
        alumnoEntity.setApellidos("Godoy Carreño");
        alumnoEntity.setNombres("Moises Godoy");
        alumnoEntity.setFecha_nacimiento("2000-04-17");
        alumnoEntity.setTipo_colegio("Municipal");
        alumnoEntity.setNombre_colegio("Almendros");
        alumnoEntity.setAno_egreso(3);
        alumnoEntity.setTipoPago("Cuotas");
        alumnoEntity.setArancel(0);
        alumnoService.guardar(alumnoEntity);
        verify(alumnoRepository, times(1)).save(alumnoEntity);
    }

    @Test
    void testTodos() {
        List<AlumnoEntity> alumnoEntity = Arrays.asList(
                new AlumnoEntity("25100345-9","Godoy Carreño",
                        "Moises Godoy","2000-04-17",
                        "Municipal","Almendros",
                        3, "Cuotas",0 ),
                new AlumnoEntity("25100346-7","Godoy Carreño",
                        "Pepe Ramon","2000-8-17",
                        "Municipal","Almendros",
                        3, "Contado",0 )
        );

        when(alumnoRepository.findAll()).thenReturn(alumnoEntity);

        Iterable<AlumnoEntity> resultado = alumnoService.todos();

        assertThat(resultado).isEqualTo(alumnoEntity);
    }
}

