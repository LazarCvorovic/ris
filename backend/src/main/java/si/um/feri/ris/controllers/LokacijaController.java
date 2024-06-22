package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Lokacija;
import si.um.feri.ris.services.LokacijaService;

import java.util.List;

@RestController
@RequestMapping("/lokacije")
public class LokacijaController {

    @Autowired
    private LokacijaService lokacijaService;

    // TX implementacija izpisa vseh zapisov (GET)
    @GetMapping
    public List<Lokacija> getAllLokacije() {
        return lokacijaService.getAllLokacije();
    }

    // TX implementacija izpisa enega zapisa (GET - glede na ID)
    @GetMapping("/{id}")
    public ResponseEntity<Lokacija> getLokacijaById(@PathVariable Long id) {
        return lokacijaService.getLokacijaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TX dodajanje zapisa (POST)
    @PostMapping
    public Lokacija createLokacija(@RequestBody Lokacija lokacija) {
        return lokacijaService.createLokacija(lokacija);
    }

    // TX spreminjanje podatkov zapisa (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Lokacija> updateLokacija(@PathVariable Long id, @RequestBody Lokacija lokacijaDetails) {
        return lokacijaService.updateLokacija(id, lokacijaDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TX brisanje podatkov zapisa (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLokacija(@PathVariable Long id) {
        if (lokacijaService.deleteLokacija(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
