package Entity;

import main.GamePanel;
import main.KeyboardListener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyboardListener key_listener;

    // player's position won't change
    public final int screen_x;
    public final int screen_y;
    int key_num = 0;

    public Player(GamePanel gp, KeyboardListener key_listener){
        this.gp = gp;
        this.key_listener = key_listener;

        screen_x = gp.screen_width/2 - (gp.tile_size/2);
        screen_y = gp.screen_height/2 - (gp.tile_size/2);

        solid_area = new Rectangle();
        solid_area.x = 8;
        solid_area.y = 16;
        default_solid_area_x = solid_area.x;
        default_solid_area_y = solid_area.y;
        solid_area.width = 32;
        solid_area.height = 32;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        map_x = gp.tile_size * 23;
        map_y = gp.tile_size * 21;
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
            } else if (key_listener.down_pressed) {
                direction = "down";
            } else if (key_listener.left_pressed) {
                direction = "left";
            } else if (key_listener.right_pressed) {
                direction = "right";
            }
            collision_on = false;
            gp.collision_checker.checkCollision(this);

            // check object collision
            int obj_index = gp.collision_checker.checkCollision_obj(this, true);
            obj_pickup(obj_index);

            // if collision, player cannot move
            // else, can move
            if(!collision_on) {
                switch (direction) {
                    case "up":
                        map_y -= speed;
                        break;
                    case "down":
                        map_y += speed;
                        break;
                    case "left":
                        map_x -= speed;
                        break;
                    case "right":
                        map_x += speed;
                        break;
                }
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
    public void obj_pickup(int i){
        if(i != Integer.MAX_VALUE){
            String obj_name = gp.obj[i].name;

            switch(obj_name){
                case "Key":
                    key_num++;
                    gp.obj[i] = null;
                    break;
                case"door":
                    if(key_num > 0){
                        gp.obj[i] = null;
                        key_num--;
                    }
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
        g2.drawImage(image, screen_x, screen_y, gp.tile_size, gp.tile_size, null);
    }
}
