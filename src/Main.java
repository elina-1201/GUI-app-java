import Components.Korisnik;
import GUI.AuthenticationForm;
import GUI.MainClientForm;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        //pozivanje forme za autentifikaciju
        SwingUtilities.invokeLater(AuthenticationForm::new);

        //ostavljeno za testiranja
//        new MainClientForm("Main Form", new Korisnik("elina", "elina"));
    }
}