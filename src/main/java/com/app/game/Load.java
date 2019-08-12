package com.app.game;
import com.app.debug.Debug;
import com.app.readWrite.*;

public class Load {

    public static String[] loadNames() {
        return MyReader.readFile("Game.txt");
    }

    public static String[] load() {
        int i;
        String data[] = loadNames();
        if (data != null) {
            System.out.println("Please select a hero id to load : ");
            String[] info;
            for(i = 0; i < data.length; i++) {
                info = data[i].split("@",  10);
                System.out.println((i + 1) + ". " + info[1] + " on " + info[0] + "             " + info[8]);
            }
            System.out.println((i + 1) + ". Back to Main Menu");
            String cmd = MyReader.readConsole();
            if (Debug.isInteger(cmd)) {
                int val = Integer.parseInt(cmd);
                if (val <= i && val > 0) {
                    info = MyReader.readFileLine("Game.txt", val).split("@", 20);
                    return info;
                }
                else
                    return null;
            }
            else
                return null;
        }
        else
            return null;
    }
}