package com.app.game;
import java.util.*;
import com.app.heroes.Hero;
import com.app.readWrite.MyReader;

public class Game {
    private int mapSize;
    private int[][] map;
    private Random rand = new Random();
    private Hero hero;
    private int inBounds = 1;

    public Game(int playerLevel) {
        mapSize = (playerLevel - 1) * 5 + 10 - (playerLevel % 2);
        map = new int[mapSize][mapSize];
        intitalizeMap();
        System.out.println();
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
        map[mapSize / 2][mapSize / 2] = 2;
    }

    public void remakeMap(int playerLevel) {
        mapSize = (playerLevel - 1) * 5 + 10 - (playerLevel % 2);
        map = new int[mapSize][mapSize];
        intitalizeMap();
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

    public void placeHero(Hero hero) {
        this.hero = hero;
        this.hero.getCoordinates().setLatitude(mapSize / 2);
        this.hero.getCoordinates().setLongitude(mapSize / 2);
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
                break ;
        }
        System.out.println("Thank you for playing");
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
        if ((rand.nextInt(10) + 1) % 3 == 0)
            System.out.println("Darkwolf killed you.");
        else
            System.out.println("You slayed Darkwolf");
    }

    private void run() {
        if ((rand.nextInt(2) + 1) % 2 == 0)
            System.out.println("Darkwolf hit you while trying to escape and you died");
        else
            System.out.println("You successfully evaded Darkwolf");
    }

    private void encounter() {
        System.out.println("You met darkwolf. Fight or run? ");
        String act = MyReader.readConsole();
        if (act.toLowerCase().compareToIgnoreCase("fight") == 0)
            fight();
        else
            run();
    }
}