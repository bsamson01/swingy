package com.app.main;

import javax.swing.*;

public class Swingy extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel mainPannel;
    private JButton menu;

    public static void main(String[] args) {
        new Swingy();
    }

    public Swingy() {
        super("Swingy");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400, 300, 500, 300);
        setResizable(true);
        setVisible(true);
        mainPannel = new JPanel();
        menu = new JButton("Menu");
    }
}