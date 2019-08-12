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

    public Game(String platform) {
        if (platform.compareToIgnoreCase("gui") == 0) {
            swingy = new Swingy(this);
        }
        else
            console = new Console(this);
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
        if (this.hero != null) {
            this.alive = true;
            this.tmpArtifacts = new Artifacts("temp");
            String cmd;
            remakeMap();
            while (alive) {
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
                    System.out.println("Congratulations you have reached the end of the map.");
                    console.PrintStats();
                    System.out.println("Do you want to load a new map? (y/n)");
                    cmd = MyReader.readConsole();
                    if (cmd.toLowerCase().compareToIgnoreCase("y") == 0 || cmd.toLowerCase().compareToIgnoreCase("yes") == 0)
                        remakeMap();
                    else
                        break ;
                }
            }
        }
        else
            System.out.println("You need to create a hero first or load an existing one");
    }

    private void newGame() {
        System.out.println("Are you sure? All progress will be lost. (y/n)");
        String cmd = MyReader.readConsole();
        if (cmd.compareToIgnoreCase("y") == 0)
            makeHero();
    }

    private void makeHero() {
        Hero newHero;
        System.out.println("Enter hero name");
        String name = MyReader.readConsole();
        while (true) {
            System.out.println("Which hero type would you want?\n1.Gunman\n2.Seilie\n3.Warlock");
            String cmd = MyReader.readConsole();
            if (Debug.isInteger(cmd)) {
                if (Integer.parseInt(cmd) == 1) {
                    newHero = new Gunman(name);
                    break ;
                }
                else if (Integer.parseInt(cmd) == 2) {
                    newHero = new Seilie(name);
                    break ;
                }
                else if (Integer.parseInt(cmd) == 3) {
                    newHero = new Warlock(name);
                    break ;
                }
                else
                    System.out.println("Error: Invalid input.");
            }
            else
                System.out.println("Error: Input not an Integer");
        }
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
        enemy.printStats();

        while (hero.getStats().getHp() > 0 && enemy.getStats().getHp() > 0) {

            sleep(300);
            damage = hero.getStats().getAttack() - (int)(0.5 * enemy.getStats().getDefense());
            if (damage < (int)(0.2 * hero.getStats().getAttack()))
                damage = (int)(0.2 * hero.getStats().getAttack());
            if (rand.nextInt(8) == 4) {
                enemy.getStats().reduceHp(damage * 3);
                System.out.println("You hit " + enemy.getName() + " by " + damage * 3 + "(critical hit)");
            }
            else {
                enemy.getStats().reduceHp(damage);
                System.out.println("You hit " + enemy.getName() + " by " + damage);
            }
            sleep(300);

            if (enemy.getStats().getHp() > 0) {
                damage = enemy.getStats().getAttack() - (int)(0.3 * hero.getStats().getDefense());
                if (damage < (int)(0.4 *enemy.getStats().getAttack()));
                    damage = (int)(0.4 *enemy.getStats().getAttack());
                if (rand.nextInt(5) == 3) {
                    hero.getStats().reduceHp(damage * 5);
                    System.out.println(enemy.getName() + " hit you by " + damage * 5 + "(critical hit)");
                }
                else {
                    hero.getStats().reduceHp(damage);
                    System.out.println(enemy.getName() + " hit you by " + damage);
                }
            }
        }
        if (hero.getStats().getHp() < 0) {
            System.out.println(enemy.getName() + " killed you");
            this.alive = false;
        }
        else {
            System.out.println("You slayed " + enemy.getName());
            hero.gainExp(300 / hero.getLevel());
            hero.gainCoins(1 * hero.getLevel());
            pickItem();
        }
    }

    public void menu(){
        if (console != null)
            while (true)
                console.menu();
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
                newGame();
            else if (val == 3)
                save();
            else if (val == 4)
                console.load();
            else if (val == 5 && hero != null)
                hero.printInfo();
            else if (val == 6)
               store();
            else if (val == 7) {
                System.out.println("Quiting...");
                sleep(500);
                System.exit(0);
            }
        }
        else
            console.menu();
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

    private void run() {
        if ((rand.nextInt(2) + 1) % 2 == 0) {
                System.out.println("You successfully evaded " + enemy.getName());
                hero.gainExp(100 / hero.getLevel());
        }
        else {
            System.out.println(enemy.getName() + " hit you by " + enemy.getStats().getAttack() * 3 + " because you chicked out");
            hero.getStats().reduceHp(enemy.getStats().getAttack() * 3);
        }
        if (hero.getStats().getHp() < 0) {
            System.out.println(enemy.getName() + " killed you");
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
        System.out.println("You met "+ enemy.getName() +". Fight or run? ");
        String act = MyReader.readConsole();
        if (act.toLowerCase().compareToIgnoreCase("fight") == 0)
            fight();
        else
            run();
    }

    private void save() {
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
        }
        else
            System.out.println("The is no hero in the current game, Please create a new one");
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