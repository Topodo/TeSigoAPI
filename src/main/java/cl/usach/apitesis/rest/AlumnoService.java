package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.*;
import cl.usach.apitesis.repository.AlumnoRepository;
import cl.usach.apitesis.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(
        origins = "*",
        allowedHeaders = {"X-PINGOTHER",
                "Content-Type",
                "X-Requested-With",
                "Accept","Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "Authorization","Access-Control-Allow-Origin"},
        exposedHeaders = {"Access-Control-Allow-Credentials",
                "Access-Control-Allow-Origin",
                "Cache-Control",
                "Content-Encoding",
                "Expires",
                "Pragma"})
@RestController
@RequestMapping("/alumno")
public class AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;
    @Autowired
    UnidadRepository unidadRepository;

    // Método que obtiene todos los alumnos
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Alumno> getAllAlumnos() {
        return this.alumnoRepository.findAll();
    }

    // Método que obtiene todos los reportes realizados sobre un alumno
    @RequestMapping(
            value = "{id_alumno}/reportes",
            method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Reporte> getReportes(@PathVariable("id_alumno") Long idAlumno) {
        return this.alumnoRepository.findAlumnoByIdAlumno(idAlumno).getReportes();
    }

    // Método que agrega un nuevo reporte a un alumno
    @RequestMapping(
            value = "{id_alumno}/reporte/nuevo",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Iterable<Reporte> createReport(@PathVariable("id_alumno") Long idAlumno, @RequestBody Reporte reporte){
        Alumno alumno = this.alumnoRepository.findAlumnoByIdAlumno(idAlumno);
        Set<Reporte> reportes = alumno.getReportes();
        reporte.setAlumno(alumno);
        reportes.add(reporte);
        alumno.setReportes(reportes);
        this.alumnoRepository.save(alumno);
        return this.alumnoRepository.findAlumnoByIdAlumno(idAlumno).getReportes();
    }
}
