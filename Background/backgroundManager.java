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
    background[] backgrounds;
    int imported_map[][];

    String path_map;

    public backgroundManager(GamePanel gp) {
        this.gp = gp;
        backgrounds = new background[10];
        imported_map = new int[gp.max_screenCol][gp.max_screenRow];

        getBackgroundImage();
        path_map = "res/map/map001.txt";
        loadMap(path_map);
    }

    public void getBackgroundImage(){
        try {
            backgrounds[0] = new background();
            backgrounds[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/background/grass.png"));

            backgrounds[1] = new background();
            backgrounds[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/background/wall.png"));

            backgrounds[2] = new background();
            backgrounds[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/background/water.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String path_map) {
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream(path_map);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int col = 0;
            int row = 0;

            while (col < gp.max_screenCol && row < gp.max_screenRow) {
                String map_line = br.readLine(); // read a line of map

                while (col < gp.max_screenCol) {
                    String numbers_map[] = map_line.split(" "); //use regular expression to split a line

                    int num = Integer.parseInt(numbers_map[col]);

                    imported_map[col][row] = num;
                    col++;
                }
                if (col == gp.max_screenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){

        }

    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.max_screenCol && row < gp.max_screenRow){
            int num_map = imported_map[col][row];
            g2.drawImage(backgrounds[num_map].image, x, y, gp.tile_size, gp.tile_size, null);
            col++;
            x+= gp.tile_size;

            if(col == gp.max_screenCol){
                col = 0;
                x = 0;
                row++;
                y+=gp.tile_size;
            }
        }
    }
}
