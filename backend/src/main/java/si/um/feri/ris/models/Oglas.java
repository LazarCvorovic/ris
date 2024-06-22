package si.um.feri.ris.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Oglas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOglas;

    private String opis;
    private LocalDate datumOd;
    private LocalDate datumDo;
    private String adresa;
    private boolean otkazano;
    private String regija;
    private String naslov;
    private String telefonskaStevilka;
    private String enaslov;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lokacija_idLokacija")
    private Lokacija lokacija;

    // Getters and Setters

    public Long getIdOglas() {
        return idOglas;
    }

    public void setIdOglas(Long idOglas) {
        this.idOglas = idOglas;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDate getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(LocalDate datumOd) {
        this.datumOd = datumOd;
    }

    public LocalDate getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(LocalDate datumDo) {
        this.datumDo = datumDo;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public boolean isOtkazano() {
        return otkazano;
    }

    public void setOtkazano(boolean otkazano) {
        this.otkazano = otkazano;
    }

    public String getRegija() {
        return regija;
    }

    public void setRegija(String regija) {
        this.regija = regija;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTelefonskaStevilka() {
        return telefonskaStevilka;
    }

    public void setTelefonskaStevilka(String telefonskaStevilka) {
        this.telefonskaStevilka = telefonskaStevilka;
    }

    public String getEnaslov() {
        return enaslov;
    }

    public void setEnaslov(String enaslov) {
        this.enaslov = enaslov;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }
}
