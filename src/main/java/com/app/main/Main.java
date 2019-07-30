package com.app.main;
import com.app.game.*;
import com.app.heroes.*;
import com.app.readWrite.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Do you want to make a new hero? (y/n): ");
        if (MyReader.readConsole().equals("y")) {
            System.out.println("Give your hero a name : ");
            String name = MyReader.readConsole();
            if (name != null) {
                Hero myHero = new Hero(name);
                Game game = new Game(1);
                game.placeHero(myHero);
                game.play();
            }
        }
        else {
            System.out.println("Swingy now quiting");
        }
    }
}