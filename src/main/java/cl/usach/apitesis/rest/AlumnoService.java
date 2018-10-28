package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.Alumno;
import cl.usach.apitesis.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/alumno")
public class AlumnoService {

    @Autowired
    AlumnoRepository alumnoRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Alumno> getAllAlumnos() {
        return this.alumnoRepository.findAll();
    }
}
