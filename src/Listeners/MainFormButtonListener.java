package Listeners;

import Components.Korisnik;
import GUI.AddOrUpdateForm;
import GUI.ShoppingCartForm;
import JSON.JSONHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MainFormButtonListener implements ActionListener {
    private JButton button;
    private JFrame frame;
    private Korisnik korisnik;
    private JList list;

    //konstruktor za UKLANJANJE
    public MainFormButtonListener(JButton button, JFrame frame, Korisnik korisnik, JList list) {
        this.button = button;
        this.frame = frame;
        this.korisnik = korisnik;
        this.list = list;
    }

    public MainFormButtonListener(JButton button, JFrame frame, Korisnik korisnik) {
        this.button = button;
        this.frame = frame;
        this.korisnik = korisnik;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String buttonText = this.button.getText();

        switch (buttonText) {
            case "Dodaj novu knjigu":
                new AddOrUpdateForm("Shopping Cart", this.korisnik);
                this.frame.dispose();
                break;

            case "Izmijeni":
                new AddOrUpdateForm("Shopping Cart", this.korisnik, this.list);
                this.frame.dispose();
                break;

            case "Ukloni":
                delete();
                break;

            case "Korpa":
                new ShoppingCartForm("Shopping cart", this.korisnik);
                this.frame.dispose();
                break;
        }
    }

    public void delete() {
        //dohvatanje selektovanog item-a
        ArrayList<HashMap<String, Object>> selectedItem = (ArrayList<HashMap<String, Object>>) this.list.getSelectedValuesList();
        HashMap<String, Object> item = selectedItem.get(0);

        JSONHandler jsonHandler = new JSONHandler();
        //pozivanje brisanja na osnovu isbn
        jsonHandler.deleteByIsbn((String) item.get("isbn"));

        //dohvatanje svih knjiga iz JSON file-a
        ArrayList<HashMap<String, Object>> result = jsonHandler.read();

        //inicijalizacija listModela
        DefaultListModel listModel1 = new DefaultListModel();

        //dodavanje svakog item-a iz file-a u model
        for(Object jsonObject: result) {
            listModel1.addElement(jsonObject);
        }

        //dodavanje modela u listu
        list.setModel(listModel1);
        //omogucavanje selekcije za samo jedan item
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
