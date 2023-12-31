package com.top_education.top_edutation.repositories;

import com.top_education.top_edutation.entities.CuotasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CuotasRepository extends JpaRepository<CuotasEntity, Long> {
    List<CuotasEntity> findByAlumnoRut(String rut);

    List<CuotasEntity> findByEstado(String estado);

    CuotasEntity findByIdCuota(long idCuota);

    CuotasEntity findByAlumnoRutAndFechaPago(String rut, LocalDate fechaPago);
}
