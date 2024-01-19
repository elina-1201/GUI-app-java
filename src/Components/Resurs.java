package Components;

import java.util.Date;

public class Resurs {
    private String ime;
    private String opis;
    private double cijena;
    private String datumKreiranja;
    private boolean dostupnost;



    public Resurs(String ime, String opis, double cijena, String datumKreiranja, boolean dostupnost) {
        this.ime = ime;
        this.opis = opis;
        this.cijena = cijena;
        this.datumKreiranja = datumKreiranja;
        this.dostupnost = dostupnost;
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

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

    public String getDatumKreiranja() {
        return datumKreiranja;
    }

    public void setDatumKreiranja(String datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    public boolean isDostupnost() {
        return dostupnost;
    }

    public void setDostupnost(boolean dostupnost) {
        this.dostupnost = dostupnost;
    }


}
