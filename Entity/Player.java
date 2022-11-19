package Entity;

import Entity.Entity;
import main.GamePanel;
import main.KeyboardListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyboardListener key_listener;

    public Player(GamePanel gp, KeyboardListener key_listener){
        this.gp = gp;
        this.key_listener = key_listener;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 200;
        y = 200;
        speed = 2;
        direction = "down";
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_up_2.png"));
            down1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/player/boy_right_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){

        if(key_listener.up_pressed || key_listener.down_pressed ||
                key_listener.left_pressed || key_listener.right_pressed) {
            if (key_listener.up_pressed) {
                direction = "up";
                y -= speed;
            } else if (key_listener.down_pressed) {
                direction = "down";
                y += speed;
            } else if (key_listener.left_pressed) {
                direction = "left";
                x -= speed;
            } else if (key_listener.right_pressed) {
                direction = "right";
                x += speed;
            }
            sprite_counter++;
            if (sprite_counter > 12) {
                if (sprite_num == 1) {
                    sprite_num = 2;
                } else if (sprite_num == 2) {
                    sprite_num = 1;
                }
                sprite_counter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch (direction){
            case "up":
                if(sprite_num == 1){
                    image = up1;
                }
                if(sprite_num == 2){
                    image = up2;
                }
                break;
            case "down":
                if(sprite_num == 1){
                    image = down1;
                }
                if(sprite_num == 2){
                    image = down2;
                }
                break;
            case "left":
                if(sprite_num == 1){
                    image = left1;
                }
                if(sprite_num == 2){
                    image = left2;
                }
                break;
            case "right":
                if(sprite_num == 1){
                    image = right1;
                }
                if(sprite_num == 2){
                    image =right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tile_size, gp.tile_size, null);
    }
}
