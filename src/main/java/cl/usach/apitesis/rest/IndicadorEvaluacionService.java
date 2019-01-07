package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.ACompletado;
import cl.usach.apitesis.entities.Alumno;
import cl.usach.apitesis.repository.ACompletadoRepository;
import cl.usach.apitesis.repository.AlumnoRepository;
import cl.usach.apitesis.repository.CursoRepository;
import cl.usach.apitesis.repository.IndicadorEvaluacionRepository;
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
@RequestMapping("/indicadorEvaluacion")
public class IndicadorEvaluacionService {
    @Autowired
    AlumnoRepository alumnoRepository;
    @Autowired
    ACompletadoRepository aCompletadoRepository;
    @Autowired
    IndicadorEvaluacionRepository indicadorEvaluacionRepository;
    @Autowired
    CursoRepository cursoRepository;

    @RequestMapping(value = "/{idIE}/curso/{idCurso}/ordenar",
            method = RequestMethod.GET)
    @ResponseBody
    public JSONObject ordenar(@PathVariable("idIE") Long idIE, @PathVariable("idCurso") Long idCurso) {
        Set<Alumno> alumnos = this.cursoRepository.findCursoByIdCurso(idCurso).getAlumnos();
        JSONObject resultado = new JSONObject();
        JSONArray completados = new JSONArray();
        JSONArray noCompletados = new JSONArray();
        for (Alumno alumno : alumnos) {
            JSONObject alumnoJSON = new JSONObject();
            alumnoJSON.put("name", alumno.getNombreAlumno() + ' ' + alumno.getApellidoPaternoAlumno() + ' ' + alumno.getApellidoMaternoAlumno());
            alumnoJSON.put("idStudent", alumno.getIdAlumno());
            ACompletado aCompletado = this.aCompletadoRepository.findACompletadoByIndicadorEvaluacionAndAlumno(
                    this.indicadorEvaluacionRepository.findByIdIndicador(idIE),
                    this.alumnoRepository.findAlumnoByIdAlumno(alumno.getIdAlumno()));
            if (aCompletado.getIndicadorCompletado()) {
                completados.add(alumnoJSON);
            } else {
                noCompletados.add(alumnoJSON);
            }
        }
        resultado.put("IEName", this.indicadorEvaluacionRepository.findByIdIndicador(idIE).getDescripcionIndicador());
        resultado.put("idIE", idIE);
        resultado.put("completeList", completados);
        resultado.put("incompleteList", noCompletados);
        return resultado;
    }
}
