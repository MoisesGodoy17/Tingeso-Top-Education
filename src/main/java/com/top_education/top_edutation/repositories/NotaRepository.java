package com.top_education.top_edutation.repositories;

import com.top_education.top_edutation.entities.CuotasEntity;
import com.top_education.top_edutation.entities.NotasEntity;
import com.top_education.top_edutation.entities.AlumnoEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<NotasEntity, Long> {
    List<NotasEntity> findByAlumnoRutAndEstadoNota(String rut, int estado);

    List<NotasEntity> findByFechaNota(LocalDate fechaNota);
}
