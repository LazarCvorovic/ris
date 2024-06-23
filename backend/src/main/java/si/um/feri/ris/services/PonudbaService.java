package si.um.feri.ris.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.um.feri.ris.models.Ponudba;
import si.um.feri.ris.repositories.PonudbaRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PonudbaService {

    private final PonudbaRepository ponudbaRepository;

    @Autowired
    public PonudbaService(PonudbaRepository ponudbaRepository) {
        this.ponudbaRepository = ponudbaRepository;
    }

    public List<Ponudba> getPonudbasByEmailAndCenaAndNaslov(String email, BigDecimal cena, String naslov) {
        return ponudbaRepository.findByEmailAndCenaAndNaslov(email, cena, naslov);
    }
}
