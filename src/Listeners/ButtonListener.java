package Listeners;

import GUI.MainClientForm;
import GUI.AddOrUpdateForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener {
    private JButton button;
    private JFrame frame;

    public ButtonListener(JButton button, JFrame frame) {
        this.button = button;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String buttonText = this.button.getText();
        if(buttonText.equals("Izmijeni") || buttonText.equals("Dodaj novu knjigu")){
            new AddOrUpdateForm("Shop");
            this.frame.dispose();
        }
        if(buttonText.equals("Spasi")) {
            new MainClientForm("title");
            this.frame.dispose();
        }
    }
}
