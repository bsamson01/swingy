package com.app.main;
import com.app.game.*;
import com.app.readWrite.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        while (true)
            game.menu();
    }
}