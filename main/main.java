package main;

import javax.swing.*;
import java.io.IOException;

public class main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("random game");

        GamePanel game_panel = new GamePanel();
        window.add(game_panel);

        window.pack();//adjust the window to preferred size

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        game_panel.setObj();
        game_panel.startGameThread();

    }
}
