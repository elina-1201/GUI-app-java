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

public class LoginListener implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    // Konstruktor prima referencu na JTextField i JPasswordField
    public LoginListener(JTextField usernameField, JPasswordField passwordField) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Dobavljanje unesenog korisničkog imena i šifre iz odgovarajućih polja
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Kreiranje instance JSONAuthHandler klase s objektom korisnika
        JSONAuthHandler database = new JSONAuthHandler(new Korisnik(username, password));

        // Dobavljanje liste korisnika iz JSONAuthHandler
        ArrayList<HashMap<String, Object>> users = database.getUsers();

        // Iteriranje kroz listu korisnika
        for (HashMap<String, Object> user : users) {
            // Dobavljanje korisničkog imena i šifre iz baze podataka
            String dbUsername = ((String) user.get("username").toString());
            String dbPassword = ((String) user.get("password")).toString();

            // Provjera podudaranja unesenih podataka s podacima iz baze
            if (dbUsername.equals(username) && dbPassword.equals(password)) {
                // Prikazivanje poruke o uspješnoj prijavi
                JOptionPane.showMessageDialog(null, "Uspješna prijava");
                break; // Prekidanje petlje jer smo pronašli podudaranje
            } else {
                // Prikazivanje poruke o neuspješnoj prijavi
                JOptionPane.showMessageDialog(null, "Korisničko ime ili password nisu tačni");
                break; // Prekidanje petlje jer smo obavili jednu provjeru
            }
        }
    }
}
