package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.Curso;
import cl.usach.apitesis.entities.Profesor;
import cl.usach.apitesis.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/profesor")
public class ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;

    // Método que obtiene los datos de un profesor
    @RequestMapping(value = "/{firebase_id}", method = RequestMethod.GET)
    @ResponseBody
    public Profesor getProfesorByFirebaseID(@PathVariable("firebase_id") String firebaseID) {
        return this.profesorRepository.findProfesorByFirebaseUID(firebaseID);
    }

    // Método que obtiene los cursos de un profesor
    @RequestMapping(value = "/{id}/cursos", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Curso> getCursos(@PathVariable("id") String firebaseUID) {
        return this.profesorRepository.findProfesorByFirebaseUID(firebaseUID).getCursos();
    }

    // Método POST que agrega un nuevo profesor a la base de datos
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Profesor create(@RequestBody Profesor profesor) {
        return this.profesorRepository.save(profesor);
    }
}
