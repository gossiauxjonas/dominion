package javaGui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jonasty on 24/03/2016.
 */
public class MainFrame extends JFrame {

    public MainFrame() {

        setSize(new Dimension(260, 100));
        setTitle("Dominion 0.1");
        Container content = getContentPane();
        content.add(new GamePanel());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);


    }


}
