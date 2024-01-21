package GUI;

import Components.Korisnik;
import JSON.JSONCartHandler;
import Listeners.BookListSelectListener;
import Listeners.MainFormButtonListener;
import Listeners.SearchListener;
import JSON.JSONHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static JSON.JSONCartHandler.addObjectToCart;

public class MainClientForm extends Form {
    Korisnik korisnik;
    JSONHandler jsonHandler = new JSONHandler();
    private JFrame frame = new JFrame();
    private JPanel panel1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton dodajNovuKnjiguButton;
    private JButton dodajUKorpuButton;
    private JSpinner spinner1;
    private JList list;
    private JButton ukloniButton;
    private JButton izmijeniButton;
    private JButton ocistiButton;
    private JButton korpaButton;
    private JButton odjavaButton;

    public MainClientForm(String title, Korisnik korisnik) {
        super(title);
        this.korisnik = korisnik;

        //listener koji prati polozaj kursora, koristi se za PRETRAGU
        this.textField1.addCaretListener(new SearchListener(this.comboBox1, this.textField1, this.list));
        //listener na IZMJENU knjige
        this.izmijeniButton.addActionListener(new MainFormButtonListener(this.izmijeniButton, this.frame, this.korisnik, this.list));
        //listener na DODAVANJE nove knjige
        this.dodajNovuKnjiguButton.addActionListener(new MainFormButtonListener(this.dodajNovuKnjiguButton, this.frame, this.korisnik));
        //listener na dugme za UKLANJANJE
        this.ukloniButton.addActionListener(new MainFormButtonListener(this.ukloniButton, this.frame, this.korisnik, this.list));
        this.ocistiButton.addActionListener(ActionEvent -> {this.textField1.setText("");});
        //listener na dugme za KORPU
        this.korpaButton.addActionListener(new MainFormButtonListener(this.korpaButton,this.frame, this.korisnik));
        //listener na dugme za DODAVANJE U KORPU
        this.dodajUKorpuButton.addActionListener(ActionEvent -> addSelectedItemToCart());
        //listener na dugme ODJAVA
        this.odjavaButton.addActionListener(actionEvent -> odjava());

        //listener koji prati OZNACENI proizvod
        this.list.addListSelectionListener(new BookListSelectListener(this.dodajUKorpuButton, this.list, this.korisnik, this.ukloniButton, this.izmijeniButton, this.spinner1));

        //kreiranje forme
        create(this.frame, this.panel1);
        fillList();
        fillCategory();
        disableButtons();
    }

    public void fillList() {
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
        //uspostavljanje razmaka izmedju item-a
        list.setFixedCellHeight(30);
    }

    private void addSelectedItemToCart() {
        int kolicina = 0;
        try {
            kolicina = (int) spinner1.getValue();
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Pogresno unesen broj", "Error", JOptionPane.ERROR_MESSAGE);
        }

        if(kolicina <= 0) {
            JOptionPane.showMessageDialog(frame, "kolicina treba da bude pozitivan cijeli broj", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Dobivanje selektovanog itema
        Object selectedItem = list.getSelectedValue();

        if (selectedItem instanceof HashMap) {
            HashMap<String, Object> selectedBook = (HashMap<String, Object>) selectedItem;

            JSONCartHandler jsonCartHandler = new JSONCartHandler();
            JSONArray cartArray = jsonCartHandler.getCartObjects(this.korisnik.getUsername());

            //iteracija kroz knjige u korpi radi provjere da li je item vec dodat
            for (Object object: cartArray) {
                HashMap<String, Object> item = (HashMap<String, Object>) object;
                String isbn = (String) item.get("isbn");
                String selectedIsbn = (String) selectedBook.get("isbn");

                if(isbn.equals(selectedIsbn)) {
                    JOptionPane.showMessageDialog(frame, "Knjiga je vec dodata u korpu");
                    return;
                }
            }

            Integer inStock = Integer.parseInt(selectedBook.get("broj primjeraka").toString());
            if(inStock < kolicina) {
                JOptionPane.showMessageDialog(frame, "nema toliko primjeraka", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Novi json object
            JSONObject selectedObject = new JSONObject();

            // Sva svojstva u json object
            selectedObject.putAll(selectedBook);

            // Dodavanje u korpu u jsonu
            addObjectToCart(korisnik.getUsername(), selectedObject, kolicina);
            JOptionPane.showMessageDialog(frame, "Knjiga: '" + selectedBook.get("ime") + "' je uspjesno dodana u korpu");
        } else {
            JOptionPane.showMessageDialog(frame, "Select a book to add to the cart.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void fillCategory() {
        ArrayList result = jsonHandler.read();
        Map element = (Map) result.get(0);
        for(Object object: element.keySet()){
            this.comboBox1.addItem(object);
        }
    }

    public void disableButtons() {
        this.izmijeniButton.setEnabled(false);
        this.ukloniButton.setEnabled(false);
        this.dodajUKorpuButton.setEnabled(false);
    }

    public void odjava() {
        //provjera da li korisnik zaista zeli da se odjavi
        int reply = JOptionPane.showConfirmDialog(frame, "Jeste li sigurni da se zelite odjaviti?", "Confirm", JOptionPane.YES_NO_OPTION);
        if(reply == JOptionPane.NO_OPTION) return;
        //pozivanje forme za autentifikaciju
        SwingUtilities.invokeLater(AuthenticationForm::new);
        this.frame.dispose();
    }
}
