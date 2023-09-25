package com.top_education.top_edutation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="alumno")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String rut;

    private String apellidos;
    private String nombres;
    private Date fecha_nacimiento;
    private String tipo_colegio;
    private String nombre_colegio;
    private Date ano_egreso;

}
