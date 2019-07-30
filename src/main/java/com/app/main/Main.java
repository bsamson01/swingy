package  com.app.main;
import  com.app.readWrite.*;
import  com.app.heroes.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("Do you want to make a new hero? (y/n): ");
        if (MyReader.readConsole().equals("y")) {
            System.out.println("Give your hero a name : ");
            String name = MyReader.readConsole();
            if (name != null) {
                Hero myHero = new Hero(name);
                myHero.getArtifacts().getWeapon().printName();
                myHero.getArtifacts().getHelm().printName();
                myHero.getArtifacts().getAmour().printName();
            }
        }
        else {
            System.out.println("Swingy now quiting");
        }
    }
}