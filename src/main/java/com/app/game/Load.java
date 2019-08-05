package com.app.game;
import com.app.heroes.*;
import com.app.readWrite.*;

public class Load {

    public static Hero load() {
        Hero hero;
        System.out.println("Please select a hero id to load : ");
        String data[] = MyReader.readFile("Game.txt");
        for(int i = 0; data[i] != null; i++) {
            System.out.println((i + 1) + " : " + data[i]);
        }
        hero = new Hero("demo");
        return hero;
    }
}