package com.app.game;
import java.util.*;

public class Game {
    private int mapSize;
    private int[][] map;

    public Game(int playerLevel) {
        mapSize = (playerLevel - 1) * 5 + 10 - (playerLevel % 2);
        map = new int[mapSize][mapSize];
        intitalizeMap();
        System.out.println();
    }

    private void intitalizeMap() {
        Random rand = new Random();
        int n;
        for (int i = 0; i < mapSize ; i++) {
            for(int y = 0; y < mapSize; y++) {
                n = rand.nextInt(50) + 1;
                if (n % 6 == 0)
                    map[i][y] = 1;
                else
                    map[i][y] = 0;
            }
        }
        map[(mapSize / 2) + 1][(mapSize / 2) + 1] = 0;
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
                else
                    System.out.print(".");
            }
            System.out.print("\n");
        }
    }
}