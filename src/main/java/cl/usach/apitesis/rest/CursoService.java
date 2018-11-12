package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.Alumno;
import cl.usach.apitesis.entities.Curso;
import cl.usach.apitesis.entities.Unidad;
import cl.usach.apitesis.repository.CursoRepository;
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
@RequestMapping("/curso")
public class CursoService {
    @Autowired
    CursoRepository cursoRepository;

    // Método que obtiene todos los cursos
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Curso> getCursos() {
        return this.cursoRepository.findAll();
    }

    // Método que obtiene la lista de alumnos de un curso
    @RequestMapping(value = "/{id}/alumnos")
    @ResponseBody
    public Iterable<Alumno> getAlumnos(@PathVariable("id") Long idCurso) {
        return this.cursoRepository.findCursoByIdCurso(idCurso).getAlumnos();
    }

    // Método que obtiene todos las unidades de un curso
    @RequestMapping(value = "/{id}/unidades", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Unidad> getUnidades(@PathVariable("id") Long idCurso) {
        return this.cursoRepository.findCursoByIdCurso(idCurso).getUnidades();
    }
}
