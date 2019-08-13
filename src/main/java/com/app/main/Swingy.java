package com.app.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.app.game.*;

public class Swingy extends JFrame {
    private static final long serialVersionUID = 1L;
    private Game game;
    private JComboBox<String> dropDown;

    public Swingy(Game gme) {
        super("Swingy");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 300, 500, 300);
        setResizable(true);
        this.game = gme;
    }

    private class MenuListener implements ActionListener {
        private int btnVal;
    
        public MenuListener(int btnVal) {
            this.btnVal = btnVal;
        }
    
        public void actionPerformed(ActionEvent e) {
            if (btnVal == 1)
                newGame();
            else if (btnVal == 5)
                store();
            else if (btnVal == 4)
                showStats();
            else if (btnVal == 0)
                game.save();
            else if (btnVal == 10)
                mainMenu();
            else if (btnVal == 6)
                System.exit(0);
            else if (btnVal == 3)
                selectPlayer();
            else if (btnVal == 42)
                game.loadHero(dropDown.getSelectedIndex());
            else if (btnVal == 20)
                System.out.println("Test heor");
        }
    }

    public void newGame() {
        String[] heroClasses =  {"Seilie", "Warlock", "Gunman"};
        String[] Weapons = {"StormBreaker","Bow And Arrow", "Gun"};
        String[] Helms = {"ChampionsHeadguard","Kingdoms's Pride","Blazeguard"};
        String[] Amour = {"Antique Blockade", "Guardian's Shield", "Light Shield"};
        JPanel newGame = new JPanel();
        JLabel nameLabel = new JLabel("Enter hero name");
        JLabel classLabel = new JLabel("Select hero class");
        JLabel weaponLabel = new JLabel("Select hero Weapon");
        JLabel helmLabel = new JLabel("Select hero helm");
        JLabel amourLabel = new JLabel("Select hero amour");
        JButton backtoMenu = new JButton("Main Menu");
        JButton createHero = new JButton("Create Hero");
        JTextField heroName = new JTextField();
        JComboBox<String> heroTypes = new JComboBox<>(heroClasses);
        JComboBox<String> heroWeapons = new JComboBox<>(Weapons);
        JComboBox<String> heroHelms = new JComboBox<>(Helms);
        JComboBox<String> heroAmour = new JComboBox<>(Amour);

        newGame.setLayout(new BoxLayout(newGame, BoxLayout.Y_AXIS));
        heroName.setMaximumSize(new Dimension(400, 80));

        newGame.add(nameLabel);
        newGame.add(heroName);
        newGame.add(classLabel);
        newGame.add(heroTypes);
        newGame.add(weaponLabel);
        newGame.add(heroWeapons);
        newGame.add(helmLabel);
        newGame.add(heroHelms);
        newGame.add(amourLabel);
        newGame.add(heroAmour);
        addBtnComp(backtoMenu, newGame, Color.RED,100, 100, 100, 50, 10);
        addBtnComp(createHero, newGame, Color.RED,100, 100, 100, 50, 20);

        Component[] comps = newGame.getComponents();
        for (int i = 0; i < newGame.getComponents().length; i++)
            ((JComponent) comps[i]).setAlignmentX(Component.CENTER_ALIGNMENT);

        setContentPane(newGame);
        newGame.setAutoscrolls(true);
        newGame.setBackground(Color.DARK_GRAY);
        newGame.setVisible(true);
        setVisible(true);
    }

    public void mainMenu() {

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        JButton newGame = new JButton("New Game");
        JButton cntie = new JButton("Continue");
        JButton loadHero = new JButton("Load Hero");
        JButton saveHero = new JButton("Save Hero");
        JButton heroStats = new JButton("Hero Stats");
        JButton store = new JButton("Store");
        JButton exit = new JButton("Exit");

        addBtnComp(newGame, menuPanel, Color.BLUE, 100, 10, 100, 50, 1);
        addBtnComp(cntie, menuPanel, Color.BLUE, 100, 60, 100, 50, 2);
        addBtnComp(loadHero, menuPanel, Color.BLUE, 100, 110, 100, 50, 3);
        addBtnComp(saveHero, menuPanel, Color.BLUE, 100, 60, 100, 50, 0);
        addBtnComp(heroStats, menuPanel, Color.BLUE, 100, 160, 100, 50, 4);
        addBtnComp(store, menuPanel, Color.BLUE, 100, 210, 100, 50, 5);
        addBtnComp(exit, menuPanel, Color.BLUE, 100, 260, 100, 50, 6);

        setContentPane(menuPanel);
        menuPanel.setBackground(Color.DARK_GRAY);
        menuPanel.setVisible(true);
        setVisible(true);
    }

    public void showStats() {
        if (game.getHero() != null) {
            JPanel statsPanel = new JPanel();
            statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
            JLabel heroName = new  JLabel(game.getHero().getName());
            JLabel heroClass = new JLabel(game.getHero().getType());
            JButton backtoMenu = new JButton("Main Menu");

            addBtnComp(backtoMenu, statsPanel, Color.RED,100, 100, 100, 50, 10);
            setContentPane(statsPanel);
            statsPanel.add(heroName);
            statsPanel.add(heroClass);
            statsPanel.setBackground(Color.DARK_GRAY);
            statsPanel.setVisible(true);
            setVisible(true);
        }
        else
            mainMenu();
    }

    public void store() {
        JPanel storePanel = new JPanel();
        storePanel.setLayout(new BoxLayout(storePanel, BoxLayout.Y_AXIS));
        JButton upgradeWpn = new JButton("Upgrade Weapon");
        JButton upgradeAmr = new JButton("Upgrade Amour");
        JButton upgradeHlm = new JButton("Upgrade Helm");
        JButton backtoMenu = new JButton("Main Menu");

        addBtnComp(upgradeWpn, storePanel, Color.RED,100, 100, 200, 50, 7);
        addBtnComp(upgradeAmr, storePanel, Color.RED,100, 100, 100, 50, 8);
        addBtnComp(upgradeHlm, storePanel, Color.RED,100, 100, 100, 50, 9);
        addBtnComp(backtoMenu, storePanel, Color.RED,100, 100, 100, 50, 10);

        setContentPane(storePanel);
        storePanel.setBackground(Color.DARK_GRAY);
        storePanel.setVisible(true);
        setVisible(true);
    }

    public void selectPlayer() {
        JPanel playerSelect = new JPanel();
        playerSelect.setLayout(new BoxLayout(playerSelect, BoxLayout.Y_AXIS));
        String[] saved = Load.loadNames();
        if (saved != null && saved.length > 0) {
            JLabel info1 = new JLabel("Please select a hero to load");
            info1.setAlignmentX(Component.CENTER_ALIGNMENT);
            playerSelect.add(info1);
            String[] data = new String[saved.length];
            for (int i = 0; i < saved.length; i++)
                data[i] = saved[i].split("@", 20)[1] + " on " + saved[i].split("@", 20)[0];
            dropDown = new JComboBox<>(data);
            dropDown.setSelectedIndex(0);
            dropDown.setMaximumSize(new Dimension(200, 30));
            playerSelect.add(dropDown);
        }
        
        JButton load = new JButton("Load Hero");
        addBtnComp(load, playerSelect, Color.DARK_GRAY, 12, 12, 100, 50, 42);
        JButton backtoMenu = new JButton("Main Menu");
        addBtnComp(backtoMenu, playerSelect, Color.RED,100, 100, 100, 50, 10);
        setContentPane(playerSelect);
        playerSelect.setBackground(Color.DARK_GRAY);
        playerSelect.setVisible(true);
        setVisible(true);
    }
    

    private void addBtnComp(JButton b, JPanel p, Color c, int sx, int sy, int width, int height, int btnCmdVal) {
        b.setBackground(Color.BLUE);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMinimumSize(new Dimension(300, 80));
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.addActionListener(new MenuListener(btnCmdVal));
        p.add(b);
    }
}