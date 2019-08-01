package com.app.artifacts;
import  com.app.artifacts.helms.*;
import  com.app.artifacts.amour.*;
import  com.app.artifacts.weapons.*;
import com.app.readWrite.*;

public  class Artifacts {
    private Weapon weapon;
    private Helm  helm;
    private Amour amour;

    public Artifacts() {
        makeWeapon();
        makeHelm();
        makeAmour();
    }

    public void makeWeapon() {
        System.out.println("Which weapon would you like?\n1. StormBreaker\n2. Bow And Arrow\n3. Gun");
        String name = MyReader.readConsole();

        switch (name) {
            case "1" : 
                weapon = new StormBreaker(1, 1);
                break ;
            case "2" : 
                weapon = new BowAndArrow(1, 1);
                break ;
            case "3" : 
                weapon = new Gun(1, 1);
                break ;
            default : 
                break ;
        }
    }

    public void makeHelm() {
        System.out.println("Which helm would you like?\n1. ChampionsHeadguard\n2. Kingdoms's Pride\n3. Blazeguard");
        String name = MyReader.readConsole();

        switch (name) {
            case "1" : 
                helm = new ChampionsHeadguard(1, 1);
                break ;
            case "2" :
                helm = new KingdomsPride(1, 1);
                break ;
            case "3" :
                helm = new Blazeguard(1, 1);
                break ;
            default : 
                break ;
        }
    }

    public void makeAmour() {
        System.out.println("Which amour would you like?\n1. Antique Blockade\n2. Guardian's Shield\n3. Light Shield");
        String name = MyReader.readConsole();

        switch (name) {
            case "1" : 
                amour = new AntiqueBlockade(1, 1);
                break ;
            case "2" :
                amour = new GuardiansShield(1, 1);
                break ;
            case "3" :
                amour = new LightShield(1, 1);
                break ;
            default : 
                break ;
        }
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
}