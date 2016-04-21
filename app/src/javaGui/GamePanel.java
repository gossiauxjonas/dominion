package javaGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Jonasty on 24/03/2016.
 */

public class GamePanel extends JPanel {
    private JButton startButton;
    private JButton exitButton;


    public GamePanel() {

        initComponents();
        addComponents();
        addListener();

    }


    private void initComponents() {
        startButton = new JButton("Start dominion");
        exitButton = new JButton("exit game");

    }


    private void addComponents() {

        setLayout(new BorderLayout());

        JPanel hoofdPanel = new JPanel();
        hoofdPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setLayout(new GridLayout(1, 2));
        startButtonPanel.add(startButton);
        startButtonPanel.add(exitButton);
        hoofdPanel.add(startButtonPanel);
        setBackground(Color.green.darker().darker());
        add(hoofdPanel, BorderLayout.SOUTH);






    }

    public void addListener() {
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("you should keep playing");

            }
        });


    }
}



