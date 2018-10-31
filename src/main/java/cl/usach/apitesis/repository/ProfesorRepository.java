package cl.usach.apitesis.repository;

import cl.usach.apitesis.entities.Profesor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProfesorRepository extends PagingAndSortingRepository<Profesor, Long> {
    Profesor findProfesorByFirebaseUID(String id);
}
