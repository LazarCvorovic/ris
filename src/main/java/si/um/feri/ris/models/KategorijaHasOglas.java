package si.um.feri.ris.models;

import jakarta.persistence.*;

@Entity
public class KategorijaHasOglas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategorija_idKategorija")
    private Kategorija kategorija;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oglas_idOglas")
    private Oglas oglas;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }
}
