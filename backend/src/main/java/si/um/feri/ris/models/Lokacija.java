package si.um.feri.ris.models;

import jakarta.persistence.*;

@Entity
public class Lokacija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLokacija;

    private String mesto;
    private int postnaStevilka;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTipLokacije")
    private Tip_lokacije tipLokacije;

    // Getters and Setters

    public Long getIdLokacija() {
        return idLokacija;
    }

    public void setIdLokacija(Long idLokacija) {
        this.idLokacija = idLokacija;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public int getPostnaStevilka() {
        return postnaStevilka;
    }

    public void setPostnaStevilka(int postnaStevilka) {
        this.postnaStevilka = postnaStevilka;
    }

    public Tip_lokacije getTipLokacije() {
        return tipLokacije;
    }

    public void setTipLokacije(Tip_lokacije tipLokacije) {
        this.tipLokacije = tipLokacije;
    }
}
