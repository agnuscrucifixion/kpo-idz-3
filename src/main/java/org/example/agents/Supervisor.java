package org.example.agents;
import java.util.ArrayList;

public class Supervisor {
    private static ArrayList<Client> clients;
    private static Cooker[] cookers;
    public Supervisor() {
        clients = new ArrayList<>();
    }
    public static void addClient(Client client) {
        clients.add(client);
    }
    public void addCookers(Cooker[] cookers) {
        Supervisor.cookers = cookers;
    }
    public static void run(Client client) {
        boolean flag = false;
        try {
            for (Cooker cooker : cookers) {
                if (!cooker.isBusy() && !flag) {
                    cooker.startCooking(client);
                    flag = true;
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Something went in wrong way");
        }
    }
}