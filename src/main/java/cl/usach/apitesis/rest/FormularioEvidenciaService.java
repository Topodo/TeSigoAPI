package cl.usach.apitesis.rest;

import cl.usach.apitesis.entities.Alumno;
import cl.usach.apitesis.entities.FormularioEvidencia;
import cl.usach.apitesis.repository.AlumnoRepository;
import cl.usach.apitesis.repository.FormularioEvidenciaRepository;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
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
@RequestMapping("/formularioEvidencia")
public class FormularioEvidenciaService {

    @Autowired
    AlumnoRepository alumnoRepository;
    @Autowired
    FormularioEvidenciaRepository formularioEvidenciaRepository;

    // Util: Método que retorna como un JSON Array las evidencias
    public JSONArray getEvidencias(Long idAlumno) throws JSONException {
        JSONArray array = new JSONArray();
        List<String> tipos = Arrays.asList(
                "video",
                "photo",
                "audio");
        for (String tipo : tipos) {
            JSONObject json = new JSONObject();
            json.put("tipo", tipo);
            json.put("evidencias", this.formularioEvidenciaRepository.findAllByAlumnoAndTipoEvidencia(
                    this.alumnoRepository.findAlumnoByIdAlumno(idAlumno),
                    tipo));
            array.add(json);
        }
        return array;
    }

    // Método que obtiene todos los formularios de un alumno
    @RequestMapping(value = "/alumno/{id_alumno}")
    @ResponseBody
    public JSONArray getAllByAlumno(@PathVariable("id_alumno") Long idAlumno) throws JSONException {
        return this.getEvidencias(idAlumno);
    }

    // Método que crea un nuevo formulario para un alumno específico
    @RequestMapping(
            value = "/alumno/{id_alumno}",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Iterable<FormularioEvidencia> create(@RequestBody FormularioEvidencia formularioEvidencia, @PathVariable("id_alumno") Long idAlumno) throws JSONException {
        Alumno alumno = this.alumnoRepository.findAlumnoByIdAlumno(idAlumno);
        Set<FormularioEvidencia> evidencias = alumno.getEvidencias();
        evidencias.add(formularioEvidencia);
        alumno.setEvidencias(evidencias); // Se añade la nueva evidencia del alumno
        formularioEvidencia.setAlumno(alumno);
        this.formularioEvidenciaRepository.save(formularioEvidencia);
        return this.getEvidencias(idAlumno);
    }
}
