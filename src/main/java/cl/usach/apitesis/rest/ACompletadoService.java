package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.ACompletado;
import cl.usach.apitesis.repository.ACompletadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/acompletado")
public class ACompletadoService {
    @Autowired
    ACompletadoRepository aCompletadoRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<ACompletado> getAllAlumnos() {
        return this.aCompletadoRepository.findAll();
    }
}
