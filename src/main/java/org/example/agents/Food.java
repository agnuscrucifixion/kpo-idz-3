package org.example.agents;
public class Food {
    private final int time;

    public Food(int id, String name, int cost, int time) {
        this.time = time;
    }
    public int time() {
        return time;
    }
}