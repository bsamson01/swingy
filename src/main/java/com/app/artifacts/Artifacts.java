package com.app.artifacts;
import  com.app.artifacts.helms.*;
import java.util.*;
import com.app.artifacts.amour.*;
import  com.app.artifacts.weapons.*;

public  class Artifacts {
    private Weapon weapon;
    private Helm  helm;
    private Amour amour;
    private static Random rand = new Random();

    public Artifacts(String tmp){}

    public void setWeapon(Weapon wpn) {
        this.weapon = wpn;
    }

    public void setHelm(Helm hlm) {
        this.helm = hlm;
    }

    public void setAmour(Amour amr) {
        this.amour = amr;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public Helm getHelm() {
        return this.helm;
    }

    public Amour getAmour() {
        return this.amour;
    }

    public static Weapon  generateWeapon(int playerLevel) {
        int val = rand.nextInt(2) + 1;
        if (val == 1)
            return new StormBreaker(1, playerLevel);
        else if (val == 2)
            return new BowAndArrow(1, playerLevel);
        else
            return new Gun(1, playerLevel);
    }

    public static Amour generateAmour(int playerLevel) {
        int val = rand.nextInt(2) + 1;
        if (val == 1)
            return new AntiqueBlockade(1, playerLevel);
        else if (val == 2)
            return new GuardiansShield(1, playerLevel);
        else
            return new LightShield(1, playerLevel);
    }

    public static Helm generateHelm(int playerLevel) {
        int val = rand.nextInt(2) + 1;
        if (val == 1)
            return new ChampionsHeadguard(1, playerLevel);
        else if (val == 2)
            return new KingdomsPride(1, playerLevel);
        else
            return new Blazeguard(1, playerLevel);
    }

    public static Helm makeHelm(int val) {
        if (val == 1)
            return new ChampionsHeadguard(1, 1);
        else if (val == 2)
            return new KingdomsPride(1, 1);
        else
            return new Blazeguard(1, 1);
    }

    public static Amour makeAmour(int val) {
        if (val == 1)
            return new AntiqueBlockade(1, 1);
        else if (val == 2)
            return new GuardiansShield(1, 1);
        else
            return new LightShield(1, 1);
    }

    public static Weapon  makeWeapon(int val) {
        if (val == 1)
            return new StormBreaker(1, 1);
        else if (val == 2)
            return new BowAndArrow(1, 1);
        else
            return new Gun(1, 1);
    }
}