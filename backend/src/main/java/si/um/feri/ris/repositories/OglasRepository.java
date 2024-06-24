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

    @Query("SELECT o FROM Oglas o WHERE o.adresa = :adresa")
    List<Oglas> findByAdresa(@Param("adresa") String adresa);

    @Query("SELECT o FROM Oglas o WHERE o.regija = :regija")
    List<Oglas> findByRegija(@Param("regija") String regija);

    @Query("SELECT o FROM Oglas o WHERE o.datumOd >= :datumOd")
    List<Oglas> findByDatumOdAfter(@Param("datumOd") LocalDate datumOd);

    @Query("SELECT o FROM Oglas o WHERE o.datumDo <= :datumDo")
    List<Oglas> findByDatumDoBefore(@Param("datumDo") LocalDate datumDo);

    @Query("SELECT o FROM Oglas o WHERE (:regija IS NULL OR o.regija = :regija) AND " +
            "(:datumOd IS NULL OR o.datumOd >= :datumOd) AND " +
            "(:datumDo IS NULL OR o.datumDo <= :datumDo) AND " +
            "(:adresa IS NULL OR o.adresa = :adresa)")
    List<Oglas> findByFilters(@Param("regija") String regija, @Param("datumOd") LocalDate datumOd,
                              @Param("datumDo") LocalDate datumDo, @Param("adresa") String adresa);


}

