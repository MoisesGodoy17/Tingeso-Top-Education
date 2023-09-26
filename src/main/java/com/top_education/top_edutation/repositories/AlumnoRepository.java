package com.top_education.top_edutation.repositories;

import com.top_education.top_edutation.entities.AlumnoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends CrudRepository<AlumnoEntity, Long>{

    public AlumnoEntity findByRut(String rut);

}
