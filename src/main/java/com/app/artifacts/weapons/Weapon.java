package  com.app.artifacts.weapons;

import java.util.*;

public class Weapon {
    private int level;
    private int stregnth;
    protected String type;

    Weapon(int points, int playerLevel, String typ) {
        Random rand = new Random();
        points = rand.nextInt(playerLevel * 10);
        this.stregnth =  (playerLevel - 1) * 10 + points;
        this.level = (this.stregnth / 10) + 1;
        this.type = typ;
    }

    public Weapon(String type, int power) {
        this.type = type;
        this.stregnth = power;
        this.level = (this.stregnth / 10) + 1;
    }

    public void upgrade(int points) {
        this.stregnth += points;
        this.level = (this.stregnth / 10) + 1;
    }

    public int destroy() {
        return (this.level);
    }

    public int getDamage() {
        return (this.stregnth);
    }

    public void printInfo() {
        System.out.println("|  Weapon : " + this.type );
        System.out.println("|  Damage : " + this.getDamage());
    }

    public String getType() {
        return (this.type);
    }

    public void improve(int coins) {
        this.stregnth += coins;
    }
}