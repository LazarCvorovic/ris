package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import si.um.feri.ris.models.Lokacija;

public interface LokacijaRepository extends JpaRepository<Lokacija, Long> {
}
