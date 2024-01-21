package GUI;

import Listeners.LoginListener;
import Listeners.RegisterListener;

import javax.swing.*;
import java.awt.*;

public class AuthenticationForm extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    // Konstruktor klase AuthenticationForm
    public AuthenticationForm() {
        super("Login Forma");

        // Postavljanje layout managera na GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Razmak između komponenata

        // Dodavanje komponenata na formu

        // Labela za korisničko ime
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Labela preko dvije kolone
        gbc.anchor = GridBagConstraints.CENTER; // Centriranje teksta
        add(new JLabel("Korisničko ime:"), gbc);

        // Text polje za unos korisničkog imena
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Širenje na dvije kolone
        gbc.fill = GridBagConstraints.HORIZONTAL;
        usernameField = new JTextField();
        add(usernameField, gbc);

        // Labela za šifru
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Labela preko dvije kolone
        add(new JLabel("Šifra:"), gbc);

        // Polje za unos šifre
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Širenje na dvije kolone
        gbc.fill = GridBagConstraints.HORIZONTAL;
        passwordField = new JPasswordField();
        add(passwordField, gbc);

        // Dugme za prijavu
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Širenje na dvije kolone
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton loginButton = new JButton("Prijavi se");
        add(loginButton, gbc);

        // Dugme za registraciju
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Širenje na dvije kolone
        JButton registerButton = new JButton("Registracija");
        add(registerButton, gbc);

        // Dodavanje akcije na dugme za prijavu
        loginButton.addActionListener(new LoginListener(usernameField, passwordField, this));

        // Dodavanje akcije na dugme za registraciju
        registerButton.addActionListener(new RegisterListener(usernameField, passwordField));

        // Postavljanje veličine prozora
        setSize(300, 250);

        // Postavljanje operacije zatvaranja prozora
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Postavljanje vidljivosti prozora
        setVisible(true);
    }
}

