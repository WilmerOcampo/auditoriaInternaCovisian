package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Asistencia;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Capacitacion;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Memorandum;

import java.util.List;

public interface ICapacitacionRepository extends JpaRepository<Capacitacion, Integer> {
}
