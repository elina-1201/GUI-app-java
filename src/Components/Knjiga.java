package Components;

public class Knjiga extends Resurs {

    private String isbn;
    private String autor;
    private int brojStranica;
    private String kategorija;

    public Knjiga(){}
    public Knjiga(String ime, String opis, double cijena, boolean dostupnost, Korisnik vlasnik, String autor, String isbn, int brojStranica, String kategorija, int brPrimjeraka) {
        super(ime, opis, cijena, dostupnost, brPrimjeraka, vlasnik);
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
        if(brojStranica > 0) {
            this.brojStranica = brojStranica;
        }
    }
    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getKategorija() {
        return kategorija;
    }

    public String  getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
