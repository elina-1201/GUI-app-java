package JSON;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONCartHandler {
    private static final String FILE_PATH = "src/resources/users.json";

    public static void addObjectToCart(String username, JSONObject objectToAdd, int kolicina) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(FILE_PATH)) {

            JSONArray userList = (JSONArray) jsonParser.parse(reader);


            for (Object user : userList) {
                JSONObject currentUser = (JSONObject) user;
                if (currentUser.get("username").equals(username)) {

                    JSONArray cart = (JSONArray) currentUser.get("cart");
                    cart.add(objectToAdd);

                    //zapisati odabranu kolicinu artikla
                    objectToAdd.put("kolicina", kolicina);


                    try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
                        fileWriter.write(userList.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Object added to cart for user: " + username);
                    return;
                }
            }

            System.out.println("User not found with username: " + username);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static JSONArray getCartObjects(String username) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(FILE_PATH)) {

            JSONArray userList = (JSONArray) jsonParser.parse(reader);


            for (Object user : userList) {
                JSONObject currentUser = (JSONObject) user;
                if (currentUser.get("username").equals(username)) {

                    return (JSONArray) currentUser.get("cart");
                }
            }

            System.out.println("User not found with username: " + username);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return new JSONArray();
    }

    public static void removeObjectFromCart(String username, String itemId) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(FILE_PATH)) {

            JSONArray userList = (JSONArray) jsonParser.parse(reader);


            for (Object user : userList) {
                JSONObject currentUser = (JSONObject) user;
                if (currentUser.get("username").equals(username)) {

                    JSONArray cart = (JSONArray) currentUser.get("cart");
                    if (removeObjectById(cart, itemId)) {

                        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
                            fileWriter.write(userList.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Object not found in cart for user: " + username + " with itemId: " + itemId);
                    }
                    return;
                }
            }

            System.out.println("User not found with username: " + username);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearCart(String username) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(FILE_PATH)) {

            JSONArray userList = (JSONArray) jsonParser.parse(reader);


            for (Object user : userList) {
                JSONObject currentUser = (JSONObject) user;
                if (currentUser.get("username").equals(username)) {

                    JSONArray cart = (JSONArray) currentUser.get("cart");
                    cart.clear();


                    try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
                        fileWriter.write(userList.toString());
                        System.out.println("Cart cleared for user: " + username);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return;
                }
            }

            System.out.println("User not found with username: " + username);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean removeObjectById(JSONArray cart, String itemId) {
        for (Object item : cart) {
            JSONObject cartObject = (JSONObject) item;
            if (cartObject.containsKey("isbn") && cartObject.get("isbn").equals(itemId)) {
                cart.remove(item);
                return true;
            }
        }
        return false;
    }
}
