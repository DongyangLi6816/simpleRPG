package main;

import Background.backgroundManager;
import Entity.Player;
import OBJ.game_object;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Screen setting
    final int original_tileSize = 16; // 16x16 tile
    final int scale = 3;

    // game window settings
    public int tile_size = original_tileSize * scale; // 48x48
    public int max_screenCol = 16;
    public int max_screenRow = 12;
    public int screen_width = tile_size * max_screenCol; // 768
    public int screen_height = tile_size * max_screenRow; // 576

    // world map setting
    public final int world_max_col = 50;
    public final int world_max_row = 50;
    public final int word_width = max_screenCol * tile_size;
    public final int word_height = max_screenRow * tile_size;

    // FPS
    int FPS = 120;

    backgroundManager background = new backgroundManager(this);

    KeyboardListener key_listener = new KeyboardListener();

    Thread game_thread;
    public CollisionCheck collision_checker = new CollisionCheck(this);
    public object_setter object_setter = new object_setter(this);
    public Player player = new Player(this, key_listener);
    public game_object obj[] = new game_object[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // improve rendering performance
        this.addKeyListener(key_listener);
        this.setFocusable(true);// this gamepanel can be focused to receive key input
    }

    public void setObj(){
        object_setter.setObject();
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

        //map
        background.draw(g2);
        //object
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //player
        player.draw(g2);

        g2.dispose();
    }
}
