package com.app.artifacts;
import  com.app.artifacts.helms.*;
import java.util.*;
import com.app.artifacts.amour.*;
import  com.app.artifacts.weapons.*;
import com.app.debug.Debug;
import com.app.readWrite.*;

public  class Artifacts {
    private Weapon weapon;
    private Helm  helm;
    private Amour amour;
    private static Random rand = new Random();
    private int randomVal;

    public Artifacts() {
        weapon = makeWeapon();
        helm = makeHelm();
        amour = makeAmour();
    }

    public Artifacts(String tmp){}

    public Weapon makeWeapon() {
        while(true) {
            randomVal = rand.nextInt(10);
            System.out.println("Which weapon would you like?\n1. StormBreaker\n2. Bow And Arrow\n3. Gun");
            String cmd = MyReader.readConsole();
            if (Debug.isInteger(cmd)) {
                int val = Integer.parseInt(cmd);
                if (val == 1)
                    return new StormBreaker(randomVal, 1);
                else if (val == 2)
                    return new BowAndArrow(randomVal, 1);
                else if (val == 3)
                    return new Gun(randomVal, 1);
                else
                    System.out.println("Invalid weapon selection");
            }
            else
                System.out.println("Invalid weapon selection");
        }
    }

    public Helm makeHelm() {
        while(true) {
            randomVal = rand.nextInt(10);
            System.out.println("Which helm would you like?\n1. ChampionsHeadguard\n2. Kingdoms's Pride\n3. Blazeguard");
            String cmd = MyReader.readConsole();
            if (Debug.isInteger(cmd)) {
                int val = Integer.parseInt(cmd);
                if (val == 1)
                    return new ChampionsHeadguard(randomVal, 1);
                else if (val == 2)
                    return new KingdomsPride(randomVal, 1);
                else if (val == 3)
                    return new Blazeguard(randomVal, 1);
                else
                    System.out.println("Invalid helm selection");
            }
            else
                System.out.println("Invalid helm selection");
        }
    }

    public Amour makeAmour() {
        while (true) {
            randomVal = rand.nextInt(10);
            System.out.println("Which amour would you like?\n1. Antique Blockade\n2. Guardian's Shield\n3. Light Shield");
            String cmd = MyReader.readConsole();
            if (Debug.isInteger(cmd)) {
                int val = Integer.parseInt(cmd);
                if (val == 1)
                    return new AntiqueBlockade(randomVal, 1);
                else if (val == 2)
                    return new GuardiansShield(randomVal, 1);
                else if (val == 3)
                    return new LightShield(randomVal, 1);
                else
                    System.out.println("Invalid amour selection");
            }
            else
                System.out.println("Invalid amour selection");
        }
    }

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
        Weapon wp;
        int val = rand.nextInt(2) + 1;
        if (val == 1)
            wp = new StormBreaker(1, playerLevel);
        else if (val == 2)
            wp = new BowAndArrow(1, playerLevel);
        else
            wp = new Gun(1, playerLevel);
        return (wp);
    }

    public static Amour generateAmour(int playerLevel) {
        Amour amr;
        int val = rand.nextInt(2) + 1;
        if (val == 1)
            amr = new AntiqueBlockade(1, playerLevel);
        else if (val == 2)
            amr = new GuardiansShield(1, playerLevel);
        else
            amr = new LightShield(1, playerLevel);
        return amr;
    }

    public static Helm generateHelm(int playerLevel) {
        Helm hlm;
        int val = rand.nextInt(2) + 1;
        if (val == 1)
            hlm = new ChampionsHeadguard(1, playerLevel);
        else if (val == 2)
            hlm = new KingdomsPride(1, playerLevel);
        else
            hlm = new Blazeguard(1, playerLevel);
        return hlm;
    } 
}