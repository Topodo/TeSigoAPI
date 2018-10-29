package cl.usach.apitesis.repository;

import cl.usach.apitesis.entities.Unidad;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UnidadRepository extends PagingAndSortingRepository<Unidad, Long> {
    Unidad findUnidadByIdUnidad(Long idUnidad);
}
