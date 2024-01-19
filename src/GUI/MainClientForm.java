package GUI;

import Listeners.ButtonListener;
import Listeners.SearchListener;
import JSON.JSONHandler;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainClientForm extends Form {
    JSONHandler jsonHandler = new JSONHandler();
    private JFrame frame = new JFrame();
    private JPanel panel1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton dodajNovuKnjiguButton;
    private JButton dodajUKorpuButton;
    private JSpinner spinner1;
    private JList list1;
    private JButton ukloniButton;
    private JButton izmijeniButton;

    public MainClientForm(String title) {
        super(title);
        this.textField1.addCaretListener(new SearchListener(this.comboBox1, this.textField1, this.list1));
        this.izmijeniButton.addActionListener(new ButtonListener(this.izmijeniButton, this.frame));
        this.dodajNovuKnjiguButton.addActionListener(new ButtonListener(this.dodajNovuKnjiguButton, this.frame));

        create(this.frame, this.panel1);
        fillList();
        fillCategory();
    }

    public void fillList() {
        ArrayList<HashMap<String, Object>> result = jsonHandler.read();

        DefaultListModel listModel1 = new DefaultListModel();

        for(Object jsonObject: result) {
            listModel1.addElement(jsonObject);
        }

        list1.setModel(listModel1);
    }

    public void fillCategory() {
        ArrayList result = jsonHandler.read();
        Map element = (Map) result.get(0);
        for(Object object: element.keySet()){
            this.comboBox1.addItem(object);
        }

//
//        for(int i = 0; i < result.size(); i++) {
//            Map element = (Map) result.get(i);
//
//            for (Object object : element.keySet()) {
//                String key = (String) object;
//                if(key.equals("kategorija")){
//                    Object value = element.get(key);
//                    this.comboBox1.addItem(value);
//                }
//            }
//        }
    }
}
