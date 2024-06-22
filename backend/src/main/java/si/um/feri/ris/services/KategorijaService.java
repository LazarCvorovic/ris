package si.um.feri.ris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.um.feri.ris.models.Kategorija;
import si.um.feri.ris.repositories.KategorijaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KategorijaService {

    @Autowired
    private KategorijaRepository kategorijaRepository;

    public List<Kategorija> getAllKategorije() {
        return kategorijaRepository.findAll();
    }

    public Optional<Kategorija> getKategorijaById(Long id) {
        return kategorijaRepository.findById(id);
    }

    public Kategorija createKategorija(Kategorija kategorija) {
        return kategorijaRepository.save(kategorija);
    }

    public Optional<Kategorija> updateKategorija(Long id, Kategorija kategorijaDetails) {
        return kategorijaRepository.findById(id).map(kategorija -> {
            kategorija.setNaziv(kategorijaDetails.getNaziv());
            kategorija.setOpis(kategorijaDetails.getOpis());
            return kategorijaRepository.save(kategorija);
        });
    }

    public boolean deleteKategorija(Long id) {
        if (kategorijaRepository.existsById(id)) {
            kategorijaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
