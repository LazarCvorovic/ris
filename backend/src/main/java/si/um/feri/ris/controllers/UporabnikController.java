package si.um.feri.ris.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import si.um.feri.ris.models.Uporabnik;
import si.um.feri.ris.requests.AddUporabnikRequest;
import si.um.feri.ris.services.UporabnikService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/uporabniki")
public class UporabnikController {

    @Autowired
    private UporabnikService uporabnikService;

    // TX implementacija izpisa vseh zapisov (GET)
    @GetMapping
    public List<Uporabnik> getAllUporabniki() {
        return uporabnikService.getAllUporabniki();
    }

    // TX implementacija izpisa enega zapisa (GET - glede na ID)
    @GetMapping("/{id}")
    public ResponseEntity<Uporabnik> getUporabnikById(@PathVariable Long id) {
        return uporabnikService.getUporabnikById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TX dodajanje zapisa (POST)
    @PostMapping
    public Uporabnik createUporabnik(@RequestBody Uporabnik uporabnik) {
        return uporabnikService.createUporabnik(uporabnik);
    }

    @CrossOrigin
    @PostMapping("/add")
    public Uporabnik addUporabnik(@RequestBody AddUporabnikRequest uporabnik) {
        return uporabnikService.save(uporabnik);
    }

    // TX spreminjanje podatkov zapisa (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Uporabnik> updateUporabnik(@PathVariable Long id, @RequestBody Uporabnik uporabnikDetails) {
        return uporabnikService.updateUporabnik(id, uporabnikDetails)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // TX brisanje podatkov zapisa (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUporabnik(@PathVariable Long id) {
        if (uporabnikService.deleteUporabnik(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // TX kompleksnejša poizvedba z vsaj 3 parametri
    @GetMapping("/search")
    public List<Uporabnik> findByImeAndPriimekAndOglasavanje(@RequestParam String ime, @RequestParam String priimek, @RequestParam boolean oglasavanje) {
        return uporabnikService.findByImeAndPriimekAndOglasavanje(ime, priimek, oglasavanje);
    }

    @PostMapping("/login/{email}/{geslo}")
    public ResponseEntity<Map<String, String>> login(@PathVariable String email, @PathVariable String geslo, HttpServletResponse response) throws Exception {
        Uporabnik u = uporabnikService.authenticate(email, geslo);
        if (u != null) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("id", String.valueOf(u.getIdUporabnik()));
            responseBody.put("email", u.getEmail());
            responseBody.put("message", "Uspješna prijava");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } else {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Neuspješna prijava");
            return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/email/{email}")
    public List<Uporabnik> findByEmail(@PathVariable String email){
        return uporabnikService.findByEmail(email);
    }

}