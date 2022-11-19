package main;

import Entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Screen setting
    final int original_tileSize = 16; // 16x16 tile
    final int scale = 3;

    public int tile_size = original_tileSize * scale; // 48x48
    final int max_screenCol = 16;
    final int max_screenRow = 12;
    final int screen_width = tile_size * max_screenCol; // 768
    final int screen_height = tile_size * max_screenRow; // 576

    // FPS
    int FPS = 120;

    KeyboardListener key_listener = new KeyboardListener();

    Thread game_thread;
    Player player = new Player(this, key_listener);

    // player's initial position
    int player_X = 100;
    int player_y = 100;
    int player_speed = 2;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // improve rendering performance
        this.addKeyListener(key_listener);
        this.setFocusable(true);// this gamepanel can be focused to receive key input
    }

    public void startGameThread() {
        game_thread = new Thread(this);
        game_thread.start(); //auto call run()
    }
//    @Override
//    public void run() {
//
//        double time_between_each_draw = 1000000000/FPS;
//        double next_draw_time = System.nanoTime() + time_between_each_draw;
//        while(game_thread != null){
//            // update info as character position
//            update();
//            // draw the screen with updated info
//            repaint();
//            try {
//                double remaining_time = next_draw_time - System.nanoTime();
//                remaining_time = remaining_time/1000000;
//
//                if(remaining_time < 0){
//                    remaining_time = 0;
//                }
//                Thread.sleep((long) remaining_time);// sometimes not precise
//
//                next_draw_time += time_between_each_draw;
//
//            } catch(InterruptedException e){
//                e.printStackTrace();
//            }
//;        }
//    }
    @Override
    public void run() {

        double time_between_each_draw = 1000000000/FPS;
        double delta = 0;
        long last_time = System.nanoTime();
        long current_time;
        while(game_thread != null){
            current_time = System.nanoTime();

            delta += (current_time - last_time) / time_between_each_draw;

            last_time = current_time;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);//use the parent class method
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);

        g2.dispose();
    }
}
