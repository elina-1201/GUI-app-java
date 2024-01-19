package JSON;

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

public class JSONAuthHandler {
    private Korisnik user;

    // Konstruktori, prazan i s Korisnik objektom
    public JSONAuthHandler() {}

    // Konstruktor s Korisnik objektom koji će se koristiti pri registraciji
    public JSONAuthHandler(Korisnik user) {
        this.user = user;
    }

    // Metoda za dohvaćanje korisnika iz JSON datoteke
    public ArrayList<HashMap<String, Object>> getUsers() {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("src/resources/users.json");
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

        // Iteriranje kroz JSONArray i mapiranje na listu HashMap objekata
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

    // Metoda za registraciju novog korisnika
    public void registerUser() {
        JSONObject authObject = new JSONObject();

        String username = this.user.getUsername();
        String password = this.user.getPassword();

        JSONParser jsonParser = new JSONParser();
        FileReader reader = null;
        try {
            reader = new FileReader("src/resources/users.json");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        JSONArray jsonArray = null;
        try {
            jsonArray = (JSONArray) jsonParser.parse(reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Kreiranje JSON objekta sa korisničkim podacima
        authObject.put("username", username);
        authObject.put("password", password);

        // Dodavanje novog korisnika u JSONArray
        jsonArray.add(authObject);

        // Pisanje novog JSONArray u datoteku
        try (FileWriter file = new FileWriter("src/resources/users.json")) {
            file.write(jsonArray.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

