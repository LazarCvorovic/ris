package si.um.feri.ris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.um.feri.ris.models.Oglas;
import si.um.feri.ris.repositories.OglasRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OglasService {

    @Autowired
    private OglasRepository oglasRepository;

    public List<Oglas> getAllOglasi() {
        return oglasRepository.findAll();
    }

    public Optional<Oglas> getOglasById(Long id) {
        return oglasRepository.findById(id);
    }

    public Oglas createOglas(Oglas oglas) {
        return oglasRepository.save(oglas);
    }

    public Optional<Oglas> updateOglas(Long id, Oglas oglasDetails) {
        return oglasRepository.findById(id).map(oglas -> {
            oglas.setOpis(oglasDetails.getOpis());
            oglas.setDatumOd(oglasDetails.getDatumOd());
            oglas.setDatumDo(oglasDetails.getDatumDo());
            oglas.setAdresa(oglasDetails.getAdresa());
            oglas.setOtkazano(oglasDetails.isOtkazano());
            oglas.setRegija(oglasDetails.getRegija());
            oglas.setNaslov(oglasDetails.getNaslov());
            oglas.setTelefonskaStevilka(oglasDetails.getTelefonskaStevilka());
            oglas.setEnaslov(oglasDetails.getEnaslov());
            oglas.setLokacija(oglasDetails.getLokacija());
            return oglasRepository.save(oglas);
        });
    }

    public boolean deleteOglas(Long id) {
        if (oglasRepository.existsById(id)) {
            oglasRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Oglas> findByRegijaAndDatumOdAfter(String regija, LocalDate datumOd) {
        return oglasRepository.findByRegijaAndDatumOdAfter(regija, datumOd);
    }

    public List<Oglas> findByRegijaAndDatumOdAfterAndOtkazano(String regija, LocalDate datumOd, boolean otkazano) {
        return oglasRepository.findByRegijaAndDatumOdAfterAndOtkazano(regija, datumOd, otkazano);
    }

    public List<Oglas> findByMestoAndOtkazano(String mesto, boolean otkazano) {
        return oglasRepository.findByMestoAndOtkazano(mesto, otkazano);
    }
}
