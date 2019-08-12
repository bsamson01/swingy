package com.app.main;
import com.app.game.*;

public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            if (args[0].compareToIgnoreCase("console") == 0 || args[0].compareToIgnoreCase("gui") == 0)
                new Game(args[0]);
            else
                System.out.println("Unrecognized interface");
        }
        else
            System.out.println("Please  specify weather to launch in gui or console");
    }
}