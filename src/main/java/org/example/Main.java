package org.example;
import org.example.agents.Client;
import org.example.agents.Cooker;
import org.example.agents.Supervisor;
import org.example.json.JsonReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int NUM_OF_THREADS = 6;
    private static final String CLIENTS_JSON_PATH = "jsons/client.json";
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);

        JsonReader jsonReader = new JsonReader(CLIENTS_JSON_PATH);
        Client[] clients = jsonReader.parse();

        Supervisor supervisor = new Supervisor();
        Cooker[] cookers = new Cooker[]{new Cooker(false, null), new Cooker(false, null), new Cooker(false, null)};
        supervisor.addCookers(cookers);

        for (Client client : clients) {
            executorService.submit(client);
        }

        executorService.shutdown();
    }
}