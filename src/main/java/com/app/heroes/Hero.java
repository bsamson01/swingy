package  com.app.heroes;
import  com.app.artifacts.*;
import java.io.*;

public  class Hero {
    private String      name;
    private Stats       stats;
    private int         level;
    private int         experience;
    private Artifacts   artifacts;
    private Coordinates coordinates;
    private int         coins;

    public Hero(String nme) throws IOException {
        this.name = nme;
        this.experience = 0;
        this.level = 1;
        this.stats = new Stats(1, 1, 100);
        this.artifacts = new Artifacts();
        coordinates = new Coordinates(10, 10, 10);
    }

    public Hero(String nme, int exp, Stats stats, Artifacts artifacts, Coordinates coordinates, int level) throws IOException {
        this.name = nme;
        this.experience = exp;
        this.level = level;
        this.stats = stats;
        this.artifacts = artifacts;
        this.coordinates = coordinates;
    }

    public int getLevel() {
        return this.level;
    }

    public int getExp() {
        return this.experience;
    }

    public String getName() {
        return this.name;
    }

    public Stats getStats() {
        return this.stats;
    }

    public int getCoins() {
        return this.coins;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Artifacts getArtifacts() {
        return this.artifacts;
    }
}