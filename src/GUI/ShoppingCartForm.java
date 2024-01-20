package GUI;

import Components.Korisnik;
import org.json.simple.JSONArray;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

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


        JSONArray cartObjects = getCartObjects(korisnik.getUsername());


        listModel = new DefaultListModel<>();
        for (Object cartObject : cartObjects) {
            listModel.addElement(cartObject);
        }

        list1.setModel(listModel); // Postvaljanje DefaultListModel u JList

        this.nazadButton.addActionListener(ActionEvent -> changeFrame());
        this.kupovinaButton.addActionListener(ActionEvent -> handleKupovinaButton());


        this.ukloniButton.addActionListener(e -> removeSelectedItem());

        create(this.frame, panel1);
        this.panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
    }

    public void changeFrame() {
        new MainClientForm("Main Form", this.korisnik);
        this.frame.dispose();
    }

    private void removeSelectedItem() {
        int selectedIndex = list1.getSelectedIndex();
        if (selectedIndex != -1) {

            Object selectedItem = list1.getSelectedValue();


                JSONObject selectedObject = (JSONObject) selectedItem;
                System.out.println(selectedItem);
                String isbn = selectedObject.containsKey("isbn") ? selectedObject.get("isbn").toString() : null;

                if (isbn != null) {
                    // Izbrisi iz JSON file
                    removeObjectFromCart(korisnik.getUsername(), isbn);
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(frame, "Selected item does not have an ISBN.", "Error", JOptionPane.ERROR_MESSAGE);
                }

        } else {
            JOptionPane.showMessageDialog(frame, "Odaberite knjigu koju ćeliti maknuti iz korpe.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleKupovinaButton() {
        // Provjera d ali je korppa prazna
        if (list1.getModel().getSize() == 0) {
            JOptionPane.showMessageDialog(frame, "Nemate artikala u korpi za kupovinu.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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

