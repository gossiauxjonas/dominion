package javaGui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jonasty on 24/03/2016.
 */
public class GamePanel extends JPanel {
    private JButton startButton;
    private JButton exitButton;



    public GamePanel() {

        initComponents();
        addComponents();

    }


    private void initComponents() {
        startButton = new JButton("Start dominion");
        exitButton = new JButton("exit game");

    }


    private void addComponents() {

        setLayout(new BorderLayout());

           JPanel hoofdPanel = new JPanel();
           hoofdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));


                JPanel startButtonPanel = new JPanel();
                startButtonPanel.setLayout(new GridLayout(1,0));
                startButtonPanel.add(startButton);
                startButtonPanel.add(exitButton);
            hoofdPanel.add(startButtonPanel);

        add(hoofdPanel, BorderLayout.SOUTH);


    }


}
