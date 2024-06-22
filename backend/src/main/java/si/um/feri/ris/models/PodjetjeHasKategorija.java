package si.um.feri.ris.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PodjetjeHasKategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate datumDodajanja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "podjetje_idPodjetje")
    private Podjetje podjetje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategorija_idKategorija")
    private Kategorija kategorija;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Podjetje getPodjetje() {
        return podjetje;
    }

    public void setPodjetje(Podjetje podjetje) {
        this.podjetje = podjetje;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }
}
