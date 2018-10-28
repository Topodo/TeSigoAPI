package cl.usach.apitesis.repository;

import cl.usach.apitesis.entities.Alumno;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Integer> {
}
