package si.um.feri.ris.controllers;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Oglas;
import si.um.feri.ris.services.OglasService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/oglasi")
public class OglasController {

    @Autowired
    private OglasService oglasService;

    // TX implementacija izpisa vseh zapisov (GET)
    @GetMapping
    public List<Oglas> getAllOglasi() {
        return oglasService.getAllOglasi();
    }

    // TX implementacija izpisa enega zapisa (GET - glede na ID)
    @GetMapping("/{id}")
    public ResponseEntity<Oglas> getOglasById(@PathVariable Long id) {
        return oglasService.getOglasById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



    // TX spreminjanje podatkov zapisa (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Oglas> updateOglas(@PathVariable Long id, @RequestBody Oglas oglasDetails) {
        return oglasService.updateOglas(id, oglasDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TX brisanje podatkov zapisa (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOglas(@PathVariable Long id) {
        if (oglasService.deleteOglas(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // TX kompleksnejša poizvedba z dvema parametroma
    @GetMapping("/searchByRegijaAndDatum")
    public List<Oglas> findByRegijaAndDatumOdAfter(@RequestParam String regija, @RequestParam String datumOd) {
        LocalDate datumOdDate = LocalDate.parse(datumOd);
        return oglasService.findByRegijaAndDatumOdAfter(regija, datumOdDate);
    }

    // TX kompleksnejša poizvedba s tremi parametri
    @GetMapping("/searchByRegijaDatumOtkazano")
    public List<Oglas> findByRegijaAndDatumOdAfterAndOtkazano(@RequestParam String regija, @RequestParam String datumOd, @RequestParam boolean otkazano) {
        LocalDate datumOdDate = LocalDate.parse(datumOd);
        return oglasService.findByRegijaAndDatumOdAfterAndOtkazano(regija, datumOdDate, otkazano);
    }

    // TX kompleksnejša poizvedba z dvema parametroma
    @GetMapping("/searchByMestoAndOtkazano")
    public List<Oglas> findByMestoAndOtkazano(@RequestParam String mesto, @RequestParam boolean otkazano) {
        return oglasService.findByMestoAndOtkazano(mesto, otkazano);
    }

    @PostMapping("/pdf")
    public ResponseEntity<Oglas> createOglas(@RequestBody Oglas oglas) throws DocumentException, IOException {
        oglasService.createOglasAndGeneratePdf(oglas);
        return ResponseEntity.ok(oglas);
    }

    // 3.TX kompleksnejša poizvedba z tri modela
    @GetMapping("/oglasi")
    public List<Oglas> getOglasi(@RequestParam int povrsina,
                                 @RequestParam String mesto,
                                 @RequestParam String naslov) {
        return oglasService.getOglasByPovrsinaMestoAndNaslov(povrsina, mesto, naslov);
    }
}
