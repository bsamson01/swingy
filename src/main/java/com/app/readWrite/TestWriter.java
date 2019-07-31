package  com.app.readWrite;
import  java.io.*;

public  class   TestWriter {
    public  static void write(String    content)
    {
        checkForFile();
        writeToFile(content);
    }

    private static void checkForFile()
    {
        try {
            File file = new File("Game.txt");
            if (file.exists())
                return ;
            else
                file.createNewFile();
        }
        catch (Exception e)
        {
            System.err.println("Error");
        }
    }

    private static  void writeToFile(String content)
    {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Game.txt", true)));
            out.println(content);
            out.close();

        }
        catch(Exception e)
        {
            System.out.println("Problem writing to file");
        }
    }

    public static  void deleteFile()
    {
        try {
            File file = new File("Game.txt");
            if (file.exists())
                file.delete();
            else
                return ;
        }
        catch (Exception e)
        {
            System.err.println("Error");
        }
    }
}