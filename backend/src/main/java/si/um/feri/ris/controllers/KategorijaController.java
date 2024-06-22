package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Kategorija;
import si.um.feri.ris.services.KategorijaService;

import java.util.List;

@RestController
@RequestMapping("/kategorije")
public class KategorijaController {

    @Autowired
    private KategorijaService kategorijaService;

    // TX implementacija izpisa vseh zapisov (GET)
    @GetMapping
    public List<Kategorija> getAllKategorije() {
        return kategorijaService.getAllKategorije();
    }

    // TX implementacija izpisa enega zapisa (GET - glede na ID)
    @GetMapping("/{id}")
    public ResponseEntity<Kategorija> getKategorijaById(@PathVariable Long id) {
        return kategorijaService.getKategorijaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TX dodajanje zapisa (POST)
    @PostMapping
    public Kategorija createKategorija(@RequestBody Kategorija kategorija) {
        return kategorijaService.createKategorija(kategorija);
    }

    // TX spreminjanje podatkov zapisa (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Kategorija> updateKategorija(@PathVariable Long id, @RequestBody Kategorija kategorijaDetails) {
        return kategorijaService.updateKategorija(id, kategorijaDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TX brisanje podatkov zapisa (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKategorija(@PathVariable Long id) {
        if (kategorijaService.deleteKategorija(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
