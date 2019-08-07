package com.app.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.app.game.*;

public class Swingy extends JFrame {
    private static final long serialVersionUID = 1L;
    private JComboBox dropDown;

    public Swingy() {
        super("Swingy");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 300, 500, 300);
        setResizable(true);
        mainMenu();
    }

    private class MenuListener implements ActionListener {
        private int btnVal;
    
        public MenuListener(int btnVal) {
            this.btnVal = btnVal;
        }
    
        public void actionPerformed(ActionEvent e) {
            if (btnVal == 5)
                store();
            else if (btnVal == 10)
                mainMenu();
            else if (btnVal == 6)
                System.exit(0);
            else if (btnVal == 3)
                selectPlayer();
            else if (btnVal == 42)
                System.out.println(dropDown.getSelectedIndex());
        }
    }

    public void mainMenu() {

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        JButton newGame = new JButton("New Game");
        JButton cntie = new JButton("Continue");
        JButton loadHero = new JButton("Load Hero");
        JButton heroStats = new JButton("Hero Stats");
        JButton store = new JButton("Store");
        JButton exit = new JButton("Exit");

        addBtnComp(newGame, menuPanel, Color.BLUE, 100, 10, 100, 50, 1);
        addBtnComp(cntie, menuPanel, Color.BLUE, 100, 60, 100, 50, 2);
        addBtnComp(loadHero, menuPanel, Color.BLUE, 100, 110, 100, 50, 3);
        addBtnComp(heroStats, menuPanel, Color.BLUE, 100, 160, 100, 50, 4);
        addBtnComp(store, menuPanel, Color.BLUE, 100, 210, 100, 50, 5);
        addBtnComp(exit, menuPanel, Color.BLUE, 100, 260, 100, 50, 6);

        setContentPane(menuPanel);
        menuPanel.setBackground(Color.DARK_GRAY);
        menuPanel.setVisible(true);
        setVisible(true);
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
            dropDown = new JComboBox(data);
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
        b.setForeground(Color.BLUE);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.addActionListener(new MenuListener(btnCmdVal));
        p.add(b);
    }
}