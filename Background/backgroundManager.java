package Background;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class backgroundManager {
    GamePanel gp;
    public background[] backgrounds;
    public int imported_map[][];

    String path_map;

    public backgroundManager(GamePanel gp) {
        this.gp = gp;
        backgrounds = new background[10];
        imported_map = new int[gp.world_max_col][gp.world_max_row];

        getBackgroundImage();
        path_map = "res/map/world01.txt";
        loadMap(path_map);
    }

    public void getBackgroundImage(){
        try {
            backgrounds[0] = new background();
            backgrounds[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/background/grass.png"));

            backgrounds[1] = new background();
            backgrounds[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/background/wall.png"));
            backgrounds[1].collision = true;

            backgrounds[2] = new background();
            backgrounds[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/background/water.png"));
            backgrounds[2].collision = true;

            backgrounds[3] = new background();
            backgrounds[3].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/background/earth.png"));

            backgrounds[4] = new background();
            backgrounds[4].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/background/tree.png"));
            backgrounds[4].collision = true;

            backgrounds[5] = new background();
            backgrounds[5].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/background/sand.png"));


        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String path_map) {
        try {
            System.out.println("start loading map");
            InputStream in = getClass().getClassLoader().getResourceAsStream(path_map);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int col = 0;
            int row = 0;

            while (col < gp.world_max_col && row < gp.world_max_row) {
                String map_line = br.readLine(); // read a line of map

                while (col < gp.world_max_col) {
                    String numbers_map[] = map_line.split(" "); //use regular expression to split a line

                    int num = Integer.parseInt(numbers_map[col]);

                    imported_map[col][row] = num;
                    col++;
                }
                if (col == gp.world_max_col) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }
        System.out.println("map successfully loaded");

    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;

        while(col < gp.world_max_col && row < gp.world_max_row){

            int num_map = imported_map[col][row];

            int world_x = col * gp.tile_size;
            int world_y = row * gp.tile_size;
            int screen_x = world_x - gp.player.map_x + gp.player.screen_x;
            int screen_y = world_y - gp.player.map_y + gp.player.screen_y;

            if(world_x + gp.tile_size > gp.player.map_x - gp.player.screen_x &&
                    world_x - gp.tile_size < gp.player.map_x + gp.player.screen_x &&
                    world_y + gp.tile_size > gp.player.map_y - gp.player.screen_y &&
                    world_y - gp.tile_size < gp.player.map_y + gp.player.screen_y){
                g2.drawImage(backgrounds[num_map].image, screen_x, screen_y, gp.tile_size, gp.tile_size, null);
            }
            col++;

            if(col == gp.world_max_col){
                col = 0;
                row++;
            }
        }
    }
}
