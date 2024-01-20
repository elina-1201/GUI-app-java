package GUI;

import javax.swing.*;
import java.awt.*;

public abstract class Form extends JFrame {
    private String title;
    public Form(String title) throws HeadlessException {
        this.title = title;
    }

    public void create (JFrame frame, JPanel contentPane) {
        frame.setContentPane(contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1500, 1000);
        frame.setLocationRelativeTo(null);
    }
}
