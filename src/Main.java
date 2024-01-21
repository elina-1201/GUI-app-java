import GUI.AuthenticationForm;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        //pozivanje forme za autentifikaciju pri pokretanju programa
        SwingUtilities.invokeLater(AuthenticationForm::new);
    }
}