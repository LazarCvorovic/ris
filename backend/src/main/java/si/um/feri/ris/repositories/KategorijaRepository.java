package si.um.feri.ris.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import si.um.feri.ris.models.Kategorija;

public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {
}
