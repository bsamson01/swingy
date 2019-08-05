package com.app.enemies;
import java.util.Random;
import com.app.heroes.*;

public class Enemy {
    private Stats stats;
    private String type;
    private Random rand = new Random();

    public Enemy(String name, int playerLevel) {
        int attack = (rand.nextInt(10) + playerLevel * 10);
        int defence = (rand.nextInt(10) + playerLevel * 10);
        int hp = rand.nextInt(80 + (playerLevel * 10)) + 80;
        this.stats = new Stats(attack, defence, hp);
        this.type = name;
    }

    public Stats getStats() {
        return this.stats;
    }

    public String getName() {
        return this.type;
    }

    public void printStats() {
        System.out.println("Enemy name   : " + this.getName());
        System.out.println("Attack       : " + stats.getAttack());
        System.out.println("Defence      : " + stats.getDefense());
        System.out.println("Hp           : " + stats.getHp());
    }

}