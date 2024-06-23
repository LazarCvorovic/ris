package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import si.um.feri.ris.models.Ponudba;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PonudbaRepository extends CrudRepository<Ponudba, Long> {

    @Query("SELECT p FROM Ponudba p " +
            "JOIN FETCH p.uporabnik u " +
            "JOIN FETCH p.oglas o " +
            "WHERE u.email = :email " +
            "AND p.cena >= :cena " +
            "AND o.naslov LIKE %:naslov%")
    List<Ponudba> findByEmailAndCenaAndNaslov(String email, BigDecimal cena, String naslov);
}