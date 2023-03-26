package org.example.json;

import org.example.agents.Client;
import org.example.agents.Food;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    private final String path;

    public JsonReader(String path) {
        this.path = path;
    }

    public Client[] parse() {
        Client[] clients = new Client[6];
        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader(path)) {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);

            int counter = 0;
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;

                String name = (String) jsonObject.get("name");
                int money = ((Long) jsonObject.get("money")).intValue();

                JSONArray orderJsonArray = (JSONArray) jsonObject.get("order");
                int[] order = new int[orderJsonArray.size()];
                for (int i = 0; i < orderJsonArray.size(); i++) {
                    order[i] = ((Long) orderJsonArray.get(i)).intValue();
                }

                Client client = new Client(name, money, order);
                clients[counter++] = client;
            }
        } catch (IOException | ParseException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }

        return clients;
    }

    public void parseFoodIntoArray(Food[] food, int[] ids) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader fileReader = new FileReader(path)) {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(fileReader);

            int counter = 0;
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;

                int id = ((Long) jsonObject.get("dish_id")).intValue();
                for (int i : ids) {
                    if (i == id) {
                        food[counter++] = new Food(id, (String) jsonObject.get("dish_name"),
                                ((Long) jsonObject.get("dish_cost")).intValue(),
                                ((Long) jsonObject.get("prep_time")).intValue());
                    }
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Error reading JSON file: " + e.getMessage(), e);
        }
    }
}
