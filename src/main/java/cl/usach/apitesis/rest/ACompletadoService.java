package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.ACompletado;
import cl.usach.apitesis.repository.ACompletadoRepository;
import cl.usach.apitesis.repository.AlumnoRepository;
import cl.usach.apitesis.repository.IndicadorEvaluacionRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @RequestMapping(value = "/update/{idIndicador}/{idAlumno}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ACompletado vincularACompletadoAlumno(@RequestBody String json, @PathVariable("idIndicador") Long idIndicador, @PathVariable("idAlumno") Long idAlumno) throws ParseException {
        ACompletado aCompletado = this.aCompletadoRepository.findACompletadoByIndicadorEvaluacionAndAlumno(
                this.indicadorEvaluacionRepository.findByIdIndicador(idIndicador),
                this.alumnoRepository.findAlumnoByIdAlumno(idAlumno));
        JSONParser parser = new JSONParser();
        JSONObject responseBody = (JSONObject) parser.parse(json);
        aCompletado.setIndicadorCompletado((Boolean) responseBody.get("status"));
        return this.aCompletadoRepository.save(aCompletado);
    }

    // Método PUT que actualiza una lista de indicadores de evaluación para un alumno
    @RequestMapping(value = "/update/{idAlumno}/indicadores",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Iterable<ACompletado> actualizarIndicadores(@RequestBody String json, @PathVariable("idAlumno") Long idAlumno) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(json);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            ACompletado aCompletado = this.aCompletadoRepository.findACompletadoByIndicadorEvaluacionAndAlumno(
                    this.indicadorEvaluacionRepository.findByIdIndicador((Long) jsonObject.get("idIndicador")),
                    this.alumnoRepository.findAlumnoByIdAlumno(idAlumno));
            aCompletado.setIndicadorCompletado((Boolean) jsonObject.get("status"));
            this.aCompletadoRepository.save(aCompletado);
        }
        return this.aCompletadoRepository.findAll();
    }
}
