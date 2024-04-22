package pe.cibertec.auditoriaInternaCovisian.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cibertec.auditoriaInternaCovisian.models.bd.Lider;

public interface LiderRepository extends JpaRepository<Lider,Integer> {
    @Query(value = "SELECT * FROM Lider WHERE id = :iduser", nativeQuery = true)
    Lider findByIdUser(@Param("iduser") Long iduser);
    Lider findByDniLider(int dni);
}
