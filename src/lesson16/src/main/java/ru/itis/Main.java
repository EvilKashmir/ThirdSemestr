package ru.itis;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {
        final Dimension dim = new Dimension(250, 250);

        JFrame frame = new JFrame("Window");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Create the menu bar.
        JMenuBar menuBar = new JMenuBar();

        //Build the first menu.
        JMenu menu = new JMenu("A Menu");
        menuBar.add(menu);

        //a group of JMenuItems
        JMenuItem menuItem = new JMenuItem("A text-only menu item");
        menu.add(menuItem);

        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
        rbMenuItem.setSelected(true);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Another one");
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        //a group of check box menu items
        menu.addSeparator();
        JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
        menu.add(cbMenuItem);
        cbMenuItem = new JCheckBoxMenuItem("Another one");
        menu.add(cbMenuItem);

        //a submenu
        menu.addSeparator();
        JMenu submenu = new JMenu("A submenu");
        menuItem = new JMenuItem("An item in the submenu");
        submenu.add(menuItem);
        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        menu.add(submenu);

        //Build second menu in the menu bar.
        menu = new JMenu("Another Menu");
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        //Build rightPanel
        JPanel rightPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(10, 0, 5, 5);
        rightPanel.setLayout(gridLayout);
        JButton button1 = new JButton("Button 1");
        rightPanel.add(button1);
        JButton button2 = new JButton("Button 2");
        rightPanel.add(button2);
        JButton button3 = new JButton("Button 3");
        rightPanel.add(button3);
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setPreferredSize(dim);
        frame.getContentPane().add(rightPanel, BorderLayout.EAST);

        //Build mainPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

        //Build bottomPanel and status
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        frame.setBounds(10, 10, 1280, 720);
        frame.setVisible(true);

    }
}
