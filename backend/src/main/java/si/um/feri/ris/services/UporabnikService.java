package si.um.feri.ris.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.um.feri.ris.models.Uporabnik;
import si.um.feri.ris.repositories.UporabnikRepository;
import si.um.feri.ris.requests.AddUporabnikRequest;

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

    public Optional<Uporabnik> authenticate(String email, String geslo) throws Exception {
        Optional<Uporabnik> uporabnik = uporabnikRepository.findByEmailAndGeslo(email, geslo);
        if (uporabnik.isPresent()) {
            return uporabnik;
        }
        throw new Exception("Not authorized");
    }

    public Uporabnik save(AddUporabnikRequest uporabnik) {
        Uporabnik u = new Uporabnik();
        u.setIme(uporabnik.getIme());
        u.setPriimek(uporabnik.getPriimek());
        u.setAdresa(uporabnik.getAdresa());
        u.setTelefonskaStevilka(uporabnik.getTelefonskaStevilka());
        u.setOglasavanje(uporabnik.isOglasavanje());
        u.setEmail(uporabnik.getEmail());
        u.setGeslo(uporabnik.getGeslo());
        return uporabnikRepository.saveAndFlush(u);
    }

    public Optional<Uporabnik> findByEmailAndGeslo(String email, String geslo) {
        return uporabnikRepository.findByEmailAndGeslo(email, geslo);
    }

    public Optional<Uporabnik> findByEmail(String email) {
        return uporabnikRepository.findByEmail(email);
    }

    public boolean isAdmin(String email) {
        Optional<Uporabnik> uporabnik = findByEmail(email);
        return uporabnik.isPresent() && uporabnik.get().isAdmin();
    }

    @PostConstruct
    public void init() {
        if (uporabnikRepository.findByEmail("ekipa2.lazar@gmail.com").isEmpty()) {
            Uporabnik admin = new Uporabnik();
            admin.setIme("Admin");
            admin.setPriimek("Admin");
            admin.setAdresa("Admin Adresa");
            admin.setTelefonskaStevilka("000000000");
            admin.setOglasavanje(false);
            admin.setEmail("ekipa2.lazar@gmail.com");
            admin.setGeslo("lazarcvorovic");
            admin.setAdmin(true);
            uporabnikRepository.save(admin);
        }
    }
}
