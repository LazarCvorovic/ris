package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import si.um.feri.ris.models.Uporabnik;

import java.util.List;

public interface UporabnikRepository extends JpaRepository<Uporabnik, Long> {

    @Query("SELECT u FROM Uporabnik u WHERE u.ime = :ime AND u.priimek = :priimek AND u.oglasavanje = :oglasavanje")
    List<Uporabnik> findByImeAndPriimekAndOglasavanje(@Param("ime") String ime, @Param("priimek") String priimek, @Param("oglasavanje") boolean oglasavanje);

    @Query("SELECT u FROM Uporabnik u WHERE u.email = :email AND u.geslo = :geslo")
    List<Uporabnik> findByEmailAndGeslo(String email, String geslo);

    @Query("SELECT u from Uporabnik u where u.email = :email")
    List<Uporabnik> findByEmail(String email);

}
