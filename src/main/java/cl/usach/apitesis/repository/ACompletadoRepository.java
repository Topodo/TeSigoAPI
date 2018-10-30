package cl.usach.apitesis.repository;

import cl.usach.apitesis.entities.ACompletado;
import cl.usach.apitesis.entities.Alumno;
import cl.usach.apitesis.entities.IndicadorEvaluacion;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ACompletadoRepository extends PagingAndSortingRepository<ACompletado, Long> {
    ACompletado findACompletadoByIdACompletado(Long idACompletado);
    ACompletado findACompletadoByIndicadorEvaluacionAndAlumno(IndicadorEvaluacion indicadorEvaluacion, Alumno alumno);
}
