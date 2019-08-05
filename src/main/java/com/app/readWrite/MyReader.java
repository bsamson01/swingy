package com.app.readWrite;
import java.io.*;

public class MyReader {
    public static String readConsole()
    {
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Boolean checkForFile()
    {
        try {
            File file = new File("Game.txt");
            if (file.exists())
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            System.err.println("Error file does not exist and could not be created");
            return false;
        }
    }
    
    public static String readFileLine(String filePath, int lineNumber) {
        if (checkForFile()) {
            String result;
            File file = new File(filePath);
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(file));
                for (int i = 0; i < lineNumber - 1; i++)
                    br.readLine();
                result = br.readLine();
                br.close();
                return result;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String[] readFile(String filePath) {
        if (checkForFile()) {
            String[] result = new String[256];
            String line;
            int i = 0;
            File file = new File(filePath);
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(file));
                while ((line = br.readLine()) != null) {
                    result[i] = line;
                    i++;
                }
                br.close();
                return result;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}