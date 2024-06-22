package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import si.um.feri.ris.models.Ocena;

import java.util.List;

public interface OcenaRepository extends JpaRepository<Ocena, Long> {

    @Query("SELECT o FROM Ocena o WHERE o.podjetje.adresa = :adresa AND o.vrednost = :vrednost")
    List<Ocena> findByAdresaAndVrednost(@Param("adresa") String adresa, @Param("vrednost") int vrednost);
}
