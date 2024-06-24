package si.um.feri.ris.controllers;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Oglas;
import si.um.feri.ris.services.OglasService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/oglasi")
public class OglasController {


    @Autowired
    private OglasService oglasService;

    @GetMapping
    public List<Oglas> getAllOglasi() {
        return oglasService.getAllOglasi();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oglas> getOglasById(@PathVariable Long id) {
        return oglasService.getOglasById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/pdf")
    public ResponseEntity<Oglas> createOglas(@RequestBody Oglas oglas) throws DocumentException, IOException {
        oglasService.createOglasAndGeneratePdf(oglas);
        return ResponseEntity.ok(oglas);
    }

    @PostMapping("/apply")
    public ResponseEntity<Void> applyToOglas(@RequestBody Oglas oglas) {
        try {
            oglasService.applyToOglas(oglas);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Oglas> updateOglas(@PathVariable Long id, @RequestBody Oglas oglasDetails) {
        return oglasService.updateOglas(id, oglasDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOglas(@PathVariable Long id) {
        if (oglasService.deleteOglas(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByRegija")
    public List<Oglas> findByRegija(@RequestParam String regija) {
        return oglasService.findByRegija(regija);
    }

    @GetMapping("/searchByDatumOdAfter")
    public List<Oglas> findByDatumOdAfter(@RequestParam String datumOd) {
        LocalDate datumOdDate = LocalDate.parse(datumOd);
        return oglasService.findByDatumOdAfter(datumOdDate);
    }

    @GetMapping("/searchByDatumDoBefore")
    public List<Oglas> findByDatumDoBefore(@RequestParam String datumDo) {
        LocalDate datumDoDate = LocalDate.parse(datumDo);
        return oglasService.findByDatumDoBefore(datumDoDate);
    }

    @GetMapping("/searchByAdresa")
    public List<Oglas> findByAdresa(@RequestParam String adresa) {
        return oglasService.findByAdresa(adresa);
    }

    @GetMapping("/search")
    public List<Oglas> findByFilters(
            @RequestParam(required = false) String regija,
            @RequestParam(required = false) String datumOd,
            @RequestParam(required = false) String datumDo,
            @RequestParam(required = false) String adresa) {
        LocalDate datumOdDate = datumOd != null ? LocalDate.parse(datumOd) : null;
        LocalDate datumDoDate = datumDo != null ? LocalDate.parse(datumDo) : null;
        return oglasService.findByFilters(regija, datumOdDate, datumDoDate, adresa);
    }
}