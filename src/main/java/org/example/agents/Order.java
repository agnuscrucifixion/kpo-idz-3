package org.example.agents;
import java.util.Arrays;
public class Order {
    private int time;
    private final Food[] foodAndDrink;

    public Order(boolean isReady, Food[] foodAndDrink) {
        this.foodAndDrink = foodAndDrink;
        time = 0;
    }
    public int time() {
        for (Food food : foodAndDrink) {
            time += food.time();
        }
        return time;
    }
    @Override
    public String toString() {
        return String.format("Заказ - %s", Arrays.toString(foodAndDrink));
    }

}