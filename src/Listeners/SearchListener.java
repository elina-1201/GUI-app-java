package Listeners;

import JSON.JSONHandler;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchListener implements CaretListener {
    private JSONHandler jsonHandler = new JSONHandler();
    private JComboBox comboBox;
    private JTextField searchField;
    private JList list;
    public SearchListener(JComboBox comboBox, JTextField searchField, JList list){
        this.searchField = searchField;
        this.comboBox = comboBox;
        this.list = list;


    }
    @Override
    public void caretUpdate(CaretEvent caretEvent) {
        //preuzimanje itema-a koji su pronadjeni po parametrima
        ArrayList result = search();

        DefaultListModel listModel1 = new DefaultListModel();

        //dodavanje pronadjenih item-a u model liste
        for(Object jsonObject: result) {
            listModel1.addElement(jsonObject);
        }

        //dodavanje modela u listu
        this.list.setModel(listModel1);
    }

    public ArrayList<HashMap<String, Object>> search() {
        //citanje svih podataka iz file-a
        ArrayList<HashMap<String, Object>> all = jsonHandler.read();
        ArrayList result = new ArrayList();

        for(int i = 0; i< all.size(); i++) {
            Map row = all.get(i);

            for(Object key: row.keySet()){
                //dohvatanje kategorije i upisane rijeci iz forme
                String category = (String) this.comboBox.getSelectedItem();
                String word = this.searchField.getText();

                //provjeravanje da li se pronadjeni rezultati podudaraju sa kriterijima
                if(key.toString().equalsIgnoreCase(category) && row.get(key).toString().toLowerCase().contains(word.toLowerCase())){
                    result.add(row);
                }

            }
        }
        //vraca se lista item-a koji se podudaraju sa zadanim uslovima
        return result;
    }
}
