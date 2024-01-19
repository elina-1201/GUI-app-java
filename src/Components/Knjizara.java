package Components;

import java.util.ArrayList;
import java.util.List;


public class Knjizara {
    private List<Knjiga> listaKnjiga;

    public Knjizara() {
       this.listaKnjiga= new ArrayList<>();
    }

    public void dodavanjeKnjige(Knjiga knjiga){
        listaKnjiga.add(knjiga);
    }
    public void obrisiKnjigu(String ime){
        listaKnjiga.removeIf(knjiga -> knjiga.getIme().equals(ime));
    }
    public void uredjivanjeKnjige(String ime, Knjiga novaKnjiga){
        for (Knjiga knjiga : listaKnjiga)
        {
            if(knjiga.getIme().equals(ime))
            {
//                knjiga.setIme(novaKnjiga.getIme());
//                knjiga.setOpis(novaKnjiga.getOpis());
//                knjiga.setCijena(novaKnjiga.setCijena(););
//                knjiga.setDatumKreiranja(novaKnjiga.setDatumKreiranja(););
//                knjiga.setDostupnost(novaKnjiga.setDostupnost(););
//                knjiga.setAutor(novaKnjiga.setAutor(););
//                knjiga.setBrojStranica(novaKnjiga.setBrojStranica(););

            }
        }
    }

}
