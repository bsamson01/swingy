package com.app.main;
import com.app.game.*;
import com.app.heroes.*;
import com.app.readWrite.*;

public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero("Brandon");
        Game game = new Game();
        game.setHero(hero);
        while (true)
            game.menu();
    }
}