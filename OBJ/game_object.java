package OBJ;


import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class game_object {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int world_x, world_y;

    public void draw(Graphics2D g2, GamePanel gp){
        int screen_x = world_x - gp.player.map_x + gp.player.screen_x;
        int screen_y = world_y - gp.player.map_y + gp.player.screen_y;

        if(world_x + gp.tile_size > gp.player.map_x - gp.player.screen_x &&
                world_x - gp.tile_size < gp.player.map_x + gp.player.screen_x &&
                world_y + gp.tile_size > gp.player.map_y - gp.player.screen_y &&
                world_y - gp.tile_size < gp.player.map_y + gp.player.screen_y){
            g2.drawImage(image, screen_x, screen_y, gp.tile_size, gp.tile_size, null);
        }
    }
}
