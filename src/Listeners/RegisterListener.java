package Listeners;

import Components.Korisnik;
import GUI.AddOrUpdateForm;
import GUI.AuthenticationForm;
import GUI.MainClientForm;
import JSON.JSONAuthHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class RegisterListener implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    // Konstruktor prima referencu na JTextField i JPasswordField
    public RegisterListener(JTextField usernameField, JPasswordField passwordField){
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Dobavljanje unesenog korisničkog imena i šifre iz odgovarajućih polja
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Provjera da li su korisničko ime i šifra prazni, i ako jesu, prekidamo daljnju obradu
        if(username.trim().isEmpty() || password.trim().isEmpty()){
            return;
        }

        // Kreiranje instance JSONAuthHandler klase s objektom korisnika
        JSONAuthHandler database = new JSONAuthHandler(new Korisnik(username, password));

        // Dobavljanje liste korisnika iz JSONAuthHandler
        ArrayList<HashMap<String, Object>> users = database.getUsers();

        // Provjera postojanja korisnika sa istim korisničkim imenom
        boolean userExist = false;
        for (HashMap<String, Object> user : users){
            String dbUsername = ((String) user.get("username").toString());
            System.out.println(dbUsername);
            if(dbUsername.equals(username)){
                // Postavljanje da korisnik već postoji i prikazivanje odgovarajuće poruke
                userExist = true;
                JOptionPane.showMessageDialog(null, "Korisnik već postoji");
                break; // Prekidanje petlje jer smo pronašli podudaranje
            }
        }

        // Ako korisnik ne postoji, dodajemo ga u bazu podataka
        if(!userExist){
            database.registerUser();
            JOptionPane.showMessageDialog(null, "Uspješno ste registrovani, molimo prijavite se");
        }
    }
}

