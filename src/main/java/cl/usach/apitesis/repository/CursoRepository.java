package cl.usach.apitesis.repository;

import cl.usach.apitesis.entities.Curso;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CursoRepository extends PagingAndSortingRepository<Curso, Long> {
    Curso findCursoByIdCurso(Long id);

}
