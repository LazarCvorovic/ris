package si.um.feri.ris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.um.feri.ris.models.Ocena;
import si.um.feri.ris.repositories.OcenaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OcenaService {

    @Autowired
    private OcenaRepository ocenaRepository;

    public List<Ocena> getAllOcene() {
        return ocenaRepository.findAll();
    }

    public Optional<Ocena> getOcenaById(Long id) {
        return ocenaRepository.findById(id);
    }

    public Ocena createOcena(Ocena ocena) {
        return ocenaRepository.save(ocena);
    }

    public Optional<Ocena> updateOcena(Long id, Ocena ocenaDetails) {
        return ocenaRepository.findById(id).map(ocena -> {
            ocena.setVrednost(ocenaDetails.getVrednost());
            ocena.setKomentar(ocenaDetails.getKomentar());
            ocena.setDatum(ocenaDetails.getDatum());
            ocena.setUporabnik(ocenaDetails.getUporabnik());
            ocena.setPodjetje(ocenaDetails.getPodjetje());
            return ocenaRepository.save(ocena);
        });
    }

    public boolean deleteOcena(Long id) {
        if (ocenaRepository.existsById(id)) {
            ocenaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Ocena> findByAdresaAndVrednost(String adresa, int vrednost) {
        return ocenaRepository.findByAdresaAndVrednost(adresa, vrednost);
    }

    public List<Ocena> getOcenasByImeVrednostAndNaziv(String ime, int vrednost, String naziv) {
        return ocenaRepository.findByImeVrednostAndNaziv(ime, vrednost, naziv);
    }
}
