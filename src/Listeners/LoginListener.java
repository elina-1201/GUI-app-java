package Listeners;

import Components.Korisnik;
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
    private JFrame frame;

    // Konstruktor prima referencu na JTextField i JPasswordField kao i sam frame da bi se mogao zatvoriti
    public LoginListener(JTextField usernameField, JPasswordField passwordField, JFrame frame) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // Dobavljanje unesenog korisničkog imena i šifre iz odgovarajućih polja
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        Korisnik korisnik = new Korisnik(username, password);
        // Kreiranje instance JSONAuthHandler klase s objektom korisnika
        JSONAuthHandler database = new JSONAuthHandler(korisnik);

        // Dobavljanje liste korisnika iz JSONAuthHandler
        ArrayList<HashMap<String, Object>> users = database.getUsers();

        //indikator koji ce pratiti da li se pronaslo podudaranje u podacima korisnika
        boolean logInindicator = false;

        // Iteriranje kroz listu korisnika
        for (HashMap<String, Object> user : users) {

            // Dobavljanje korisničkog imena i šifre iz baze podataka
            String dbUsername =  user.get("username").toString();
            String dbPassword =  user.get("password").toString();

            // Provjera podudaranja unesenih podataka s podacima iz baze
            if (dbUsername.equals(username) && dbPassword.equals(password)) {

                //prikazivanje glavne forme nakon uspjesnog login-a
                new MainClientForm("Main Form", korisnik);
                //uklanjanje trenutne login forme
                frame.dispose();

                logInindicator = true;

                break; // Prekidanje petlje jer smo pronašli podudaranje
            }
        }

        if(!logInindicator) {
            // Prikazivanje poruke o neuspješnoj prijavi
            JOptionPane.showMessageDialog(null, "Korisničko ime ili password nisu tačni");
        }
    }
}
