package GUI;

import Components.Korisnik;
import JSON.JSONHandler;
import org.json.simple.JSONArray;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;

import org.json.simple.JSONObject;

import static JSON.JSONCartHandler.*;

public class ShoppingCartForm extends Form {
    JFrame frame = new JFrame();
    private JList<Object> list1;
    private DefaultListModel<Object> listModel; // Dodan DefaultListModel
    private JPanel panel1;
    private JButton kupovinaButton;
    private JButton ukloniButton;
    private JButton nazadButton;
    private Korisnik korisnik;

    public ShoppingCartForm(String title, Korisnik korisnik) throws HeadlessException {
        super(title);
        this.korisnik = korisnik;

        this.nazadButton.addActionListener(ActionEvent -> changeFrame());

        this.kupovinaButton.addActionListener(ActionEvent -> handleKupovinaButton());
        this.ukloniButton.addActionListener(e -> removeSelectedItem());

        this.panel1.setBorder(new EmptyBorder(0, 10, 10, 10));
        create(this.frame, panel1);
        fillList();



//        changeInStock();
    }

    public void changeFrame() {
        new MainClientForm("Main Form", this.korisnik);
        this.frame.dispose();
    }
    public void fillList(){
        JSONArray cartObjects = getCartObjects(korisnik.getUsername());

        listModel = new DefaultListModel<>();
        for (Object cartObject : cartObjects) {
            listModel.addElement(cartObject);
        }

        list1.setModel(listModel); // Postavljanje DefaultListModel u JList
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void removeSelectedItem() {
        int selectedIndex = list1.getSelectedIndex();
        if (selectedIndex != -1) {
            //provjera da li korisnik zaista zeli obrisati item u korpi
            int reply = JOptionPane.showConfirmDialog(null, "Jeste li sigurni?", "Confirm", JOptionPane.YES_NO_OPTION);
            if(reply == JOptionPane.NO_OPTION) return;

            Object selectedItem = list1.getSelectedValue();
                JSONObject selectedObject = (JSONObject) selectedItem;
                String isbn = selectedObject.containsKey("isbn") ? selectedObject.get("isbn").toString() : null;

                if (isbn != null) {
                    // Izbrisi iz JSON file
                    removeObjectFromCart(korisnik.getUsername(), isbn);
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(frame, "Selected item does not have an ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
                }
        } else {
            JOptionPane.showMessageDialog(frame, "Odaberite knjigu koju zelite maknuti iz korpe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //funkcija koja ce umanjivati broj primjeraka kada se item kupi
    public void changeInStock(){
        JSONArray cartObjects = getCartObjects(korisnik.getUsername());
        JSONHandler jsonHandler = new JSONHandler();

        for(Object object: cartObjects){
            HashMap<String, Object> item = (HashMap<String, Object>) object;
            String isbn = (String) item.get("isbn");

            //pronalazak knjige koja je u bazi
            JSONObject objectInShop = jsonHandler.findByIsbn(isbn);
            //brisanje zapisa u kojem stoji stara kolicina
            jsonHandler.deleteByIsbn(isbn);

            Integer inStock = Integer.parseInt(objectInShop.get("broj primjeraka").toString());
            Integer kolicina = Integer.parseInt(item.get("kolicina").toString());
            Integer newInStock = inStock - kolicina;

            objectInShop.put("broj primjeraka", newInStock);

            //ako nije ostalo primjeraka u zalihama, uciniti da proizvod bude nedostupan
            if(newInStock == 0) {
                objectInShop.put("dostupnost", false);
            }

            jsonHandler.addObjectToCart(objectInShop);
        }
    }

    private void handleKupovinaButton() {
        // Provjera da li je korppa prazna
        if (list1.getModel().getSize() == 0) {
            JOptionPane.showMessageDialog(frame, "Nemate artikala u korpi za kupovinu.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //promijena broja primjeraka nakon kupovine
        changeInStock();

       // Izbrisi sve iz json korpe
        clearCart(korisnik.getUsername());

        // Updateuj GUI listu
        clearList();

        // Prikazati poruku za uspjesnu kupovinu
        JOptionPane.showMessageDialog(frame, "Kupovina uspješna", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearList() {
        DefaultListModel listModel = new DefaultListModel();
        list1.setModel(listModel);
    }
}

