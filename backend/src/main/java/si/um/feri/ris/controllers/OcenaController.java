package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Ocena;
import si.um.feri.ris.services.OcenaService;

import java.util.List;

@RestController
@RequestMapping("/ocene")
public class OcenaController {

    @Autowired
    private OcenaService ocenaService;

    // TX implementacija izpisa vseh zapisov (GET)
    @GetMapping
    public List<Ocena> getAllOcene() {
        return ocenaService.getAllOcene();
    }

    // TX implementacija izpisa enega zapisa (GET - glede na ID)
    @GetMapping("/{id}")
    public ResponseEntity<Ocena> getOcenaById(@PathVariable Long id) {
        return ocenaService.getOcenaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TX dodajanje zapisa (POST)
    @PostMapping
    public Ocena createOcena(@RequestBody Ocena ocena) {
        return ocenaService.createOcena(ocena);
    }

    // TX spreminjanje podatkov zapisa (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Ocena> updateOcena(@PathVariable Long id, @RequestBody Ocena ocenaDetails) {
        return ocenaService.updateOcena(id, ocenaDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TX brisanje podatkov zapisa (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOcena(@PathVariable Long id) {
        if (ocenaService.deleteOcena(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // TX kompleksnejša poizvedba z dvema parametroma
    @GetMapping("/searchByAdresaAndVrednost")
    public List<Ocena> findByAdresaAndVrednost(@RequestParam String adresa, @RequestParam int vrednost) {
        return ocenaService.findByAdresaAndVrednost(adresa, vrednost);
    }

    // 2.TX kompleksnejša poizvedba z tri modela
    @GetMapping("/ocene")
    public List<Ocena> getOcenas(@RequestParam String ime,
                                 @RequestParam int vrednost,
                                 @RequestParam String naziv) {
        return ocenaService.getOcenasByImeVrednostAndNaziv(ime, vrednost, naziv);
    }
}
