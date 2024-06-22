package si.um.feri.ris.models;

import jakarta.persistence.*;

@Entity
public class Podjetje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPodjetje;

    private String naziv;
    private String adresa;
    private String telefonskaStevilka;
    private String email;

    // Getters and Setters

    public Long getIdPodjetje() {
        return idPodjetje;
    }

    public void setIdPodjetje(Long idPodjetje) {
        this.idPodjetje = idPodjetje;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
