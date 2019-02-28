package com.tr.SwingLearn;

import javax.swing.*;
import java.awt.*;

public class Demo1 {
    public static void main(String[] args) {
        new ShowInterface();
    }

}

class ShowInterface extends JFrame {
    public ShowInterface() {
        JButton buttonNorth = new JButton();
        buttonNorth.setText("buttonNorth");

        JButton buttonWest = new JButton();
        buttonWest.setText("buttonWest");

        JButton buttonEast = new JButton();
        buttonEast.setText("buttonEast");

        JButton buttonSouth = new JButton();
        buttonSouth.setText("buttonSouth");

        this.setLayout(new BorderLayout());
        this.add(buttonNorth,BorderLayout.NORTH);
        this.add(buttonEast,BorderLayout.EAST);
        this.add(buttonSouth,BorderLayout.SOUTH);
        this.add(buttonWest, BorderLayout.WEST);
        this.setSize(400, 400);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("demo");
        this.setVisible(true);
    }
}
