package com.app.heroes;

import javax.validation.constraints.*;

import com.app.artifacts.*;

public  class Hero {
    @NotNull(message = "Name cannot be null")
    private String name;

    private Stats       stats;
    private Stats       fullStats;
    private int         level;
    private int         experience;
    
    @NotNull(message = "Artifacts cannot be null")
    private Artifacts   artifacts;

    private Coordinates coordinates;
    private int         coins;
    private int         maxExp;
    private String      type;
    private Boolean     leveledUp = false;

    public Hero(String nme, String clss, int wpn, int hlm, int amr) {
        this.name = nme;
        this.experience = 0;
        this.level = 1;
        this.coins = 0;
        this.type = clss.toLowerCase();
        if (clss.compareToIgnoreCase("warlock") == 0)
            this.stats = new Stats(15, 15, 100);
        else if (clss.compareToIgnoreCase("gunman") == 0)
            this.stats = new Stats(20, 10, 100);
        else
            this.stats = new Stats(10, 20, 100);
        this.artifacts = new Artifacts("new hero");
        this.artifacts.setWeapon(Artifacts.makeWeapon(wpn));
        this.artifacts.setHelm(Artifacts.makeHelm(hlm));
        this.artifacts.setAmour(Artifacts.makeAmour(amr));
        this.coordinates = new Coordinates(0, 0);
        combineStats();
    }

    public Hero(String nme, int exp, int level, int coins, String type, Stats stats, Artifacts artifacts) {
        this.type = type;
        this.name = nme;
        this.experience = exp;
        this.level = level;
        this.stats = stats;
        this.artifacts = artifacts;
        this.coins = coins;
        this.coordinates = new Coordinates(0, 0);
        combineStats();
    }

    public String getType() {
        return this.type;
    }

    public void combineStats() {
        int att = stats.getAttack() + artifacts.getWeapon().getDamage();
        int def = stats.getDefense() + artifacts.getAmour().getDefence();
        int hp = stats.getHp() + artifacts.getHelm().getHp();
        this.fullStats = new Stats(att, def, hp);
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

    public Stats getHeroStats() {
        return this.stats;
    }

    public Stats getStats() {
        return this.fullStats;
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

    public void nextLevel() {
        this.level++;
        int att = (int)(stats.getAttack() * 1.3);
        int def = (int)(stats.getDefense() * 1.3);
        int health = (int)(stats.getHp() * 1.5);
        this.stats = new Stats(att, def, health);
        this.leveledUp = true;
    }

    public Boolean checkLeveledUp() {
        if (this.leveledUp) {
            this.leveledUp = false;
            return true;
        }
        else
            return false;
    }

    public void gainCoins(int cn) {
        this.coins += cn;
    }

    public void useCoins(int cn) {
        this.coins -= cn;
    }

    public void gainExp(int points) {
        this.experience += points;
        this.maxExp = this.level * 1000 + (this.level - 1) * (this.level - 1) * 450;
        if (this.experience >= this.maxExp) {
            this.nextLevel();
        }
    }
}