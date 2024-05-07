package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Auditor;

@Repository
public interface AuditorRepository extends JpaRepository<Auditor, Integer> {
     /*@Query(value = "SELECT * FROM Auditores WHERE dni_auditor = :dniAuditor", nativeQuery = true)
     Auditor findByDni(@Param("dniAuditor") int dniAuditor);*/
    @Query(value = "SELECT * FROM Auditores WHERE id = :iduser", nativeQuery = true)
    Auditor findByIdUser(@Param("iduser") Long iduser);
    Auditor findByDniAuditor(int dni);
}
