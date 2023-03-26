package org.example.agents;
import org.example.json.JsonReader;
public class Cooker {
    private boolean isBusy;
    public Cooker(boolean isBusy, int[] order) {
        this.isBusy = isBusy;
    }
    public boolean isBusy() {
        return isBusy;
    }
    public void startCooking(Client client) throws InterruptedException {
        int[] ids = client.getOrder();
        JsonReader jsonReader = new JsonReader("jsons/menu.json");
        Food[] food = new Food[ids.length];
        jsonReader.parseFoodIntoArray(food, ids);
        Order order = new Order(false, food);
        System.out.printf("The order for %s has started cooking. The cooking time will be %s minutes\n",
                client.getName(), order.time());
        isBusy = true;
        Thread.sleep(order.time() * 100L);
        System.out.printf("Order for %s is ready!\n", client.getName());
        isBusy = false;
    }
}