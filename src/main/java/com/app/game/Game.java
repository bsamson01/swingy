package com.app.game;
import java.text.*;
import java.util.*;
import com.app.enemies.*;
import com.app.heroes.*;
import com.app.readWrite.*;


public class Game {
    private int mapSize;
    private int[][] map;
    private Random rand = new Random();
    private Hero hero;
    private Enemy enemy;
    private int inBounds = 1;
    private String[] enemies = {"Darkwolf", "Downworlder", "Feral"};
    private String[] weapons = {"Darkwolf", "Downworlder", "Feral"};
    private String[] amour = {"Darkwolf", "Downworlder", "Feral"};
    private String[] helm = {"Darkwolf", "Downworlder", "Feral"};

    public Game(Hero player) {
        this.hero = player;
        int playerLevel = hero.getLevel();
        mapSize = (playerLevel - 1) * 5 + 10 - (playerLevel % 2);
        map = new int[mapSize][mapSize];
        intitalizeMap();
    }

    private void intitalizeMap() {
        int n;
        for (int i = 0; i < mapSize ; i++) {
            for(int y = 0; y < mapSize; y++) {
                n = rand.nextInt(50) + 1;
                if (n % 5 == 0)
                    map[i][y] = 1;
                else
                    map[i][y] = 0;
            }
        }
        map[(mapSize + 1) / 2][(mapSize + 1) / 2] = 2;
        hero.getCoordinates().setLatitude((mapSize + 1) / 2);
        hero.getCoordinates().setLongitude((mapSize + 1) / 2);
    }

    public void remakeMap() {
        int playerLevel = hero.getLevel();
        mapSize = (playerLevel - 1) * 5 + 10 - (playerLevel % 2);
        map = new int[mapSize][mapSize];
        intitalizeMap();
        inBounds = 1;
        hero.combineStats();
    }

    public void printMap() {
        for (int i = 0; i < mapSize; i++) {
            for (int y = 0; y < mapSize; y++) {
                if (map[i][y] == 1)
                    System.out.print("*");
                else if (map[i][y] == 2)
                    System.out.print("H");
                else
                    System.out.print(".");
            }
            System.out.print("\n");
        }
    }

    public void play() {
        String cmd;
        while (true) {
            System.out.println("Please enter a direction you want to move");
            cmd = MyReader.readConsole();
            if (cmd.toLowerCase().compareToIgnoreCase("left") == 0 || cmd.toLowerCase().compareToIgnoreCase("right") == 0 || cmd.toLowerCase().compareToIgnoreCase("up") == 0 || cmd.toLowerCase().compareToIgnoreCase("down") == 0)
                move(cmd);
            else if (cmd.toLowerCase().compareToIgnoreCase("quit") == 0)
                break ;
            else
                System.out.println("Invalid input");
            if (inBounds == 0)
            {
                System.out.println("Congratulations you have reached the end of the map. Do you want to load the new map? (y/n)");
                cmd = MyReader.readConsole();
                if (cmd.toLowerCase().compareToIgnoreCase("y") == 0 || cmd.toLowerCase().compareToIgnoreCase("yes") == 0) {
                    remakeMap();
                    PrintStats();
                }
                else
                    break ;
            }
        }
        // save();
        // load(1);
    }

    private void PrintStats() {
        System.out.println("Hero name  : " + hero.getName());
        System.out.println("Hero Level : " + hero.getLevel());
        System.out.println("Hero Exp   : " + hero.getExp());
    } 

    private void move(String direction) {
        int lat = hero.getCoordinates().getLatitude();
        int lng = hero.getCoordinates().getLongitude();

        map[lng][lat] = 0;

        if (direction.compareToIgnoreCase("left") == 0)
            hero.getCoordinates().moveLeft();
        else if (direction.compareToIgnoreCase("right") == 0)
            hero.getCoordinates().moveRight();
        else if (direction.compareToIgnoreCase("up") == 0)
            hero.getCoordinates().moveUp();
        else if (direction.compareToIgnoreCase("down") == 0)
            hero.getCoordinates().moveDown();
        lat = hero.getCoordinates().getLatitude();
        lng = hero.getCoordinates().getLongitude();
        if (lng >= mapSize || lng < 0 || lat >= mapSize || lat < 0)
            inBounds = 0;
        else if (map[lng][lat] == 1)
            encounter();
        else
            map[lng][lat] = 2;
    }

    private void fight() {
        int damage;
        while (hero.getStats().getHp() > 0 && enemy.getStats().getHp() > 0) {

            sleep(300);
            damage = hero.getStats().getAttack() - enemy.getStats().getDefense();
            enemy.getStats().reduceHp(damage);
            System.out.println("You hit " + enemy.getName() + " by " + damage);
            sleep(300);

            if (enemy.getStats().getHp() > 0) {

                damage = enemy.getStats().getAttack() - hero.getStats().getDefense();
                hero.getStats().reduceHp(damage);
                System.out.println(enemy.getName() + " hit you by " + damage);
            }
        }
        if (hero.getStats().getHp() < 0) {
            System.out.println(enemy.getName() + " killed you");
        }
        else {
            System.out.println("You slayed " + enemy.getName());
            hero.gainExp(30);
            hero.gainCoins(1);
            if ((rand.nextInt(4) + 1) % 2 == 0) {
                System.out.println(enemy.getName() + " dropped an item. Click 1 to inspect");

            }
        }
    }

    private void pickItem() {

    }

    private void run() {
        if ((rand.nextInt(2) + 1) % 2 == 0) {
                System.out.println("You successfully evaded " + enemy.getName());
                hero.gainExp(20);
        }
        else {
            System.out.println(enemy.getName() + "hit by " + enemy.getStats().getAttack() * 3 + " because you chicked out");
            hero.getStats().reduceHp(enemy.getStats().getAttack() * 3);
        }
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void encounter() {
        int type = rand.nextInt(3 - 1) + 1;
        enemy = new Enemy(enemies[type], hero.getLevel());

        System.out.println("You met "+ enemy.getName() +". Fight or run? ");
        enemy.printStats();
        String act = MyReader.readConsole();
        if (act.toLowerCase().compareToIgnoreCase("fight") == 0)
            fight();
        else
            run();
    }

    private void save() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        String result;
        String date = dateFormat.format(cal.getTime());
        result = date + " | " + hero.getName() + " | " + hero.getExp() + " | " + hero.getLevel() + " | " + hero.getCoins() + " | "
                + hero.getStats().getAttack() + " | " + hero.getStats().getDefense() + " | " + hero.getStats().getHp() + " | "
                + hero.getArtifacts().getWeapon().getType() + " | " + hero.getArtifacts().getWeapon().getDamage() + " | "
                + hero.getArtifacts().getAmour().getType() + " | " + hero.getArtifacts().getAmour().getDefence() + " | "
                + hero.getArtifacts().getHelm().getType() + " | " + hero.getArtifacts().getHelm().getHp();
        TestWriter.write(result);
    }

    private void load(int index) {
        String data[] = MyReader.readFile("Game.txt");
        String[] info;
        for(int i = 0; data[i] != null; i++) {
            info = data[i].split("|",  50);
            System.out.println(info[1]);
        }
    }
}