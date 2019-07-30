package  com.app.artifacts.weapons;

public class Weapon {
    private int level;
    private int stregnth;
    protected String type;

    Weapon(int points, int playerLevel, String typ) {
        this.stregnth =  (playerLevel - 1) * 10 + points;
        this.level = (this.stregnth / 10) + 1;
        this.type = typ;
    }

    public void upgrade(int points) {
        this.stregnth += points;
        this.level = (this.stregnth / 10) + 1;
    }

    public int destroy() {
        return (this.level);
    }

    public int getDamage() {
        return (this.stregnth);
    }

    public void printName() {
        System.out.println("Weapon :-> " + this.type + " :: Damage :-> +" + this.getDamage());
    }

    public String getType() {
        return (this.type);
    }
}