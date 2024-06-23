package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import si.um.feri.ris.models.Ponudba;
import si.um.feri.ris.services.PonudbaService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class PonudbaController {

    private final PonudbaService ponudbaService;

    @Autowired
    public PonudbaController(PonudbaService ponudbaService) {
        this.ponudbaService = ponudbaService;
    }
    // 1.TX kompleksnejša poizvedba z vsaj 3 modela
    @GetMapping("/ponudbe")
    public List<Ponudba> getPonudbe(@RequestParam String email,
                                    @RequestParam BigDecimal cena,
                                    @RequestParam String naslov) {
        return ponudbaService.getPonudbasByEmailAndCenaAndNaslov(email, cena, naslov);
    }
}
