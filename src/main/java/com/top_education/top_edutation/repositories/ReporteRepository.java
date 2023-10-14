package com.top_education.top_edutation.repositories;


import com.top_education.top_edutation.entities.ReporteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReporteRepository extends CrudRepository<ReporteEntity, Long>{
}
