package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.ACompletado;
import cl.usach.apitesis.repository.ACompletadoRepository;
import cl.usach.apitesis.repository.AlumnoRepository;
import cl.usach.apitesis.repository.IndicadorEvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/acompletado")
public class ACompletadoService {
    @Autowired
    ACompletadoRepository aCompletadoRepository;
    @Autowired
    AlumnoRepository alumnoRepository;
    @Autowired
    IndicadorEvaluacionRepository indicadorEvaluacionRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Iterable<ACompletado> getCompletadosPorAlumno() {
        return this.aCompletadoRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ACompletado getById(@PathVariable("id") Long idACompletado) {
        return this.aCompletadoRepository.findACompletadoByIdACompletado(idACompletado);
    }


    // Método que vincula un alumno a su respectivo indicador completado
    @RequestMapping(value = "/update/{idIndicador}/{idAlumno}", method = RequestMethod.GET)
    @ResponseBody
    public ACompletado vincularACompletadoAlumno(@PathVariable("idIndicador") Long idIndicador, @PathVariable("idAlumno") Long idAlumno) {
        ACompletado aCompletado = this.aCompletadoRepository.findACompletadoByIndicadorEvaluacionAndAlumno(
                this.indicadorEvaluacionRepository.findByIdIndicador(idIndicador),
                this.alumnoRepository.findAlumnoByIdAlumno(idAlumno));
        aCompletado.setIndicadorCompletado(!aCompletado.getIndicadorCompletado());
        return this.aCompletadoRepository.save(aCompletado);
    }
}
