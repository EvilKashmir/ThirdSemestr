package ru.itis;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private Graphics2D graphics;
    private JFrame frame;
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    private JCheckBoxMenuItem cbMenuItem;
    private JRadioButtonMenuItem rbMenuItem;
    private JMenu submenu;
    private Shape shape;
    private boolean status = true;

    private final Dimension dim = new Dimension(250, 250);

    public static void main(String[] args) {
        Main main = new Main();
        main.initGUI();
    }

    public void initGUI() {
        frame = new JFrame("Window");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Menu");
        menuBar.add(menu);

        //a group of JMenuItems
        menuItem = new JMenuItem("Text-only menu item");
        menu.add(menuItem);

        //a group of radio button menu items
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();
        rbMenuItem = new JRadioButtonMenuItem("Radio button menu item");
        rbMenuItem.setSelected(true);
        group.add(rbMenuItem);
        menu.add(rbMenuItem);
        rbMenuItem = new JRadioButtonMenuItem("Another one");
        group.add(rbMenuItem);
        menu.add(rbMenuItem);

        //group of check box menu items
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("Check box menu item");
        menu.add(cbMenuItem);
        cbMenuItem = new JCheckBoxMenuItem("Another one");
        menu.add(cbMenuItem);

        //submenu
        menu.addSeparator();
        submenu = new JMenu("Submenu");
        menuItem = new JMenuItem("Item in the submenu");
        submenu.add(menuItem);
        menuItem = new JMenuItem("Another item");
        submenu.add(menuItem);
        menu.add(submenu);

        //Exit menu item
        menu.addSeparator();
        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener(new ExitListener());
        menu.add(menuItem);

        //Build second menu in the menu bar.
        menu = new JMenu("About");
        menuBar.add(menu);
        menu.addMenuListener(new AboutListener());
        frame.setJMenuBar(menuBar);

        //Build rightPanel
        JPanel rightPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(10, 0, 20, 20);
        rightPanel.setLayout(gridLayout);
        JButton drawButton = new JButton("Draw square");
        rightPanel.add(drawButton);
        drawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int width = panel.getSize().width;
                int height = panel.getSize().height;
                shape = new Rectangle((width / 4), 30 + (height / 4), width / 2, height / 2);
                graphics.setColor(Color.PINK);
                graphics.fill(shape);

            }
        });
        JButton spinButton = new JButton("Spin Right Round");
        rightPanel.add(spinButton);
        spinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                status = !status;
                new Thread(() -> {
                    while (true) {
                        if (status) {

                            break;
                        }
                        panel.repaint();
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                graphics.rotate(Math.toRadians(15), panel.getSize().width / 2, panel.getSize().height / 2);
                                graphics.draw(shape);
                                graphics.setColor(Color.PINK);
                                graphics.fill(shape);
                            }
                        });
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        JButton printButton = new JButton("Print Form");
        rightPanel.add(printButton);
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setPreferredSize(dim);
        frame.getContentPane().add(rightPanel, BorderLayout.EAST);

        //Build mainPanel
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        //Build bottomPanel and status
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        frame.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));

        frame.setBounds(10, 10, 1900, 1000);
        frame.setVisible(true);
        graphics = (Graphics2D) frame.getGraphics();
    }

    private class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class AboutListener implements MenuListener {
        private JFrame aboutFrame;

        public void menuSelected(MenuEvent e) {
            aboutFrame = new JFrame("About");
            aboutFrame.setBounds(600, 300, 500, 500);
            aboutFrame.setVisible(true);
            aboutFrame.setAlwaysOnTop(true);
        }

        public void menuDeselected(MenuEvent e) {
            aboutFrame.dispose();
        }

        public void menuCanceled(MenuEvent e) {
        }
    }
}
