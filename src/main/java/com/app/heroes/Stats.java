package  com.app.heroes;

public  class Stats {
    private int     attack;
    private int     defense;
    private int     hp;

    public Stats(int attack, int defense, int hp) {
        this.attack = attack;
        this.defense = defense;
        this.hp = hp;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getHp() {
        return this.hp;
    }

    public void reduceHp(int damage) {
        this.hp -= damage;
    }
}