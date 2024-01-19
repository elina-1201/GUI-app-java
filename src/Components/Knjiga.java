package Components;

import java.util.Date;

public class Knjiga extends Resurs {
 private String autor;
 private int brojStranica;
 private String kategorija;

    public Knjiga(String ime, String opis, double cijena, String datumKreiranja, boolean dostupnost, String autor, int brojStranica, String kategorija) {
        super(ime, opis, cijena, datumKreiranja, dostupnost);
        this.autor = autor;
        this.brojStranica = brojStranica;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getBrojStranica() {
        return brojStranica;
    }

    public void setBrojStranica(int brojStranica) {
        this.brojStranica = brojStranica;
    }
    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getKategorija() {
        return kategorija;
    }
}
