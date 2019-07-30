package com.app.main;
// import com.app.heroes.*;
import java.io.IOException;
import com.app.game.Game;
import com.app.readWrite.MyReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Do you want to make a new hero? (y/n): ");
        if (MyReader.readConsole().equals("y")) {
            System.out.println("Give your hero a name : ");
            String name = MyReader.readConsole();
            if (name != null) {
                // Hero myHero = new Hero(name);
                // myHero.getArtifacts().getWeapon().printName();
                // myHero.getArtifacts().getHelm().printName();
                // myHero.getArtifacts().getAmour().printName();
                Game game = new Game(1);
                game.printMap();
            }
        }
        else {
            System.out.println("Swingy now quiting");
        }
    }
}