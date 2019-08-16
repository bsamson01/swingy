package com.app.game;
import java.text.*;
import java.util.*;

import com.app.artifacts.*;
import com.app.artifacts.amour.*;
import com.app.artifacts.helms.*;
import com.app.artifacts.weapons.*;
import com.app.debug.*;
import com.app.enemies.*;
import com.app.heroes.*;
import com.app.main.*;
import com.app.readWrite.*;


public class Game {
    private int mapSize;
    private int[][] map;
    private Random rand = new Random();
    private Hero hero;
    private Enemy enemy;
    private int inBounds;
    private String[] enemies = {"Darkwolf", "Downworlder", "Feral"};
    private Artifacts tmpArtifacts;
    private Boolean alive;
    private String platform;
    private Swingy swingy = null;
    private Console console = null;
    private Boolean encount = false;
    private Boolean moved = true;

    public Boolean metEnemy() {
        return this.encount;
    }

    public Game(String platform) {
        if (platform.compareToIgnoreCase("gui") == 0) {
            swingy = new Swingy(this);
        }
        else
            this.console = new Console(this);
        menu();
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setHero(Hero player) {
        this.hero = player;
        int playerLevel = hero.getLevel();
        mapSize = (playerLevel - 1) * 5 + 10 - (playerLevel % 2);
        map = new int[mapSize][mapSize];
        intitalizeMap();
        this.alive = true;
        this.inBounds = 1;
    }

    public Hero getHero() {
        return this.hero;
    }

    public Weapon heroWeapon() {
        return this.hero.getArtifacts().getWeapon();
    }

    public Helm heroHelm() {
        return this.hero.getArtifacts().getHelm();
    }

    public Amour heroAmour() {
        return this.hero.getArtifacts().getAmour();
    }

    private void intitalizeMap() {
        for (int i = 0; i < mapSize ; i++) {
            for(int y = 0; y < mapSize; y++) {
                int n = rand.nextInt(50) + 1;
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

    public int[][] getMap() {
        return this.map;
    }

    public int getMapSize() {
        return this.mapSize;
    }

    public void guiGetDirection() { 
        System.out.println("Hello");
    }

    public void play() {

        if (this.hero != null) {
            this.alive = true;
            this.tmpArtifacts = new Artifacts("temp");
            remakeMap();
            while (alive) {
                this.encount = false;
                if (console != null) {
                    int val = console.getDirection();
                    if (val != 0)
                        move(val);
                    else
                        break ;
                }
                if (this.inBounds == 0) {
                    if (console != null) {
                        if (console.endOfMap())
                            remakeMap();
                        else
                            break ;
                    }
                }
            }
        }
        else
            if (console != null)
                this.console.noHero();
    }

    public void newHeroCreate(String name, int type, int weapon, int helm, int amour) {
        Hero newHero;
        if (type == 2)
            newHero = new Seilie(name, weapon, helm, amour);
        else if (type == 3)
            newHero = new Warlock(name, weapon, helm, amour);
        else
            newHero = new Gunman(name, weapon, helm, amour);
        setHero(newHero);
    }


    private void store() {
        if (hero != null && hero.getCoins() > 0) {
            while(true) {
                System.out.println("----------------Store---------------");
                System.out.println("|            "+hero.getCoins()+"coins");
                System.out.println("| 1. Upgrade Weapon                |");
                System.out.println("| 2. Upgrade Helm                  |");
                System.out.println("| 3. Upgrade Amour                 |");
                System.out.println("| 4. Main menu                     |");
                System.out.println("------------------------------------");
                String cmd = MyReader.readConsole();

                if (Debug.isInteger(cmd)) {
                    int val = Integer.parseInt(cmd);
                    if (val == 1) {
                        hero.getArtifacts().getWeapon().printInfo();
                        System.out.println("Enter amount of coins to upgrade your weapon ("+hero.getCoins()+"coins available)");
                        String tmp = MyReader.readConsole();
                        if (Debug.isInteger(tmp) && Integer.parseInt(tmp) <= hero.getCoins() && Integer.parseInt(tmp) > 0) {
                            hero.getArtifacts().getWeapon().improve(Integer.parseInt(tmp));
                            hero.useCoins(Integer.parseInt(tmp));
                            System.out.println("Weapon upgraded");
                            hero.getArtifacts().getWeapon().printInfo();
                        }
                        else
                            System.out.println("Invalid entry");
                    }
                    else if (val == 2) {
                        hero.getArtifacts().getHelm().printName();
                        System.out.println("Enter amount of coins to upgrade your helm ("+hero.getCoins()+"coins available)");
                        String tmp = MyReader.readConsole();
                        if (Debug.isInteger(tmp) && Integer.parseInt(tmp) <= hero.getCoins() && Integer.parseInt(tmp) > 0) {
                            hero.getArtifacts().getHelm().improve(Integer.parseInt(tmp));
                            hero.useCoins(Integer.parseInt(tmp));
                            System.out.println("Helm upgraded");
                            hero.getArtifacts().getHelm().printName();
                        }
                        else
                            System.out.println("Invalid entry");
                    }
                    else if (val == 3) {
                        hero.getArtifacts().getAmour().printName();
                        System.out.println("Enter amount of coins to upgrade your Amour ("+hero.getCoins()+"coins available)");
                        String tmp = MyReader.readConsole();
                        if (Debug.isInteger(tmp) && Integer.parseInt(tmp) <= hero.getCoins() && Integer.parseInt(tmp) > 0) {
                            hero.getArtifacts().getAmour().improve(Integer.parseInt(tmp));
                            hero.useCoins(Integer.parseInt(tmp));
                            System.out.println("Amour upgraded");
                            hero.getArtifacts().getAmour().printName();
                        }
                        else
                            System.out.println("Invalid entry");
                    }
                    else if (val == 4)
                        break ;
                }
                if (hero.getCoins() <= 0) {
                    break ;
                }
            }
        }
        else
            System.out.println("Sorry, You do not have any coins to access the store");
    }

    public void move(int direction) {
        this.encount = false;
        int lat = this.hero.getCoordinates().getLatitude();
        int lng = this.hero.getCoordinates().getLongitude();

        map[lng][lat] = 0;

        System.out.println("Hello");
        System.exit(0);
        if (direction == 4)
            hero.getCoordinates().moveLeft();
        else if (direction == 2)
            hero.getCoordinates().moveRight();
        else if (direction == 1)
            hero.getCoordinates().moveUp();
        else if (direction == 3)
            hero.getCoordinates().moveDown();
        lat = hero.getCoordinates().getLatitude();
        lng = hero.getCoordinates().getLongitude();
        if (lng >= this.mapSize || lng < 0 || lat >= this.mapSize || lat < 0)
            this.inBounds = 0;
        else if (this.map[lng][lat] == 1)
            encounter();
        else
            this.map[lng][lat] = 2;
    }

    private void fight() {
        int damage;
        enemy.printStats();

        while (hero.getStats().getHp() > 0 && enemy.getStats().getHp() > 0) {

            sleep(300);
            damage = hero.getStats().getAttack() - (int)(0.2 * enemy.getStats().getDefense());
            if (damage < (int)(0.2 * hero.getStats().getAttack()))
                damage = (int)(0.2 * hero.getStats().getAttack());
            if (rand.nextInt(8) == 4) {
                enemy.getStats().reduceHp(damage * 3);
                if (console != null)
                    this.console.enemyHit(damage, true);
            }
            else {
                enemy.getStats().reduceHp(damage);
                if (console != null)
                    this.console.enemyHit(damage, false);
            }
            sleep(300);

            if (enemy.getStats().getHp() > 0) {
                damage = enemy.getStats().getAttack() - (int)(0.1 * hero.getStats().getDefense());
                if (damage < (int)(0.4 *enemy.getStats().getAttack()));
                    damage = (int)(0.4 *enemy.getStats().getAttack());
                if (rand.nextInt(5) == 3) {
                    hero.getStats().reduceHp(damage * 5);
                    if (this.console != null)
                        this.console.heroHit(damage, true);
                }
                else {
                    hero.getStats().reduceHp(damage);
                    if (console != null)
                        this.console.heroHit(damage, false);
                }
            }
        }
        if (hero.getStats().getHp() < 0) {
            if (console != null)
                this.console.heroKilled();
            this.alive = false;
        }
        else {
            if (console != null)
                this.console.enemyKilled();
            hero.gainExp(300 / hero.getLevel());
            hero.gainCoins(1 * hero.getLevel());
            pickItem();
        }
    }

    public void menu() {
        if (console != null)
            while (true)
                this.console.menu();
        else
            swingy.mainMenu();
    }

    public void menuCommand(String cmd) {
        if (Debug.isInteger(cmd)) {
            int val;
            val = Integer.parseInt(cmd);
            if (val == 1)
                play();
            else if (val == 2)
                this.console.newGame();
            else if (val == 3) {
                save();
            }
            else if (val == 4)
                this.console.load();
            else if (val == 5 && hero != null) {
                if (console != null)
                    this.console.printHeroInfo();
            }
            else if (val == 6)
               store();
            else if (val == 7) {
                System.exit(0);
            }
        }
        else
            this.console.menu();
    }

    private void pickItem() {
        int val;
        String cmd;
        if ((rand.nextInt(4) + 1) % 2 == 0) {
            System.out.println(enemy.getName() + " dropped an item . Click 1 to inspect or any key to ignore");
            cmd = MyReader.readConsole();
            if (cmd.compareToIgnoreCase("1") == 0) {
                val = rand.nextInt(2) + 1;
                if (val == 1) {
                    tmpArtifacts.setWeapon(Artifacts.generateWeapon(hero.getLevel()));
                    tmpArtifacts.getWeapon().printInfo();
                }
                else if (val == 2) {
                    tmpArtifacts.setAmour(Artifacts.generateAmour(hero.getLevel()));
                    tmpArtifacts.getAmour().printName();
                }
                else {
                    tmpArtifacts.setHelm(Artifacts.generateHelm(hero.getLevel()));
                    tmpArtifacts.getHelm().printName();
                }

                System.out.println("Do you want to equip(e) or destroy(any key)?");
                cmd = MyReader.readConsole();
                if (cmd.compareToIgnoreCase("e") == 0) {
                    if (val == 1) {
                        hero.getArtifacts().setWeapon(this.tmpArtifacts.getWeapon());
                    }
                    else if (val == 2) {
                        hero.getArtifacts().setAmour(this.tmpArtifacts.getAmour());
                    }
                    else {
                        hero.getArtifacts().setHelm(this.tmpArtifacts.getHelm());
                    }
                }
            }
        }
    }

    public Enemy getEnemy() {
        return this.enemy;
    }

    private void run() {
        if ((rand.nextInt(2) + 1) % 2 == 0) {
                if (console != null)
                    this.console.evade();
                hero.gainExp(100 / hero.getLevel());
                move((rand.nextInt(3) + 1));
        }
        else {
            if (this.console != null)
                this.console.failedRun();
            hero.getStats().reduceHp(enemy.getStats().getAttack() * 3);
        }
        if (hero.getStats().getHp() < 0) {
            if (console != null)
                this.console.heroKilled();
            this.alive = false;
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
        if (this.console != null)
            this.console.metEnemy();
        else
            this.swingy.metEnemy();
        String act = MyReader.readConsole();
        this.encount = true;
        if (act.toLowerCase().compareToIgnoreCase("fight") == 0)
            fight();
        else
            run();
    }

    public Boolean save() {
        if (this.hero != null) {
            String result;
            String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());

            result = date + "@" + hero.getName() + "@" + hero.getExp() + "@" + hero.getLevel() + "@" + hero.getCoins() + "@"
                    + hero.getHeroStats().getAttack() + "@" + hero.getHeroStats().getDefense() + "@" + hero.getHeroStats().getHp() + "@"
                    + hero.getArtifacts().getWeapon().getType() + "@" + hero.getArtifacts().getWeapon().getDamage() + "@"
                    + hero.getArtifacts().getAmour().getType() + "@" + hero.getArtifacts().getAmour().getDefence() + "@"
                    + hero.getArtifacts().getHelm().getType() + "@" + hero.getArtifacts().getHelm().getHp() + "@"
                    + hero.getType();
            TestWriter.write(result);
            return true;
        }
        return false;
    }

    public void loadHero(int lineNumber) {
        String data[] = MyReader.readFileLine("Game.txt", lineNumber + 1).split("@", 20);

        if (data != null) {
            Artifacts loaddedArt = new Artifacts("loaded");
            loaddedArt.setWeapon(new Weapon(data[8], Integer.parseInt(data[9])));
            loaddedArt.setAmour(new Amour(data[10], Integer.parseInt(data[11])));
            loaddedArt.setHelm(new Helm(data[12], Integer.parseInt(data[13])));
            Stats sts = new Stats(Integer.parseInt(data[5]), Integer.parseInt(data[6]), Integer.parseInt(data[7]));
            this.hero = new Hero(data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]), data[14], sts, loaddedArt);
        }
    }
}