package si.um.feri.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Ocena;
import si.um.feri.ris.services.OcenaService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/ocene")
public class OcenaController {

    @Autowired
    private OcenaService ocenaService;

    @GetMapping
    public List<Ocena> getAllOcene() {
        return ocenaService.getAllOcene();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ocena> getOcenaById(@PathVariable Long id) {
        return ocenaService.getOcenaById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ocena> addOcena(@RequestBody Ocena ocena) {
        Ocena novaOcena = ocenaService.addOcena(ocena);
        return ResponseEntity.ok(novaOcena);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ocena> updateOcena(@PathVariable Long id, @RequestBody Ocena ocenaDetails) {
        return ocenaService.updateOcena(id, ocenaDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOcena(@PathVariable Long id) {
        if (ocenaService.deleteOcena(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByAdresaAndVrednost")
    public List<Ocena> findByAdresaAndVrednost(@RequestParam String adresa, @RequestParam int vrednost) {
        return ocenaService.findByAdresaAndVrednost(adresa, vrednost);
    }

    @GetMapping("/oglas/{oglasId}")
    public List<Ocena> getOceneByOglas(@PathVariable Long oglasId) {
        return ocenaService.getOceneByOglas(oglasId);
    }
}