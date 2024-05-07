package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Sala;

public interface ISalaRepository extends JpaRepository<Sala, Integer> {
}
