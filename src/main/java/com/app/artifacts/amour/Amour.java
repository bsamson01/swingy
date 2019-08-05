package com.app.artifacts.amour;

import java.util.*;

public class Amour {
    private int level;
    private int defence;
    protected String type;

    Amour(int points, int playerLevel, String typ) {
        Random rand = new Random();
        points = rand.nextInt(playerLevel * 10);
        this.defence =  (playerLevel - 1) * 10 + points;
        this.level = (this.defence / 10) + 1;
        this.type = typ;
    }

    public Amour(String type, int def) {
        this.type = type;
        this.defence = def;
        this.level = (this.defence / 10) + 1;
    }

    public void upgrade(int points) {
        this.defence += points;
        this.level = (this.defence / 30) + 1;
    }

    public int destroy() {
        return (this.level);
    }

    public int getDefence() {
        return (this.defence);
    }

    public void printName() {
        System.out.println("Amour    :-> " + this.type );
        System.out.println("Defence  :-> " + this.getDefence());
    }

    public String getType() {
        return (this.type);
    }

    public void improve(int coins) {
        this.defence += coins;
    }
}