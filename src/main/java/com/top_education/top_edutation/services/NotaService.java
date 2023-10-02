package com.top_education.top_edutation.services;

import com.top_education.top_edutation.entities.AlumnoEntity;
import com.top_education.top_edutation.entities.CuotasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.top_education.top_edutation.entities.NotasEntity;
import com.top_education.top_edutation.repositories.NotaRepository;
import org.springframework.web.multipart.MultipartFile;
import com.top_education.top_edutation.repositories.AlumnoRepository;

import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private  CuotasService cuotasService;

    private final Logger logg = LoggerFactory.getLogger(NotaService.class);

    public static List<LocalDate> obtenerPrimerosLunes() {
        List<LocalDate> primerosLunes = new ArrayList<>();
        primerosLunes.add(LocalDate.of(2023, 1, 2));
        primerosLunes.add(LocalDate.of(2023, 2, 6));
        primerosLunes.add(LocalDate.of(2023, 3, 6));
        primerosLunes.add(LocalDate.of(2023, 4, 3));
        primerosLunes.add(LocalDate.of(2023, 5, 1));
        primerosLunes.add(LocalDate.of(2023, 6, 5));
        primerosLunes.add(LocalDate.of(2023, 7, 3));
        primerosLunes.add(LocalDate.of(2023, 8, 7));
        primerosLunes.add(LocalDate.of(2023, 9, 4));
        primerosLunes.add(LocalDate.of(2023, 10, 2));
        primerosLunes.add(LocalDate.of(2023, 11, 6));
        primerosLunes.add(LocalDate.of(2023, 12, 4));
        return primerosLunes;
    }

    public static boolean contieneFecha(List<LocalDate> fechas, LocalDate fecha) {
        return fechas.contains(fecha);
    }

    public int verificaPrimerMesDelMes() {
        List<LocalDate> primerosLunes = obtenerPrimerosLunes();
        LocalDate fechaDada = LocalDate.now();
        if (contieneFecha(primerosLunes, fechaDada)) {//si la fecha coincide con el primer lunes del mes, entonces permite recibir archivos
            return 1;
        }
        return 0;
    }

    @Generated
    public String guardarArchivo(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null){
            if(!file.isEmpty()){
                try{
                    byte [] bytes = file.getBytes();
                    Path path  = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                }
                catch (IOException e){
                    logg.error("ERROR", e);
                }
            }
            return "Archivo guardado con exito!";
        }
        else{
            return "No se pudo guardar el archivo";
        }
    }

    @Generated
    public void leerCsv(String nombreArchivo) {
        try (Scanner scanner = new Scanner(new File(nombreArchivo))) {
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            System.out.println("Contenido del archivo:");
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");
                if (datos.length == 3) {
                    String fecha = datos[0];
                    String nota = datos[1];
                    String rut = datos[2];
                    escribirDatos(fecha,nota, rut);
                    //System.out.println("Fecha: " + fecha + ", Nota: " + nota + ", Rut: " + rut);
                } else {
                    System.out.println("Formato incorrecto en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public void escribirDatos(String fecha, String nota, String rut) {
        AlumnoEntity alumnoEntity = alumnoRepository.findByRut(rut);
        NotasEntity notasEntity = new NotasEntity();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Cambiado el formato de la fecha
        LocalDate fechaNota = LocalDate.parse(fecha, formatter);
        notasEntity.setFechaNota(fechaNota);

        int puntaje = Integer.parseInt(nota);
        notasEntity.setPuntajeNota(puntaje);
        notasEntity.setAlumno(alumnoEntity);
        notaRepository.save(notasEntity);
    }

    public int generaDescuentoPorNota(){
        List<LocalDate> primerosLunes = obtenerPrimerosLunes();
        LocalDate fechaActual = LocalDate.now();

        List<NotasEntity> notasPorFecha = notaRepository.findByFechaNota(fechaActual);
        if (contieneFecha(primerosLunes, fechaActual)) {//si la fecha coincide con el primer lunes del mes, entonces permite recibir archivos
            for (NotasEntity notas : notasPorFecha){
                String rutAlumnoRevizado = notas.getAlumno().getRut();
                List<NotasEntity> notasPorFechaYrut = notaRepository.findByAlumnoRutAndEstadoNota(rutAlumnoRevizado, 0);
                int cantNotas = notasPorFechaYrut.size();
                int i = 0;
                int sumNota = 0;
                for (NotasEntity notasPorRut : notasPorFechaYrut){
                    sumNota = sumNota + notasPorRut.getPuntajeNota();
                    notasPorRut.setEstadoNota(1);
                    if ((i + 1) == cantNotas){
                        int prom = sumNota/cantNotas;
                        //falta bucar la cuota de es fecha y aplicarle el descuento
                    }
                }
            }

            return 1;
        }
        return 0;

    }

    public float calcularDescuentoNotas(int prom){
        float descuento = 0;
        if (prom >= 950 && prom <= 1000) {
            descuento = 0.10f;
        }
        if (prom >= 900 && prom <= 949) {
            descuento = 0.5f;
        }
        if (prom >= 850 && prom <= 899) {
            descuento = 0.2f;
        }
        return descuento;
    }

}
