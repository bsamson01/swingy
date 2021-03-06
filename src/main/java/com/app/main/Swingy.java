package com.app.main;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;

import com.app.artifacts.amour.*;
import com.app.artifacts.helms.*;
import com.app.artifacts.weapons.*;
import com.app.game.*;

public class Swingy extends JFrame {
    private static final long serialVersionUID = 1L;
    private Game game;
    private JComboBox<String> dropDown;

    JComboBox<String> heroTypes;
    JComboBox<String> heroWeapons;
    JComboBox<String> heroHelms;
    JComboBox<String> heroAmour;
    JTextField heroName;
    JPanel gameView = new JPanel();
    JPanel gameControls = new JPanel();
    JSplitPane gameDisplay = new JSplitPane(SwingConstants.VERTICAL, gameView, gameControls);
    JButton left = new JButton("Left");
    JButton right = new JButton("Right");
    JButton up = new JButton("Up");
    JButton down = new JButton("Down");
    JButton fight = new JButton("Fight");
    JButton run = new JButton("Run");
    JButton equip = new JButton("Equip");
    JButton inspect = new JButton("Inspect");
    JButton mainMenu = new JButton("Main Menu");
    JEditorPane fightSim = new JEditorPane();

    public Swingy(Game gme) {
        super("Swingy");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 300, 700, 500);
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
            else if (btnVal == 2)
                play();
            else if (btnVal == 3)
                selectPlayer();
            else if (btnVal == 4)
                showStats();
            else if (btnVal == 5)
                store();
            else if (btnVal == 6)
                System.exit(0);
            else if (btnVal == 0)
                game.save();
            else if (btnVal == 10)
                mainMenu();
            else if (btnVal == 42)
                game.loadHero(dropDown.getSelectedIndex());
            else if (btnVal == 20)
                createHero();
            else if (btnVal == 33)
                game.move(4);
            else if (btnVal == 34)
                game.move(2);
            else if (btnVal == 35)
                game.move(1);
            else if (btnVal == 36)
                game.move(3);
            else if (btnVal == 30)
                game.fight();
            else if (btnVal == 31)
                game.run();
            else if (btnVal == 37)
                game.guiInspect();
            else if (btnVal == 32)
                game.guiPickItem();
        }
    }

    public void play() {
        if (gameDisplay != null) {
            gameView = new JPanel();
            gameControls = new JPanel();
            gameDisplay = new JSplitPane(SwingConstants.VERTICAL, gameView, gameControls);
        }
        addBtnComp(mainMenu, gameControls, Color.RED,100, 100, 100, 50, 10);
        addBtnComp(fight, gameControls, Color.RED,100, 100, 100, 50, 30);
        addBtnComp(run, gameControls, Color.RED,100, 100, 100, 50, 31);
        addBtnComp(equip, gameControls, Color.RED,100, 100, 100, 50, 32);
        addBtnComp(left, gameControls, Color.RED,100, 100, 100, 50, 33);
        addBtnComp(right, gameControls, Color.RED,100, 100, 100, 50, 34);
        addBtnComp(up, gameControls, Color.RED,100, 100, 100, 50, 35);
        addBtnComp(down, gameControls, Color.RED,100, 100, 100, 50, 36);
        addBtnComp(inspect, gameControls, Color.RED,100, 100, 100, 50, 37);

        updateControls();
        fightSim.setEnabled(false);
        fightSim.setPreferredSize(new Dimension(350, 470));
        fightSim.setForeground(Color.red);
        JScrollPane viewScroll = new JScrollPane(fightSim, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        gameView.add(viewScroll);
        gameControls.setMinimumSize(new Dimension(300, 500));
        gameView.setMinimumSize(new Dimension(380, 400));
        setContentPane(gameDisplay);
        gameDisplay.setBackground(Color.DARK_GRAY);
        setVisible(true);
        game.remakeMap();
        this.requestDirection();
    }

    public void updateControls() {
        if (!game.metEnemy()) {
            fight.setEnabled(false);
            run.setEnabled(false);
            equip.setEnabled(false);
            left.setEnabled(true);
            right.setEnabled(true);
            up.setEnabled(true);
            down.setEnabled(true);
        }
        else {
            fight.setEnabled(true);
            run.setEnabled(true);
            equip.setEnabled(true);
            left.setEnabled(false);
            right.setEnabled(false);
            up.setEnabled(false);
            down.setEnabled(false);
        }

        if (game.picking())
            equip.setEnabled(true);
        else
            equip.setEnabled(false);
        
        if (game.inspecting())
            inspect.setEnabled(true);
        else
            inspect.setEnabled(false);
    }

    public void updateGamePanel(String s) {
        try {
            updateControls();
            Document doc = fightSim.getDocument();
            doc.insertString(doc.getLength(), s + "\n", null);
        } catch(BadLocationException exc) {
            exc.printStackTrace();
        }
    }

    public void newGame() {
        String[] heroClasses =  {"Gunman", "Seilie", "Warlock"};
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
        this.heroName = new JTextField();
        this.heroTypes = new JComboBox<>(heroClasses);
        this.heroWeapons = new JComboBox<>(Weapons);
        this.heroHelms = new JComboBox<>(Helms);
        this.heroAmour = new JComboBox<>(Amour);

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

    private void createHero() {
        if (heroName.getText().trim().equals(""))
            displayMessages("Hero Name cannot be empty", false);
        else {
            game.newHeroCreate(heroName.getText().trim(), heroTypes.getSelectedIndex() + 1, heroWeapons.getSelectedIndex() + 1, heroHelms.getSelectedIndex() + 1, heroAmour.getSelectedIndex() + 1);
            displayMessages("Hero " + game.getHero().getName() + " sucessfully created", true);
        }
    }

    private void displayMessages(String message, Boolean success) {

        JPanel messagePanel = new JPanel();
        JButton backtoMenu = new JButton("Main Menu");
        JLabel msg = new JLabel(message);

        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
        if (success)
            msg.setForeground(Color.GREEN);
        else
            msg.setForeground(Color.RED);
        msg.setFont(new Font("Pangrams", Font.BOLD, 19));
        messagePanel.add(msg);
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtnComp(backtoMenu, messagePanel, Color.RED,100, 100, 100, 50, 10);

        setContentPane(messagePanel);
        messagePanel.setBackground(Color.DARK_GRAY);
        messagePanel.setVisible(true);
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

        if (game.getHero() == null) {
            cntie.setEnabled(false);
            saveHero.setEnabled(false);
            heroStats.setEnabled(false);
            store.setEnabled(false);
        }

        setContentPane(menuPanel);
        menuPanel.setBackground(Color.DARK_GRAY);
        menuPanel.setVisible(true);
        setVisible(true);
    }

    public void showStats() {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(0, 2));
        JLabel heroName = new  JLabel(game.getHero().getName());
        JLabel heroClass = new JLabel(game.getHero().getType());
        JLabel heroExp = new JLabel(String.valueOf(game.getHero().getExp()));
        JLabel heroLevel = new JLabel(String.valueOf(game.getHero().getLevel()));
        JLabel heroCoins = new JLabel(String.valueOf(game.getHero().getCoins()));
        JLabel heroAttack = new JLabel(String.valueOf(game.getHero().getHeroStats().getAttack()));
        JLabel heroDef = new JLabel(String.valueOf(game.getHero().getHeroStats().getDefense()));
        JLabel heroHp = new JLabel(String.valueOf(game.getHero().getHeroStats().getHp()));
        JLabel heroWpn = new JLabel(game.heroWeapon().getType() + " (damage = +" + game.heroWeapon().getDamage() + ")");
        JLabel heroHlm = new JLabel(game.heroHelm().getType() + " (hitpoints = +" + game.heroHelm().getHp() + ")");
        JLabel heroAmr = new JLabel(game.heroAmour().getType() + " (defence = +" + game.heroAmour().getDefence() + ")");
        JButton backtoMenu = new JButton("Main Menu");

        statsPanel.add(setLabelBorder("Hero name", 0, 50, 0, 0));
        statsPanel.add(heroName);
        statsPanel.add(setLabelBorder("Hero Class", 0, 50, 0, 0));
        statsPanel.add(heroClass);
        statsPanel.add(setLabelBorder("Hero Exp", 0, 50, 0, 0));
        statsPanel.add(heroExp);
        statsPanel.add(setLabelBorder("Hero Level", 0, 50, 0, 0));
        statsPanel.add(heroLevel);
        statsPanel.add(setLabelBorder("Hero Coins", 0, 50, 0, 0));
        statsPanel.add(heroCoins);
        statsPanel.add(setLabelBorder("Hero Attack", 0, 50, 0, 0));
        statsPanel.add(heroAttack);
        statsPanel.add(setLabelBorder("Hero Defence", 0, 50, 0, 0));
        statsPanel.add(heroDef);
        statsPanel.add(setLabelBorder("Hero HP", 0, 50, 0, 0));
        statsPanel.add(heroHp);
        statsPanel.add(setLabelBorder("Hero Weapon", 0, 50, 0, 0));
        statsPanel.add(heroWpn);
        statsPanel.add(setLabelBorder("Hero Amour", 0, 50, 0, 0));
        statsPanel.add(heroAmr);
        statsPanel.add(setLabelBorder("Hero Helm", 0, 50, 0, 0));
        statsPanel.add(heroHlm);


        addBtnComp(backtoMenu, statsPanel, Color.RED,100, 100, 100, 50, 10);
        statsPanel.setBorder(new EmptyBorder(0, 10, 0, 0));
        setContentPane(statsPanel);
        statsPanel.setBackground(Color.DARK_GRAY);
        statsPanel.setVisible(true);
        setVisible(true);
    }

    private JLabel setLabelBorder(String name, int up, int left, int down, int right) {
        JLabel label = new JLabel(name);
        label.setBorder(new EmptyBorder(up, left, down, right));
        return label;
    }

    public void store() {
        if (game.getHero().getCoins() > 0) {
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
        else
            displayMessages("You currently have insufficient coins to access the store", false);
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
        b.setLocation(30, 30);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.addActionListener(new MenuListener(btnCmdVal));
        p.add(b);
    }

    public void droppedItem() {
        updateGamePanel(game.getEnemy().getName() + " dropped an item . Click inspect to view it.");
        updateControls();
    }

    public void metEnemy() {
        updateGamePanel("You met "+ game.getEnemy().getName() +". Choose wether to fight or run?");
        updateControls();
    }

    public void endOfMap() {
        updateGamePanel("You have reached the end of the map");
        updateControls();
    }

    public void requestDirection() {
        updateGamePanel("Please select a direction to move. Your current coordinates are (x : " + game.getHero().getCoordinates().getLatitude() + " , y : "+ game.getHero().getCoordinates().getLongitude() +")");
    }

    public void enemyHit(int damage, Boolean critical) {
        if (critical)
            updateGamePanel("You hit " + game.getEnemy().getName() + " by " + damage * 3 + "(critical hit)");
        else
            updateGamePanel("You hit " + game.getEnemy().getName() + " by " + damage);
    }

    public void heroHit(int damage, Boolean critical) {
        if (critical)
            updateGamePanel(game.getEnemy().getName() + " hit you by " + damage * 5 + "(critical hit)");
        else
            updateGamePanel(game.getEnemy().getName() + " hit you by " + damage);
    }

    public void enemyKilled() {
        updateGamePanel("You slayed " + game.getEnemy().getName());
        updateControls();
    }

    public void heroKilled() {
        updateGamePanel(game.getEnemy().getName() + " killed you");
        updateControls();
    }

    public void printEnemyStats() {
        updateGamePanel("Enemy name   : " + game.getEnemy().getName());
        updateGamePanel("Attack       : " + game.getEnemy().getStats().getAttack());
        updateGamePanel("Defence      : " + game.getEnemy().getStats().getDefense());
        updateGamePanel("Hp           : " + game.getEnemy().getStats().getHp());
        updateControls();
    }

    public void printWeaponInfo(Weapon wpn) {
        updateGamePanel("|  Weapon      : " + wpn.getType() );
        updateGamePanel("|  Damage      : " + wpn.getDamage());
        updateControls();
    }

    public void printAmourInfo(Amour amr) {
        updateGamePanel("|  Amour      : " + amr.getType() );
        updateGamePanel("|  Defence    : " + amr.getDefence());
        updateControls();
    }

    public void printHelmInfo(Helm hlm) {
        updateGamePanel("|  Helm    : " + hlm.getType() );
        updateGamePanel("|  Hp      : " + hlm.getHp());
        updateControls();
    }

    public void leveledUp(int level) {
        updateGamePanel("Congratulations you haved leved up to level " + level);
        updateControls();
    }
}