package cl.usach.apitesis.repository;

import cl.usach.apitesis.entities.IndicadorEvaluacion;
import cl.usach.apitesis.entities.ObjetivoAprendizaje;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ObjetivoAprendizajeRepository extends PagingAndSortingRepository<ObjetivoAprendizaje, Long> {
    ObjetivoAprendizaje findByIdObjetivo(Long idObjetivo);
}
