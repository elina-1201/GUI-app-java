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
        ArrayList result = search();

        DefaultListModel listModel1 = new DefaultListModel();

        for(Object jsonObject: result) {
            listModel1.addElement(jsonObject);
        }

        this.list.setModel(listModel1);
    }

    public ArrayList<HashMap<String, Object>> search() {
        ArrayList<HashMap<String, Object>> all = jsonHandler.read();
        ArrayList result = new ArrayList();

        for(int i = 0; i< all.size(); i++) {
            Map row = all.get(i);

            for(Object key: row.keySet()){
                String category = (String) this.comboBox.getSelectedItem();
                String word = this.searchField.getText();

                if(key.toString().equalsIgnoreCase(category) && row.get(key).toString().toLowerCase().startsWith(word.toLowerCase())){
                    result.add(row);
                }

            }

        }

        return result;
    }
}
