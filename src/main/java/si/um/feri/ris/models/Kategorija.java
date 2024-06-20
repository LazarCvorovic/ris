package si.um.feri.ris.models;

import jakarta.persistence.*;

@Entity
public class Kategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naziv;
    private String opis;

    // Getters and Setters

    public Long getIdKategorija() {
        return id;
    }

    public void setIdKategorija(Long idKategorija) {
        this.id = id;
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
}
