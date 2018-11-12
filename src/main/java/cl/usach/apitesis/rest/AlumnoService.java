package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.*;
import cl.usach.apitesis.repository.AlumnoRepository;
import cl.usach.apitesis.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
