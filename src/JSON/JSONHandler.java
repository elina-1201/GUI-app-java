package JSON;

import Components.Knjiga;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JSONHandler {
    private Knjiga book;

    public JSONHandler() {}
    public JSONHandler(Knjiga book) {
        this.book = book;
    }

    public ArrayList<HashMap<String, Object>> read() {
//    public void read() throws Exception {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("src/resources/books.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        ArrayList list = new ArrayList();
        JSONObject jsonObject;

        for(int i = 0; i < jsonArray.size(); i++) {
            jsonObject = (JSONObject) jsonArray.get(i);
            Map<String, Object> map = new HashMap<String, Object>();

            for (Object object : jsonObject.keySet()) {
                String key = (String) object;
                Object value = jsonObject.get(key);
                map.put(key, value);
            }
            list.add(map);
        }
        return list;
    }

    public void write() {
        JSONObject bookObject = new JSONObject();

//        for (Object object: this.book)
        String author = this.book.getAutor();
        int brStr = this.book.getBrojStranica();
        Double price = this.book.getCijena();
        String name = this.book.getIme();
        String desc = this.book.getOpis();
        String date = this.book.getDatumKreiranja();
        String category = this.book.getKategorija();


        bookObject.put("autor", author);
        bookObject.put("broj str", brStr);
        bookObject.put("cijena", price);
        bookObject.put("ime", name);
        bookObject.put("opis", desc);
        bookObject.put("datum", date);
        bookObject.put("kategorija", category);



//        JSONArray employeeList = new JSONArray();
//        employeeList.add(book1);
//        employeeList.add(book2);

        //Write JSON file
        try (FileWriter file = new FileWriter("src/resources/books.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(bookObject.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
