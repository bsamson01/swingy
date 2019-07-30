package  com.app.artifacts.helms;

public class Helm {
    private int level;
    private int hp;
    protected String type;

    Helm(int points, int playerLevel, String typ) {
        this.hp =  (playerLevel - 1) * 10 + points;
        this.level = (this.hp / 10) + 1;
        this.type = typ;
    }

    public void upgrade(int points) {
        this.hp += points;
        this.level = (this.hp / 10) + 1;
    }

    public int destroy() {
        return (this.level);
    }

    public int getHp() {
        return (this.hp);
    }

    public void printName() {
        System.out.println("Helm :-> " + this.type + " :: Hp :-> +" + this.getHp());
    }

    public String getType() {
        return (this.type);
    }
}