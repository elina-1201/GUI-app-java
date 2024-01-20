package Listeners;

import Components.Korisnik;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookListSelectListener implements ListSelectionListener {
    private JButton dodajUKorpuButton;
    private JList list;
    private Korisnik korisnik;
    private JButton izmijeniButton;
    private JButton ukloniButton;
    private JSpinner jSpinner;

    public BookListSelectListener(JButton dodajUKorpuButton, JList list, Korisnik korisnik, JButton ukloniButton, JButton izmijeniButton, JSpinner jSpinner) {
        this.dodajUKorpuButton = dodajUKorpuButton;
        this.ukloniButton = ukloniButton;
        this.izmijeniButton = izmijeniButton;
        this.jSpinner = jSpinner;
        this.list = list;
        this.korisnik = korisnik;
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        selectionControl(!list.isSelectionEmpty());
        PodesiButton();
    }

    public void selectionControl(boolean dostupnost){
        //omogucuje dugme ako je item iz liste odabran, u suprotnom ga onemogucuje
        this.dodajUKorpuButton.setEnabled(dostupnost);
        this.jSpinner.setEnabled(dostupnost);
    }

    public void PodesiButton(){
        //dohvatanje oznacenog item-a
        ArrayList<HashMap<String, Object>> selectedItem;

        //provjera i omogucavanje dugmadi se vrsi ako je nesto odabrano, u suprotnom ce dugmad biti onemogucena
        if (!this.list.isSelectionEmpty()){
            selectedItem = (ArrayList<HashMap<String, Object>>) this.list.getSelectedValuesList();

            //pretraga vlasnika
            for(Map map: selectedItem){
                String imeVlasnika = map.get("vlasnik").toString();
                Boolean dostupnost = (Boolean) map.get("dostupnost");

                //ako je artikal nedostupan onemoguciti njegovo dodavanje u korpu
                selectionControl(dostupnost);

                //ako je korisnik vlasnik artikla moze da ga mijenja i brise, u suprotnom ne moze
                if(imeVlasnika.equals(this.korisnik.getUsername())) {
                    this.izmijeniButton.setEnabled(true);
                    this.ukloniButton.setEnabled(true);
                }
                else {
                    this.izmijeniButton.setEnabled(false);
                    this.ukloniButton.setEnabled(false);
                }
            }
        }
        else {
            this.izmijeniButton.setEnabled(false);
            this.ukloniButton.setEnabled(false);
        }
    }
}
