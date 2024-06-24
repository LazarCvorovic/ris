package si.um.feri.ris.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Ocena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOcena;

    private int vrednost;
    private String komentar;
    private LocalDate datum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uporabnik_idUporabnik")
    private Uporabnik uporabnik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "podjetje_idPodjetje")
    private Podjetje podjetje;

    @ManyToOne
    @JoinColumn(name = "oglas_id")
    private Oglas oglas;

    // Getters and Setters

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }

    public Long getIdOcena() {
        return idOcena;
    }

    public void setIdOcena(Long idOcena) {
        this.idOcena = idOcena;
    }

    public int getVrednost() {
        return vrednost;
    }

    public void setVrednost(int vrednost) {
        this.vrednost = vrednost;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    public Podjetje getPodjetje() {
        return podjetje;
    }

    public void setPodjetje(Podjetje podjetje) {
        this.podjetje = podjetje;
    }
}
