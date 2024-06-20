package si.um.feri.ris.models;

import jakarta.persistence.*;

@Entity
public class Podkategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPodkategorija;

    private String naziv;
    private String opis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategorija_idKategorija")
    private Kategorija kategorija;

    // Getters and Setters

    public Long getIdPodkategorija() {
        return idPodkategorija;
    }

    public void setIdPodkategorija(Long idPodkategorija) {
        this.idPodkategorija = idPodkategorija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }
}
