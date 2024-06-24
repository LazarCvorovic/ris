package si.um.feri.ris.models;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Uporabnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUporabnik;

    private String ime;
    private String priimek;
    private String adresa;
    private String telefonskaStevilka;
    private boolean oglasavanje;
    private String email;
    private String geslo;
    private boolean isAdmin;  // Novo polje za administratorsku ulogu

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Long getIdUporabnik() {
        return idUporabnik;
    }

    public void setIdUporabnik(Long idUporabnik) {
        this.idUporabnik = idUporabnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefonskaStevilka() {
        return telefonskaStevilka;
    }

    public void setTelefonskaStevilka(String telefonskaStevilka) {
        this.telefonskaStevilka = telefonskaStevilka;
    }

    public boolean isOglasavanje() {
        return oglasavanje;
    }

    public void setOglasavanje(boolean oglasavanje) {
        this.oglasavanje = oglasavanje;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGeslo() {
        return geslo;
    }

    public void setGeslo(String geslo) {
        this.geslo = geslo;
    }


}

