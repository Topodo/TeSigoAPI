package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.Curso;
import cl.usach.apitesis.entities.Profesor;
import cl.usach.apitesis.entities.Unidad;
import cl.usach.apitesis.repository.ProfesorRepository;
import cl.usach.apitesis.repository.UnidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
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
@RequestMapping("/profesor")
public class ProfesorService {

    @Autowired
    ProfesorRepository profesorRepository;
    @Autowired
    UnidadRepository unidadRepository;

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

    // Método que obtiene las unidades asociadas a un curso y que son dictadas por un profesor en específico
    @RequestMapping(value = "/{idProfesor}/curso/{idCurso}/unidades",
            method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Unidad> getUnidadesOfProfesorFilteredByCurso(@PathVariable("idProfesor") String firebaseUID, @PathVariable("idCurso") Long idCurso) {
        Profesor profesor = this.profesorRepository.findProfesorByFirebaseUID(firebaseUID);
        Set<Unidad> unidades = profesor.getUnidades();
        Set<Unidad> unidadesSet = new HashSet<>();
        // Se filtran las unidades que no pertenecen al curso
        for (Unidad unidad : unidades) {
            if (unidad.getCurso().getIdCurso() == idCurso) {
                unidadesSet.add(unidad);
            }
        }
        return unidadesSet;
    }
}
