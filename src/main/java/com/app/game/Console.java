package com.app.game;
import com.app.debug.*;
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

    public void newGame() {
        System.out.println("Are you sure? All progress will be lost. (y/n)");
        String cmd = MyReader.readConsole();
        if (cmd.compareToIgnoreCase("y") == 0)
            makeHero();
    }

    private void makeHero() {
        String name;
        int type;
        String cmd;
        int weapon = 0;
        int helm = 0;
        int amour = 0;
        while (true) {
            System.out.println("Enter hero name");
            cmd = MyReader.readConsole();
            if (cmd == null)
                System.out.println("Please enter a hero name");
            else
                break;
        }
        type = selectHero();
        weapon = selectWeapon();
        helm = selectHelm();
        amour = selectAmour();

    }

    private int selectHero() {
        String cmd;
        while (true) {
            System.out.println("Which hero type would you want?\n1.Gunman\n2.Seilie\n3.Warlock");
            cmd = MyReader.readConsole();
            if (cmd != null && Debug.isInteger(cmd)) {
                int val = Integer.parseInt(cmd);
                if (val < 1 || val > 3)
                    System.out.println("Invalid Hero selection");
                else
                    return val;
            }
            else
                System.out.println("Invalid Hero selection");
        }
    }

    private int selectWeapon() {
        String cmd;
        while (true) {
            System.out.println("Which weapon would you like?\n1. StormBreaker\n2. Bow And Arrow\n3. Gun");
            cmd = MyReader.readConsole();
            if (cmd != null && Debug.isInteger(cmd)) {
                int val = Integer.parseInt(cmd);
                if (val < 1 || val > 3)
                    System.out.println("Invalid Weapon selection");
                else
                    return val;
            }
            else
                System.out.println("Invalid Weapon selection");
        }
    }

    private int selectHelm() {
        String cmd;
        while (true) {
            System.out.println("Which helm would you like?\n1. ChampionsHeadguard\n2. Kingdoms's Pride\n3. Blazeguard");
            cmd = MyReader.readConsole();
            if (cmd != null && Debug.isInteger(cmd)) {
                int val = Integer.parseInt(cmd);
                if (val < 1 || val > 3)
                    System.out.println("Invalid Helm selection");
                else
                    return val;
            }
            else
                System.out.println("Invalid Helm selection");
        }
    }

    private int selectAmour() {
        String cmd;
        while (true) {
            System.out.println("Which weapon would you like?\n1. StormBreaker\n2. Bow And Arrow\n3. Gun");
            cmd = MyReader.readConsole();
            if (cmd != null && Debug.isInteger(cmd)) {
                int val = Integer.parseInt(cmd);
                if (val < 1 || val > 3)
                    System.out.println("Invalid Weapon selection");
                else
                    return val;
            }
            else
                System.out.println("Invalid Weapon selection");
        }
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
                    game.loadHero(val - 1);
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

    public void failedRun() {
        System.out.println(game.getEnemy().getName() + " hit you by " + game.getEnemy().getStats().getAttack() * 3 + " because you chicked out");
    }

    public void heroHit(int damage, Boolean critical) {
        if (critical)
            System.out.println(game.getEnemy().getName() + " hit you by " + damage * 5 + "(critical hit)");
        else
            System.out.println(game.getEnemy().getName() + " hit you by " + damage);
    }

    public void enemyHit(int damage, Boolean critical) {
        if (critical)
            System.out.println("You hit " + game.getEnemy().getName() + " by " + damage * 3 + "(critical hit)");
        else
            System.out.println("You hit " + game.getEnemy().getName() + " by " + damage);
    }

    public void evade() {
        System.out.println("You successfully evaded " + game.getEnemy().getName());
    }

    public void enemyKilled(){
        System.out.println("You slayed " + game.getEnemy().getName());
    }

    public void heroKilled() {
        System.out.println(game.getEnemy().getName() + " killed you");
    }

    public void metEnemy() {
        System.out.println("You met "+ game.getEnemy().getName() +". Fight or run? ");
    }

    public int getDirection() {
        while (true) {
            System.out.println("Please enter a direction you want to move (left, right, up, down), type (quit) to go back to the main menu");
            String cmd = MyReader.readConsole();
            if (cmd.toLowerCase().compareToIgnoreCase("left") == 0)
                return 4;
            else if (cmd.toLowerCase().compareToIgnoreCase("right") == 0)
                return 2;
            else if (cmd.toLowerCase().compareToIgnoreCase("up") == 0)
                return 1;
            else if (cmd.toLowerCase().compareToIgnoreCase("down") == 0)
                return 3;
            else if (cmd.toLowerCase().compareToIgnoreCase("quit") == 0)
                return 0;
            else
                System.out.println("Invalid input");
        }
    }

    public boolean endOfMap() {
        System.out.println("Congratulations you have reached the end of the map.");
        this.PrintStats();
        this.printMap();
        System.out.println("Do you want to load a new map? (y/n)");
        String cmd = MyReader.readConsole();
        if (cmd.compareToIgnoreCase("y") == 0)
            return true;
        else
            return false;
    }

    public void noHero() {
        System.out.println("You need to create a hero first or load an existing one");
    }

    public void printMap() {
        for (int i = 0; i < game.getMapSize(); i++) {
            for (int y = 0; y < game.getMapSize(); y++) {
                if (game.getMap()[i][y] == 1)
                    System.out.print("*");
                else if (game.getMap()[i][y] == 2)
                    System.out.print("H");
                else
                    System.out.print(".");
            }
            System.out.print("\n");
        }
    }

    public void printHeroInfo() {
        System.out.println("----------------Hero Info-------------------------");
        System.out.println("| Name   : " + game.getHero().getName());
        System.out.println("| Exp    : " + game.getHero().getExp());
        System.out.println("| Type   : " + game.getHero().getType());
        System.out.println("| Level  : " + game.getHero().getLevel());
        System.out.println("| Coins  : " + game.getHero().getCoins());
        System.out.println("|_________________Stats___________________________");
        System.out.println("|            Attack   : " + game.getHero().getStats().getAttack());
        System.out.println("|            Defense  : " + game.getHero().getStats().getDefense());
        System.out.println("|            Hp       : " + game.getHero().getStats().getHp());
        System.out.println("|_______________Artifacts_________________________");
        System.out.println("| Weapon : " + game.getHero().getArtifacts().getWeapon().getType() + " (damage = +"+game.getHero().getArtifacts().getWeapon().getDamage()+")");
        System.out.println("| Amour  : " + game.getHero().getArtifacts().getAmour().getType() + " (protection = +"+game.getHero().getArtifacts().getAmour().getDefence()+")");
        System.out.println("| Helm   : " + game.getHero().getArtifacts().getHelm().getType() + " (hitpoints = +"+game.getHero().getArtifacts().getHelm().getHp()+")");
        System.out.println("--------------------------------------------------");
    }
}