package si.um.feri.ris.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Ponudba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPonudba;

    private BigDecimal cena;
    private String opis;
    private boolean prekinjeno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uporabnik_idUporabnik")
    private Uporabnik uporabnik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oglas_idOglas")
    private Oglas oglas;

    // Getters and Setters

    public Long getIdPonudba() {
        return idPonudba;
    }

    public void setIdPonudba(Long idPonudba) {
        this.idPonudba = idPonudba;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public boolean isPrekinjeno() {
        return prekinjeno;
    }

    public void setPrekinjeno(boolean prekinjeno) {
        this.prekinjeno = prekinjeno;
    }

    public Uporabnik getUporabnik() {
        return uporabnik;
    }

    public void setUporabnik(Uporabnik uporabnik) {
        this.uporabnik = uporabnik;
    }

    public Oglas getOglas() {
        return oglas;
    }

    public void setOglas(Oglas oglas) {
        this.oglas = oglas;
    }
}
