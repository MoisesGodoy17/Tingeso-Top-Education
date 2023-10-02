package com.top_education.top_edutation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="notas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idNota", unique = true, nullable = false)
    private Long idNota;

    @Column(name = "fechaNota")
    private LocalDate fechaNota;

    @Column(name = "puntajeNota")
    private int puntajeNota;

    @Column(name = "estadoNota")
    private int estadoNota;
    /*
    si el estado es 0, significa que no se a usado para aplicar descuento, caso contraio si se uso para
    aplicar un descuento
     */

    @ManyToOne
    @JoinColumn(name = "rut")
    private AlumnoEntity alumno;
}
