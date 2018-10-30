package cl.usach.apitesis.repository;

import cl.usach.apitesis.entities.IndicadorEvaluacion;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IndicadorEvaluacionRepository extends PagingAndSortingRepository<IndicadorEvaluacion, Long> {
    IndicadorEvaluacion findByIdIndicador(Long idIndicador);
}
