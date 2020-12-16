package ru.itis;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private Graphics2D graphics;
    private JPanel mainPanel;
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
        JFrame frame = new JFrame("Window");
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
        menuItem.addActionListener(e -> System.exit(0));
        menu.add(menuItem);

        //Build second menu in the menu bar.
        menu = new JMenu("About");
        menuBar.add(menu);
        menu.addMenuListener(new MenuListener() {
            private JFrame aboutFrame;

            @Override
            public void menuSelected(MenuEvent e) {
                aboutFrame = new JFrame("About");
                aboutFrame.setBounds(600, 300, 500, 500);
                aboutFrame.setVisible(true);
                aboutFrame.setAlwaysOnTop(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                aboutFrame.dispose();
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
        frame.setJMenuBar(menuBar);

        //Build rightPanel
        JPanel rightPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(10, 0, 20, 20);
        rightPanel.setLayout(gridLayout);
        JButton drawButton = new JButton("Draw");
        rightPanel.add(drawButton);
        drawButton.addActionListener(e -> {
            int width = mainPanel.getSize().width;
            int height = mainPanel.getSize().height;
            shape = new Rectangle((width / 3), (height / 3), width / 3, height / 3);
            graphics.setColor(Color.PINK);
            graphics.fill(shape);

        });
        JButton spinButton = new JButton("Spin");
        rightPanel.add(spinButton);
        spinButton.addActionListener(e -> {
            status = !status;
            new Thread(() -> {
                while (true) {
                    if (status) {
                        break;
                    }
                    mainPanel.repaint();
                    SwingUtilities.invokeLater(() -> {
                        graphics.rotate(Math.toRadians(15), mainPanel.getSize().width/2.f, mainPanel.getSize().height/2.f);
                        graphics.draw(shape);
                        graphics.setColor(Color.PINK);
                        graphics.fill(shape);
                    });
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }).start();
        });
        JButton printButton = new JButton("Form");
        rightPanel.add(printButton);
        printButton.addActionListener(e -> {
            List<Component> componentList = new LinkedList<>();
            mainPanel.repaint();

            JLabel formLabel = new JLabel("Form");
            formLabel.setBounds(220, 68, 46, 14);
            mainPanel.add(formLabel);
            componentList.add(formLabel);

            JLabel emailLabel = new JLabel("Email");
            emailLabel.setBounds(65, 115, 100, 20);
            mainPanel.add(emailLabel);
            componentList.add(emailLabel);

            JTextField emailText = new JTextField();
            emailText.setBounds(150, 112, 250, 20);
            mainPanel.add(emailText);
            emailText.setColumns(10);
            componentList.add(emailText);

            JLabel passwordLabel = new JLabel("Password");
            passwordLabel.setBounds(65, 150, 100, 20);
            mainPanel.add(passwordLabel);
            componentList.add(passwordLabel);

            JTextField passwordText = new JTextField();
            passwordText.setBounds(150, 150, 250, 20);
            mainPanel.add(passwordText);
            passwordText.setColumns(10);
            componentList.add(passwordText);

            JButton clearButton = new JButton("Clear");
            clearButton.addActionListener(e1 -> {
                emailText.setText("");
                passwordText.setText("");
            });
            componentList.add(clearButton);
            clearButton.setBounds(300, 200, 100, 25);
            mainPanel.add(clearButton);

            JButton submitButton = new JButton("Submit");
            componentList.add(submitButton);
            submitButton.addActionListener(p -> {
                for (Component component : componentList) {
                    mainPanel.remove(component);
                }
                mainPanel.repaint();
            });

            submitButton.setBounds(65, 200, 100, 25);
            mainPanel.add(submitButton);
        });
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setPreferredSize(dim);
        frame.getContentPane().add(rightPanel, BorderLayout.EAST);

        //Build mainPanel
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);

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
}
