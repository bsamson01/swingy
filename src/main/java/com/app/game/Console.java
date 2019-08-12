package com.app.game;

import com.app.debug.Debug;
import com.app.readWrite.*;

public class Console {
    private Game game;

    Console(Game gme){
        this.game = gme;
    }

    public void PrintStats() {
        System.out.println("--------------------------------------------");
        System.out.println("|  Hero name  : " + game.getHero().getName());
        System.out.println("|  Hero Level : " + game.getHero().getLevel());
        System.out.println("|  Hero Exp   : " + game.getHero().getExp());
        System.out.println("|  Hero Hp    : " + game.getHero().getStats().getHp());
        System.out.println("|  Hero coins : " + game.getHero().getCoins());
        System.out.println("--------------------------------------------");
    }

    public void menu() {
        System.out.println("------------Main menu------------");
        System.out.println("|       Select an option        |");
        System.out.println("| 1. Continue                   |");
        System.out.println("| 2. New Game                   |");
        System.out.println("| 3. Save Progress              |");
        System.out.println("| 4. Load Another Hero          |");
        System.out.println("| 5. Hero Info                  |");
        System.out.println("| 6. Store                      |");
        System.out.println("| 7. Exit                       |");
        System.out.println("-------------Swingy--------------");
        String cmd = MyReader.readConsole();
        if (Debug.isInteger(cmd)) {
            int val = Integer.parseInt(cmd);
            if (val > 0 && val < 8)
                game.menuCommand(cmd);
            else {
                System.out.println("Invalid command");
                menu();
            }
        }
        else {
            System.out.println("Invalid command");
            menu();
        }
        
    }

    public void load() {
        String[] names = Load.loadNames();
        int i = 0;
        String[] info;

        if (names != null) {
            System.out.println("Please select a hero id to load : ");

            for(i = 0; i < names.length; i++) {
                info = names[i].split("@",  10);
                System.out.println((i + 1) + ". " + info[1] + " on " + info[0]);
            }

            System.out.println((i + 1) + ". Back to Main Menu");
            String cmd = MyReader.readConsole();

            if (Debug.isInteger(cmd)) {
                int val = Integer.parseInt(cmd);
                if (val > 0 && val < names.length)
                    game.loadHero(val);
                else {
                    System.out.println("Invalid command");
                    load();
                }
            }
            else {
                System.out.println("Input is not an integer. Please enter an Integer");
                load();
            }
        }
    }
}