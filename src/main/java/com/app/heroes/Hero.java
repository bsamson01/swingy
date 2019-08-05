package  com.app.heroes;
import  com.app.artifacts.*;

public  class Hero {
    private String      name;
    private Stats       stats;
    private Stats       fullStats;
    private int         level;
    private int         experience;
    private Artifacts   artifacts;
    private Coordinates coordinates;
    private int         coins;
    private int         maxExp;

    public Hero(String nme) {
        this.name = nme;
        this.experience = 0;
        this.level = 1;
        this.stats = new Stats(20, 10, 100);
        this.artifacts = new Artifacts();
        coordinates = new Coordinates(0, 0);
        combineStats();
    }

    public Hero(String nme, int exp, Stats stats, Artifacts artifacts, int level) {
        this.name = nme;
        this.experience = exp;
        this.level = level;
        this.stats = stats;
        this.artifacts = artifacts;
        this.coordinates = new Coordinates(0, 0);
        combineStats();
    }

    public void printInfo() {
        System.out.println("----------------Hero Info-------------------------");
        System.out.println("| Name   : " + getName());
        System.out.println("| Exp    : " + getExp());
        System.out.println("| Level  : " + getLevel());
        System.out.println("| Coins  : " + getCoins());
        System.out.println("|_________________Stats___________________________");
        combineStats();
        System.out.println("|            Attack   : " + stats.getAttack());
        System.out.println("|            Defense  : " + stats.getDefense());
        System.out.println("|            Hp       : " + stats.getHp());
        System.out.println("|_______________Artifacts_________________________");
        System.out.println("| Weapon : " + artifacts.getWeapon().getType() + " (damage = +"+artifacts.getWeapon().getDamage()+")");
        System.out.println("| Amour  : " + artifacts.getAmour().getType() + " (protection = +"+artifacts.getAmour().getDefence()+")");
        System.out.println("| Helm   : " + artifacts.getHelm().getType() + " (hitpoints = +"+artifacts.getHelm().getHp()+")");
        System.out.println("--------------------------------------------------");
    }

    public void combineStats() {
        int att = stats.getAttack() + artifacts.getWeapon().getDamage();
        int def = stats.getDefense() + artifacts.getAmour().getDefence();
        int hp = stats.getHp() + artifacts.getHelm().getHp();
        this.fullStats = new Stats(att, def, hp);
    } 

    public int getLevel() {
        return this.level;
    }

    public int getExp() {
        return this.experience;
    }

    public String getName() {
        return this.name;
    }

    public Stats getStats() {
        return this.fullStats;
    }

    public int getCoins() {
        return this.coins;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    public Artifacts getArtifacts() {
        return this.artifacts;
    }

    public void nextLevel() {
        this.level++;
        int att = (int)(stats.getAttack() * 1.3);
        int def = (int)(stats.getDefense() * 1.3);
        int health = (int)(stats.getHp() * 1.5); 
        stats = new Stats(att, def, health);
        System.out.println("Congratulations you have leveled up to level " + this.level);
    }

    public void gainCoins(int cn) {
        this.coins += cn;
    }

    public void useCoins(int cn) {
        this.coins -= cn;
    }

    public void gainExp(int points) {
        this.experience += points;
        this.maxExp = this.level * 1000 + (this.level - 1) * (this.level - 1) * 450;
        if (this.experience >= this.maxExp) {
            this.nextLevel();
        }
    }
}