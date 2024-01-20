package GUI;

import Components.Knjiga;
import Components.Korisnik;
import JSON.JSONHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AddOrUpdateForm extends Form {
    private JFrame frame = new JFrame();
    private JPanel panel1;
    private JTextField textFieldIme;
    private JTextField textFieldAutor;
    private JTextField textFieldOpis;
    private JTextField textFieldCijena;
    private JTextField textFieldKategorija;
    private JButton spasiButton;
    private JTextField textFieldIsbn;
    private JComboBox comboBoxDostupnost;
    private JSpinner spinnerBrStr;
    private JButton nazadButton;
    private JSpinner spinnerPrimjerak;

    private Korisnik korisnik;
    private JList list;

    //konstruktor za IZMJENU
    public AddOrUpdateForm(String title, Korisnik korisnik, JList list) throws HeadlessException {
        super(title);
        this.korisnik = korisnik;
        this.list = list;

        //dodavanje listener-a na spasavanje
        this.spasiButton.addActionListener(actionEvent -> changeBook());
        //dodavanje listener funkcije na dugme nazad
        this.nazadButton.addActionListener(actionEvent -> changeFrame());

        this.panel1.setBorder(new EmptyBorder(50, 300, 50, 300));
        setFields();
        create(this.frame, this.panel1);
    }

    //konstruktor za DODAVANJE
    public AddOrUpdateForm(String title, Korisnik korisnik) throws HeadlessException {
        super(title);
        this.korisnik = korisnik;

        //dodavanje listener-a na spasavanje
        this.spasiButton.addActionListener(actionEvent -> createBook());
        //dodavanje listener funkcije na dugme nazad
        this.nazadButton.addActionListener(actionEvent -> changeFrame());

        this.panel1.setBorder(new EmptyBorder(50, 300, 50, 300));
        create(this.frame, this.panel1);

    }

    public void changeFrame(){
        new MainClientForm("Main Form", this.korisnik );
        this.frame.dispose();
    }

    public void setFields(){
        //preuzimanje podataka selektovanog item-a
        ArrayList<HashMap<String, Object>> selectedItem = (ArrayList<HashMap<String, Object>>) this.list.getSelectedValuesList();
        //odabir prvog i jedinog item-a u listi
        HashMap<String, Object> item = selectedItem.get(0);

        //pretvaranje values u integer, jer spinner prima samo taj tip
        Integer brStr = Integer.parseInt(item.get("broj stranica").toString());
        Integer brojPrimjeraka = Integer.parseInt(item.get("broj primjeraka").toString());

        //postavljanje vrijednosti polja na vrijednosti selektovane knjige
        this.textFieldIme.setText((String) item.get("ime"));
        this.textFieldAutor.setText((String) item.get("autor"));
        this.textFieldIsbn.setText((String) item.get("isbn"));
        this.textFieldIsbn.setEnabled(false);
        this.textFieldKategorija.setText((String) item.get("kategorija"));
        this.textFieldOpis.setText((String) item.get("opis"));
        this.textFieldCijena.setText(item.get("cijena").toString());
        this.spinnerPrimjerak.setValue(brojPrimjeraka);
        this.spinnerBrStr.setValue(brStr);
    }


    public void changeBook() {
        JSONHandler jsonHandler = new JSONHandler();
        //pronalazak knjige po isbn i njeno brisanje
        jsonHandler.deleteByIsbn(this.textFieldIsbn.getText());
        //ponovno upisivanje izmijenjene knjige u json file
        createBook();
    }

    public void createBook() {
        Knjiga book = new Knjiga();
        boolean indicatorBrojeva = false;
        boolean indicatorPolja = true;
        boolean dostupnost = comboBoxDostupnost.getSelectedItem().equals("DA");

        //preuzimanje vrijednosti polja i spasavanje u varijable
        String ime = textFieldIme.getText();
        String autor = textFieldAutor.getText();
        String isbn = textFieldIsbn.getText();
        String kategorija = textFieldKategorija.getText();
        String opis = textFieldOpis.getText();
        int brStr = 0;
        int brPrimjeraka = 0;
        Double cijena = 0.0;

        //pretvaranje tekstualne vrijednosti iz polja u numericku
        try{
            cijena = Double.parseDouble(textFieldCijena.getText());
            brStr = (int) spinnerBrStr.getValue();
            brPrimjeraka = (int) spinnerPrimjerak.getValue();
            indicatorBrojeva = true;
        }
        catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Pogresno unesen broj ");
        }

        //provjera da li su sva polja popunjena i postavljanje indikatora na false ako sva polja nisu popunjena
        if(ime.isEmpty() || autor.isEmpty() || isbn.isEmpty() || kategorija.isEmpty() || opis.isEmpty()) {
            indicatorPolja = false;
            JOptionPane.showMessageDialog(null, "Sva polja trebaju biti popunjena");
        }
        //ako su sva polja popunjena i brojevi ispravni vrijednosti se dodjeljuju kao atributi klase
        if(indicatorBrojeva && indicatorPolja) {
            book.setIme(ime);
            book.setAutor(autor);
            book.setIsbn(isbn);
            book.setKategorija(kategorija);
            book.setCijena(cijena);
            book.setOpis(opis);
            book.setBrojStranica(brStr);
            book.setBrPrimjeraka(brPrimjeraka);
            book.setDostupnost(dostupnost);
            book.setVlasnik(this.korisnik);

            //proslijedjivanje popunjenog objekta u JSON i zapisivinje podataka u file
            JSONHandler josnhandler = new JSONHandler(book);
            josnhandler.write();

            //poruka o uspjesnoj akciji
            JOptionPane.showMessageDialog(null, "Knjiga je uspjesno dodana");

            //promijena formi
            new MainClientForm("Main Form", this.korisnik);
            this.frame.dispose();
        }
    }
}
