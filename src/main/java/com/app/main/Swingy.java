package  com.app.main;
import  com.app.readWrite.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Swingy implements ActionListener {
    private static Frame frame;
    private static Button left;
    private static Button right;
    private static Button up;
    private static Button down;
    private static Button fight;
    private static Button run;
    private static Button quit;
    private static Button mainMenu;
    private static TextArea txt;
    private static Button fightResult;

    private Button createButton(String name, int startX, int startY, int width, int height) {
        Button newButton = new Button(name);
        newButton.setBounds(startX, startY, width, height);
        newButton.addActionListener(this);
        frame.add(newButton);
        return newButton;
    }

    private TextArea createTextArea(String initialMsg, int startX, int startY, int width, int height) {
        TextArea newTextArea = new TextArea(initialMsg);
        newTextArea.setBounds(startX, startY, width, height);
        return newTextArea;
    }

    private void simulateFight() {
        int nbr = generateRandomValue(10, 1);
        if (nbr % 2 == 0)
            fightResult.setLabel("Won fight");
        else
            fightResult.setLabel("Lost fight");
    }

    private int generateRandomValue(int max, int min) {
        Random rand = new Random();
        return (rand.nextInt((max - min) + 1) + min);
    }


    private void movePlayer(String command) {
        if (command.equals("up"))
            fight.setEnabled(false);
        else if (command.equals("down"))
            run.setEnabled(false);
        else if (command.equals("left"))
            run.setEnabled(true);
        else
            fight.setEnabled(true);
    } 

    Swingy() {
        frame = new Frame("This is my test frame");
        left = createButton("left", 10, 200, 150, 30);
        right = createButton("right", 350, 200, 150, 30);
        up = createButton("up", 180, 100, 150, 30);
        down = createButton("down", 180, 300, 150, 30);
        fight = createButton("fight", 70, 400, 150, 30);
        run = createButton("run", 270, 400, 150, 30);
        fightResult = createButton("", 180, 200, 150, 30);
        
        fightResult.setEnabled(false);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent act) {
        switch (act.getActionCommand()) {
            case "left" :
            case "right" :
            case "up" :
            case "down" :
                movePlayer(act.getActionCommand());
                break ;
            case "fight" :
                simulateFight();
                break ;
            default :
                break ;
        }
    }

    public static void main(String args[]){
        Swingy f = new Swingy();
    }
}