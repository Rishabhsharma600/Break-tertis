package demogame;

import javax.swing.*;


public class main_i {
    public static void main(String[] args) {
        JFrame frame=new JFrame();

        frame.setSize(700,600);
        frame.setTitle("Kittu game!");
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePlay g1=new gamePlay();
        frame.add(g1);
    }

}

