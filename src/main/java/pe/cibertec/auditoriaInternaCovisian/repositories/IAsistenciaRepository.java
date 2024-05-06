package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Asistencia;

public interface IAsistenciaRepository extends JpaRepository<Asistencia, Integer> {
}
