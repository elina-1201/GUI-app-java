package GUI;

import Components.Korisnik;
import Listeners.BookListSelectListener;
import Listeners.MainFormButtonListener;
import Listeners.SearchListener;
import JSON.JSONHandler;
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

    public MainClientForm(String title, Korisnik korisnik) {
        super(title);
        this.korisnik = korisnik;
        this.dodajUKorpuButton.addActionListener(ActionEvent -> addSelectedItemToCart());
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

        //listener koji prati OZNACENI proizvod
        this.list.addListSelectionListener(new BookListSelectListener(this.dodajUKorpuButton, this.list, this.korisnik, this.ukloniButton, this.izmijeniButton, this.spinner1));

        //postavljanje padding na panel
        this.panel1.setBorder(new EmptyBorder(10, 15, 10, 10));
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
        // Dobivanje selektovanog itema
        Object selectedItem = list.getSelectedValue();

        if (selectedItem instanceof HashMap) {
            HashMap<String, Object> selectedBook = (HashMap<String, Object>) selectedItem;

            // Novi json object
            JSONObject selectedObject = new JSONObject();

            // Sva svojstva u json object
            selectedObject.putAll(selectedBook);

            // Dodavanje u korpu u jsonu
            addObjectToCart(korisnik.getUsername(), selectedObject);

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
}
