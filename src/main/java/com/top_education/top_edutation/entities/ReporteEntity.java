package com.top_education.top_edutation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="reporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idReporte", unique = true, nullable = false)
    private Long idNota;

    @ManyToOne
    @JoinColumn(name = "rut")
    private AlumnoEntity alumno;
}
