package si.um.feri.ris.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.um.feri.ris.models.Ocena;
import si.um.feri.ris.models.Uporabnik;
import si.um.feri.ris.repositories.OcenaRepository;
import si.um.feri.ris.repositories.UporabnikRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OcenaService {

    @Autowired
    private OcenaRepository ocenaRepository;

    @Autowired
    private UporabnikRepository uporabnikRepository;

    public List<Ocena> getAllOcene() {
        return ocenaRepository.findAll();
    }

    public Optional<Ocena> getOcenaById(Long id) {
        return ocenaRepository.findById(id);
    }

    @Transactional
    public Ocena addOcena(Ocena ocena) {
        Uporabnik uporabnik = ocena.getUporabnik();
        if (uporabnik.getIdUporabnik() == null) {
            uporabnikRepository.save(uporabnik);
        }
        return ocenaRepository.save(ocena);
    }

    private void initializeLazyProperties(Ocena ocena) {
        if (ocena.getUporabnik() != null) {
            ocena.getUporabnik().getIme();
        }
        if (ocena.getPodjetje() != null) {
            ocena.getPodjetje().getNaziv();
        }
    }

    @Transactional
    public Optional<Ocena> updateOcena(Long id, Ocena ocenaDetails) {
        return ocenaRepository.findById(id).map(ocena -> {
            ocena.setVrednost(ocenaDetails.getVrednost());
            ocena.setOglas(ocenaDetails.getOglas());
            ocena.setUporabnik(ocenaDetails.getUporabnik());
            return ocenaRepository.save(ocena);
        });
    }

    @Transactional
    public boolean deleteOcena(Long id) {
        if (ocenaRepository.existsById(id)) {
            ocenaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Ocena> findByAdresaAndVrednost(String adresa, int vrednost) {
        return ocenaRepository.findByAdresaAndVrednost(adresa, vrednost);
    }

    public List<Ocena> getOcenasByImeVrednostAndNaziv(String ime, int vrednost, String naziv) {
        return ocenaRepository.findByImeVrednostAndNaziv(ime, vrednost, naziv);
    }

    public List<Ocena> getOceneByOglas(Long oglasId) {
        return ocenaRepository.findByOglasIdOglas(oglasId);
    }
}