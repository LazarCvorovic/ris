package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import si.um.feri.ris.models.Oglas;

import java.time.LocalDate;
import java.util.List;

public interface OglasRepository extends JpaRepository<Oglas, Long> {

    @Query("SELECT o FROM Oglas o WHERE o.regija = :regija AND o.datumOd >= :datumOd")
    List<Oglas> findByRegijaAndDatumOdAfter(@Param("regija") String regija, @Param("datumOd") LocalDate datumOd);

    @Query("SELECT o FROM Oglas o WHERE o.regija = :regija AND o.datumOd >= :datumOd AND o.otkazano = :otkazano")
    List<Oglas> findByRegijaAndDatumOdAfterAndOtkazano(@Param("regija") String regija, @Param("datumOd") LocalDate datumOd, @Param("otkazano") boolean otkazano);

    @Query("SELECT o FROM Oglas o WHERE o.lokacija.mesto = :mesto AND o.otkazano = :otkazano")
    List<Oglas> findByMestoAndOtkazano(@Param("mesto") String mesto, @Param("otkazano") boolean otkazano);

    @Query("SELECT o FROM Oglas o " +
            "JOIN FETCH o.lokacija l " +
            "JOIN FETCH l.tipLokacije t " +
            "WHERE t.povrsina >= :povrsina " +
            "AND l.mesto = :mesto " +
            "AND o.naslov LIKE %:naslov%")
    List<Oglas> findByPovrsinaMestoAndNaslov(int povrsina, String mesto, String naslov);
}


