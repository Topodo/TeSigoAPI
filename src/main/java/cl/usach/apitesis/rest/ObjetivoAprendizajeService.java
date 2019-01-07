package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.ACompletado;
import cl.usach.apitesis.entities.Alumno;
import cl.usach.apitesis.entities.IndicadorEvaluacion;
import cl.usach.apitesis.entities.ObjetivoAprendizaje;
import cl.usach.apitesis.repository.AlumnoRepository;
import cl.usach.apitesis.repository.CursoRepository;
import cl.usach.apitesis.repository.IndicadorEvaluacionRepository;
import cl.usach.apitesis.repository.ObjetivoAprendizajeRepository;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(
        origins = "*",
        allowedHeaders = {"X-PINGOTHER",
                "Content-Type",
                "X-Requested-With",
                "Accept", "Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers",
                "Authorization", "Access-Control-Allow-Origin"},
        exposedHeaders = {"Access-Control-Allow-Credentials",
                "Access-Control-Allow-Origin",
                "Cache-Control",
                "Content-Encoding",
                "Expires",
                "Pragma"})
@RestController
@RequestMapping("/objetivoAprendizaje")
public class ObjetivoAprendizajeService {

    @Autowired
    CursoRepository cursoRepository;
    @Autowired
    ObjetivoAprendizajeRepository objetivoAprendizajeRepository;
    @Autowired
    AlumnoRepository alumnoRepository;

    // Método que obtiene todos los indicadores de evaluación completados de un alumno y un objetivo de aprendizaje en particular
    private JSONArray getIndicadoresCompletados(Long idAlumno, Long idOA) throws JSONException {
        Alumno alumno = this.alumnoRepository.findAlumnoByIdAlumno(idAlumno);
        Iterable<ACompletado> aCompletado = alumno.getCompletados();
        JSONArray indicadoresPorAlumnoYOA = new JSONArray();
        for (ACompletado completado : aCompletado) {
            JSONObject indicadorToJSON = new JSONObject();
            ObjetivoAprendizaje objetivoAprendizaje = completado.getIndicadorEvaluacion().getObjetivoAprendizaje();
            if (objetivoAprendizaje.getIdObjetivo() == idOA) {
                indicadorToJSON.put("id", completado.getIndicadorEvaluacion().getIdIndicador());
                indicadorToJSON.put("isComplete", completado.getIndicadorCompletado());
                indicadoresPorAlumnoYOA.add(indicadorToJSON);
            }
        }
        return indicadoresPorAlumnoYOA;
    }

    @RequestMapping(value = "/{idOA}/curso/{idCurso}/avance",
            method = RequestMethod.GET)
    @ResponseBody
    public JSONArray avanceCurso(@PathVariable("idOA") Long idOA, @PathVariable("idCurso") Long idCurso) throws JSONException {
        Set<Alumno> alumnos = this.cursoRepository.findCursoByIdCurso(idCurso).getAlumnos();
        Set<IndicadorEvaluacion> indicadoresEvaluacion = this.objetivoAprendizajeRepository.findByIdObjetivo(idOA).getIndicadoresEvaluacion();
        JSONArray avanceIEs = new JSONArray();
        JSONArray avanceCurso = new JSONArray();
        for (Alumno alumno : alumnos) {
            avanceIEs.add(this.getIndicadoresCompletados(alumno.getIdAlumno(), idOA));
        }
        // Se calcula la cantidad de alumnos que han completado cada uno de los IE del OA
        int completados = 0;
        for (IndicadorEvaluacion IE : indicadoresEvaluacion) {
            for (Object avanceIE : avanceIEs) {
                for (Object subIE : (JSONArray)avanceIE) {
                    JSONObject aux = (JSONObject) subIE;
                    if ((Long)aux.get("id") == IE.getIdIndicador()) {
                        if ((Boolean)aux.get("isComplete")) {
                            completados++;
                        }
                    }
                }
            }
            JSONObject json = new JSONObject();
            json.put("idIE", IE.getIdIndicador());
            json.put("IEName", IE.getDescripcionIndicador());
            json.put("completeCount", completados);
            json.put("incompleteCount", alumnos.size() - completados);
            avanceCurso.add(json);
            completados = 0;
        }
        return avanceCurso;
    }
}
