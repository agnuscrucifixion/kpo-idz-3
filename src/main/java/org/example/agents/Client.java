package org.example.agents;
import java.util.Arrays;
public class Client implements Runnable {

    private final String name;
    private final int money;
    private final int[] order;

    public Client(String name, int money, int[] order) {
        this.name = name;
        this.money = money;
        this.order = order;
    }
    public int[] getOrder() {
        return order;
    }
    public String getName() {
        return name;
    }
    public void run() {
        Supervisor.addClient(this);
        Supervisor.run(this);
    }
    @Override
    public String toString() {
        return String.format("Name - %s, Money - %d, Order - %s", name, money, Arrays.toString(order));
    }


}