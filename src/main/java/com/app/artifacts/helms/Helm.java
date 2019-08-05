package com.app.artifacts.helms;

import java.util.*;

public class Helm {
    private int level;
    private int hp;
    protected String type;

    Helm(int points, int playerLevel, String typ) {
        Random rand = new Random();
        points = rand.nextInt(playerLevel * 10);
        this.hp =  (playerLevel - 1) * 20 + points;
        this.level = (this.hp / 20) + 1;
        this.type = typ;
    }

    public Helm(String typ, int health) {
        this.type = typ;
        this.hp = health;
        this.level = (this.hp / 20) + 1;
    }

    public void upgrade(int points) {
        this.hp += points;
        this.level = (this.hp / 10) + 1;
    }

    public int destroy() {
        return (this.level);
    }

    public int getHp() {
        return (this.hp);
    }

    public void printName() {
        System.out.println("Helm :-> " + this.type );
        System.out.println("Hp   :-> " + this.getHp());
    }

    public String getType() {
        return (this.type);
    }

    public void improve(int coins) {
        this.hp += coins;
    }
}