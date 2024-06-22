package si.um.feri.ris.requests;

public class AddUporabnikRequest {
    private String ime;
    private String priimek;
    private String adresa;
    private String telefonskaStevilka;
    private boolean oglasavanje;
    private String email;
    private String geslo;

    public AddUporabnikRequest() {

    }

    public AddUporabnikRequest(String ime, String priimek, String adresa, String telefonskaStevilka, boolean oglasavanje, String email, String geslo) {
        this.ime = ime;
        this.priimek = priimek;
        this.adresa = adresa;
        this.telefonskaStevilka = telefonskaStevilka;
        this.oglasavanje = oglasavanje;
        this.email = email;
        this.geslo = geslo;
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