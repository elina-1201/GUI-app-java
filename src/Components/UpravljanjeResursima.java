package Components;

import java.util.ArrayList;
import java.util.List;


public class UpravljanjeResursima {
    private List<Resurs> listaResursa;

    public UpravljanjeResursima(List<Resurs> listaResursa) {
        this.listaResursa = listaResursa;
    }
    public void dodajResurs(Resurs resurs)
    {
        listaResursa.add(resurs);
    }

public void obrisiResurs(String ime){
        listaResursa.removeIf(resurs ->resurs.getIme().equals(ime));
}
public void uredjivanjeResursa(String ime, Resurs noviResurs){
        for(int i =0; i< listaResursa.size();i++)
        {
            Resurs resurs= listaResursa.get(i);
            if(resurs.getIme().equals(ime))
            {
                listaResursa.set(i, noviResurs);
                break;
            }
        }
}

}
