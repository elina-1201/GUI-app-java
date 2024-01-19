package GUI;

import Listeners.ButtonListener;

import javax.swing.*;
import java.awt.*;

public class AddOrUpdateForm extends Form {
    private JFrame frame = new JFrame();
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton button1;

    public AddOrUpdateForm(String title) throws HeadlessException {
        super(title);
        button1.addActionListener(new ButtonListener(this.button1, this.frame));

        create(this.frame, this.panel1);
    }
}
