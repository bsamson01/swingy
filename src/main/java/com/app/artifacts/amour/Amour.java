package  com.app.artifacts.amour;

public class Amour {
    private int level;
    private int defence;
    protected String type;

    Amour(int points, int playerLevel, String typ) {
        this.defence =  (playerLevel - 1) * 30 + points;
        this.level = (this.defence / 30) + 1;
        this.type = typ;
    }

    public void upgrade(int points) {
        this.defence += points;
        this.level = (this.defence / 30) + 1;
    }

    public int destroy() {
        return (this.level);
    }

    public int getDefence() {
        return (this.defence);
    }

    public void printName() {
        System.out.println("Amour :-> " + this.type + " :: defence :-> +" + this.getDefence());
    }

    public String getType() {
        return (this.type);
    }

    public void improve(int coins) {
        this.defence += coins;
    }
}