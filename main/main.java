package main;

import javax.swing.*;

public class main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("勇者斗恶龙");

        GamePanel game_panel = new GamePanel();
        window.add(game_panel);

        window.pack();//adjust the window to preferred size

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        game_panel.startGameThread();

    }
}
