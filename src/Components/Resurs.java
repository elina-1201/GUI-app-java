package Components;

public class Resurs {
    private String ime;
    private String opis;
    private double cijena;
    private boolean dostupnost;
    private int brPrimjeraka;
    private Korisnik vlasnik;

    public Resurs() {
    }

    public Resurs(String ime, String opis, double cijena, boolean dostupnost, int brPrimjeraka, Korisnik vlasnik) {
        this.ime = ime;
        this.opis = opis;
        this.cijena = cijena;
        this.dostupnost = dostupnost;
        this.brPrimjeraka = brPrimjeraka;
        this.vlasnik = vlasnik;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCijena() {
        return cijena;
    }

    public Korisnik getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(Korisnik vlasnik) {
        this.vlasnik = vlasnik;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public boolean isDostupnost() {
        return dostupnost;
    }

    public void setDostupnost(boolean dostupnost) {
        if(this.brPrimjeraka == 0) {
            this.dostupnost = false;
        }
        else {
            this.dostupnost = dostupnost;
        }
    }

    public void setBrPrimjeraka(int brPrimjeraka) {
        if(brPrimjeraka >= 0) {
            this.brPrimjeraka = brPrimjeraka;
        }
    }

    public int getBrPrimjeraka() {
        return brPrimjeraka;
    }
}
