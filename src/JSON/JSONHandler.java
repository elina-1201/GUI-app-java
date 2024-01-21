package JSON;

import Components.Knjiga;
import Components.Korisnik;
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

    private static final String FILE_PATH = "src/resources/books.json";

    //funkcija za citanje podataka iz file
    public JSONArray readFile(){
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader(FILE_PATH);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return jsonArray;
    }

    //funkcija za pretvaranje podataka u povoljan tip
    public ArrayList<HashMap<String, Object>> read() {
        JSONArray jsonArray = readFile();

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

    public JSONObject findByIsbn(String isbn) {
        JSONArray jsonArray = readFile();
        JSONObject foundedObject = null;
        for(Object item: jsonArray) {
            JSONObject jsonObject = (JSONObject) item;
            if(jsonObject.get("isbn").equals(isbn)) {
                foundedObject = jsonObject;
            }
        }
        return foundedObject;
    }

    //funkcija za brisanje knjige pomocu isbn
    public void deleteByIsbn(String isbn) {
        JSONArray jsonArray = readFile();
        JSONObject foundedObject = findByIsbn(isbn);

        jsonArray.remove(foundedObject);
        writeFile(jsonArray);
    }

    public void addObjectToCart(JSONObject objectToAdd) {
        JSONArray jsonArray = readFile();
        jsonArray.add(objectToAdd);

        writeFile(jsonArray);
    }

    //funkcija za pisanje podataka u json file
    public void writeFile(JSONArray jsonArray){
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(jsonArray.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        JSONObject bookObject = new JSONObject();
        JSONArray jsonArray = readFile();

        //preuzimanje atributa iz klase knjiga
        String ime = this.book.getIme();
        String opis = this.book.getOpis();
        Double cijena = this.book.getCijena();
        Boolean dostupnost = this.book.isDostupnost();
        Korisnik vlasnik = this.book.getVlasnik();
        String isbn = this.book.getIsbn();
        String autor = this.book.getAutor();
        int brStr = this.book.getBrojStranica();
        String kategorija = this.book.getKategorija();
        int brPrimjeraka = this.book.getBrPrimjeraka();

        //dodavanje atributa u JSON objekat
        bookObject.put("ime", ime);
        bookObject.put("opis", opis);
        bookObject.put("cijena", cijena);
        bookObject.put("dostupnost", dostupnost);
        bookObject.put("vlasnik", vlasnik.getUsername());
        bookObject.put("isbn", isbn);
        bookObject.put("autor", autor);
        bookObject.put("broj stranica", brStr);
        bookObject.put("kategorija", kategorija);
        bookObject.put("broj primjeraka", brPrimjeraka);

        //dodavanje knjige u JSON
        jsonArray.add(bookObject);

        //Upisivanje u JSON file
        writeFile(jsonArray);
    }
}
