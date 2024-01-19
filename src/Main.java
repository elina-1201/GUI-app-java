import Components.Knjiga;
import GUI.MainClientForm;
import JSON.JSONHandler;

public class Main {
    public static void main(String[] args) throws Exception {

        new MainClientForm("Client");

        Knjiga book = new Knjiga("HP", "verry good book", 22.1, "21.2.2001", true, "Rowling", 300, "history");
    }
}