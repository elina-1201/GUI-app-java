import Components.Knjiga;
import GUI.AuthenticationForm;
import GUI.MainClientForm;
import JSON.JSONHandler;
import javax.swing.*;
public class Main {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AuthenticationForm();
            }
        });

        Knjiga book = new Knjiga("HP", "verry good book", 22.1, "21.2.2001", true, "Rowling", 300, "history");
    }
}