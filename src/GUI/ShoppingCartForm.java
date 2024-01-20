package GUI;

import Components.Korisnik;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
public class ShoppingCartForm extends Form{
    JFrame frame = new JFrame();
    private JList list1;
    private JPanel panel1;
    private JButton kupovinaButton;
    private JButton ukloniButton;
    private JButton nazadButton;
    private Korisnik korisnik;

    public ShoppingCartForm(String title, Korisnik korisnik) throws HeadlessException {
        super(title);
        this.korisnik = korisnik;

        this.nazadButton.addActionListener(ActionEvent -> changeFrame());


        create(this.frame, panel1);
        this.panel1.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    public void changeFrame(){
        new MainClientForm("Main Form", this.korisnik );
        this.frame.dispose();
    }
}
