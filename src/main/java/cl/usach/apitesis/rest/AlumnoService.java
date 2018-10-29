package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.*;
import cl.usach.apitesis.repository.AlumnoRepository;
import cl.usach.apitesis.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
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
}
