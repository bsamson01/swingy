package  com.app.readWrite;
import java.io.*;

public class MyReader {
    public static String readConsole() throws IOException
    {
        BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }
    
    public static String readFile(String filePath, int lineNumber) throws IOException
    {
        String result;
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        for (int i = 0; i < lineNumber - 1; i++)
            br.readLine();
        result = br.readLine();
        br.close();
        return result;
    }
}