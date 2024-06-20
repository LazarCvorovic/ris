package si.um.feri.ris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.um.feri.ris.models.Uporabnik;
import si.um.feri.ris.repositories.UporabnikRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UporabnikService {

    @Autowired
    private UporabnikRepository uporabnikRepository;

    public List<Uporabnik> getAllUporabniki() {
        return uporabnikRepository.findAll();
    }

    public Optional<Uporabnik> getUporabnikById(Long id) {
        return uporabnikRepository.findById(id);
    }

    public Uporabnik createUporabnik(Uporabnik uporabnik) {
        return uporabnikRepository.save(uporabnik);
    }

    public Optional<Uporabnik> updateUporabnik(Long id, Uporabnik uporabnikDetails) {
        return uporabnikRepository.findById(id).map(uporabnik -> {
            uporabnik.setIme(uporabnikDetails.getIme());
            uporabnik.setPriimek(uporabnikDetails.getPriimek());
            uporabnik.setAdresa(uporabnikDetails.getAdresa());
            uporabnik.setTelefonskaStevilka(uporabnikDetails.getTelefonskaStevilka());
            uporabnik.setOglasavanje(uporabnikDetails.isOglasavanje());
            uporabnik.setEmail(uporabnikDetails.getEmail());
            uporabnik.setGeslo(uporabnikDetails.getGeslo());
            return uporabnikRepository.save(uporabnik);
        });
    }

    public boolean deleteUporabnik(Long id) {
        if (uporabnikRepository.existsById(id)) {
            uporabnikRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Uporabnik> findByImeAndPriimekAndOglasavanje(String ime, String priimek, boolean oglasavanje) {
        return uporabnikRepository.findByImeAndPriimekAndOglasavanje(ime, priimek, oglasavanje);
    }
}
