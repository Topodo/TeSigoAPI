package cl.usach.apitesis.repository;

import cl.usach.apitesis.entities.Alumno;
import cl.usach.apitesis.entities.FormularioEvidencia;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FormularioEvidenciaRepository extends PagingAndSortingRepository<FormularioEvidencia, Long> {
    Iterable<FormularioEvidencia> findAllByAlumno(Alumno alumno);
}
