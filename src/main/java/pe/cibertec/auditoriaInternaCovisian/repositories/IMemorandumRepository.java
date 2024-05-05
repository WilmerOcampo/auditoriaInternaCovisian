package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Memorandum;

public interface IMemorandumRepository extends JpaRepository<Memorandum, Integer> {
}
