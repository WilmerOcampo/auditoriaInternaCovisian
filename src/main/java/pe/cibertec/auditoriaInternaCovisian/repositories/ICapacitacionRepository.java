package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Capacitacion;

public interface ICapacitacionRepository extends JpaRepository<Capacitacion, Integer> {
}
