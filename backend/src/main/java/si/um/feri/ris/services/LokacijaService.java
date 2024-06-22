package si.um.feri.ris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.um.feri.ris.models.Lokacija;
import si.um.feri.ris.repositories.LokacijaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LokacijaService {

    @Autowired
    private LokacijaRepository lokacijaRepository;

    public List<Lokacija> getAllLokacije() {
        return lokacijaRepository.findAll();
    }

    public Optional<Lokacija> getLokacijaById(Long id) {
        return lokacijaRepository.findById(id);
    }

    public Lokacija createLokacija(Lokacija lokacija) {
        return lokacijaRepository.save(lokacija);
    }

    public Optional<Lokacija> updateLokacija(Long id, Lokacija lokacijaDetails) {
        return lokacijaRepository.findById(id).map(lokacija -> {
            lokacija.setMesto(lokacijaDetails.getMesto());
            lokacija.setPostnaStevilka(lokacijaDetails.getPostnaStevilka());
            return lokacijaRepository.save(lokacija);
        });
    }

    public boolean deleteLokacija(Long id) {
        if (lokacijaRepository.existsById(id)) {
            lokacijaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
