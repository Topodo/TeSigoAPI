package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.ACompletado;
import cl.usach.apitesis.entities.Alumno;
import cl.usach.apitesis.entities.ObjetivoAprendizaje;
import cl.usach.apitesis.entities.Unidad;
import cl.usach.apitesis.repository.AlumnoRepository;
import cl.usach.apitesis.repository.CursoRepository;
import cl.usach.apitesis.repository.UnidadRepository;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
@RequestMapping("/unidad")
public class UnidadService {

    @Autowired
    AlumnoRepository alumnoRepository;
    @Autowired
    UnidadRepository unidadRepository;
    @Autowired
    CursoRepository cursoRepository;


    // Método que obtiene todos los indicadores de evaluación completados de un alumno y un objetivo de aprendizaje en particular
    public JSONArray getIndicadoresCompletados(Long idAlumno, Long idOA) throws JSONException {
        Alumno alumno = this.alumnoRepository.findAlumnoByIdAlumno(idAlumno);
        Iterable<ACompletado> aCompletado = alumno.getCompletados();
        JSONArray indicadoresPorAlumnoYOA = new JSONArray();
        for (ACompletado completado : aCompletado) {
            JSONObject indicadorToJSON = new JSONObject();
            ObjetivoAprendizaje objetivoAprendizaje = completado.getIndicadorEvaluacion().getObjetivoAprendizaje();
            if (objetivoAprendizaje.getIdObjetivo() == idOA) {
                indicadorToJSON.put("id", completado.getIndicadorEvaluacion().getIdIndicador());
                indicadorToJSON.put("description", completado.getIndicadorEvaluacion().getDescripcionIndicador());
                indicadorToJSON.put("isComplete", completado.getIndicadorCompletado());
                indicadoresPorAlumnoYOA.add(indicadorToJSON);
            }
        }
        return indicadoresPorAlumnoYOA;
    }

    // Método que obtiene todos los objetivos de aprendizaje de una unidad
    private JSONArray getOAs(Long idUnidad, Long idAlumno) throws JSONException {
        Unidad unidad = this.unidadRepository.findUnidadByIdUnidad(idUnidad);
        JSONArray OAsArray = new JSONArray();
        for (ObjetivoAprendizaje OA : unidad.getObjetivosAprendizaje()) {
            JSONObject OAToJSON = new JSONObject();
            OAToJSON.put("id", OA.getIdObjetivo());
            OAToJSON.put("name", OA.getDescripcionObjetivo());
            JSONArray indicadoresPorOA = getIndicadoresCompletados(idAlumno, OA.getIdObjetivo());
            int completados = 0;
            int total = 0;
            for (Object indicador : indicadoresPorOA) {
                JSONObject indicadorAux = (JSONObject) indicador;
                if ((Boolean) indicadorAux.get("isComplete")) {
                    completados++;
                }
                total++;
            }
            OAToJSON.put("percentage", ((double) completados / (double) total));
            OAToJSON.put("evalIndicators", indicadoresPorOA);
            OAsArray.add(OAToJSON);
        }
        return OAsArray;
    }

    // Método que obtiene el JSON resultante con todas las unidades y la evolución de un alumno en particular
    @RequestMapping(value = "/{id_unidad}/alumno/{id_alumno}", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getUnidad(@PathVariable("id_unidad") Long idUnidad, @PathVariable("id_alumno") Long idAlumno) throws JSONException {
        JSONObject unidadToJSON = new JSONObject();
        Unidad unidad = this.unidadRepository.findUnidadByIdUnidad(idUnidad);
        unidadToJSON.put("id", unidad.getIdUnidad());
        unidadToJSON.put("name", unidad.getNombreUnidad());
        unidadToJSON.put("OAs", getOAs(unidad.getIdUnidad(), idAlumno));
        JSONObject finalUnidad = new JSONObject();
        finalUnidad.put("subjects", unidadToJSON);
        return finalUnidad;
    }

    // Método que obtiene el JSON resultante con todas las unidades y la evolución de un alumno en particular
    @RequestMapping(value = "all/alumno/{id_alumno}", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getUnidades(@PathVariable("id_alumno") Long idAlumno) throws JSONException {
        Set<Unidad> unidades = this.alumnoRepository.findAlumnoByIdAlumno(idAlumno)
                .getCurso()
                .getUnidades();
        JSONObject unidadToJSON;
        JSONArray arrayUnidades = new JSONArray();
        // Se agrega el contenido de cada unidad al JSON y luego se agrega al arreglo
        for (Unidad unidad : unidades) {
            unidadToJSON = new JSONObject();
            unidadToJSON.put("id", unidad.getIdUnidad());
            unidadToJSON.put("name", unidad.getNombreUnidad());
            unidadToJSON.put("OAs", getOAs(unidad.getIdUnidad(), idAlumno));
            arrayUnidades.add(unidadToJSON);
        }
        return arrayUnidades;
    }

    // Método que obtiene el avance de un curso en una unidad
    @RequestMapping(value = "/{idUnidad}/curso/{idCurso}/avance",
            method = RequestMethod.GET)
    @ResponseBody
    public JSONArray avanceCurso(@PathVariable("idUnidad") Long idUnidad, @PathVariable("idCurso") Long idCurso) throws JSONException {
        Set<ObjetivoAprendizaje> OAs = this.unidadRepository.findUnidadByIdUnidad(idUnidad).getObjetivosAprendizaje();
        Set<Alumno> alumnos = this.cursoRepository.findCursoByIdCurso(idCurso).getAlumnos();
        JSONArray avanceOAs = new JSONArray();
        JSONArray promedios = new JSONArray();
        for (Alumno alumno : alumnos) {
            avanceOAs.add(this.getOAs(idUnidad, alumno.getIdAlumno()));
        }
        // Se calcula el promedio de avance para el curso
        int porcentaje = 0;
        for (ObjetivoAprendizaje OA : OAs) {
            for (Object avanceOA : avanceOAs) {
                for (Object subOA : (JSONArray) avanceOA) {
                    JSONObject aux = (JSONObject) subOA;
                    if (OA.getIdObjetivo() == (Long) aux.get("id")) {
                        if ((double) aux.get("percentage") == 1) {
                            porcentaje++;
                        }
                    }
                }
            }
            JSONObject json = new JSONObject();
            json.put("idOA", OA.getIdObjetivo());
            json.put("percentage", (double) porcentaje / (double) alumnos.size());
            json.put("OAName", OA.getDescripcionObjetivo());
            promedios.add(json);
            porcentaje = 0;
        }
        return promedios;
    }
}

