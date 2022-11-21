package main;

import OBJ.game_chest;
import OBJ.game_door;
import OBJ.game_key;

public class object_setter {
    GamePanel gp;
    public object_setter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new game_key();
        gp.obj[0].world_x = 23*gp.tile_size;
        gp.obj[0].world_y = 7 * gp.tile_size;

        gp.obj[1] = new game_key();
        gp.obj[1].world_x = 23*gp.tile_size;
        gp.obj[1].world_y = 40*gp.tile_size;

        gp.obj[2] = new game_key();
        gp.obj[2].world_x = 38*gp.tile_size;
        gp.obj[2].world_y = 8*gp.tile_size;

        gp.obj[3] = new game_door();
        gp.obj[3].world_x = 10*gp.tile_size;
        gp.obj[3].world_y = 11*gp.tile_size;

        gp.obj[4] = new game_door();
        gp.obj[4].world_x = 8*gp.tile_size;
        gp.obj[4].world_y = 28*gp.tile_size;
        //12 22 10 7
        gp.obj[5] = new game_door();
        gp.obj[5].world_x = 12*gp.tile_size;
        gp.obj[5].world_y = 22*gp.tile_size;

        gp.obj[6] = new game_chest();
        gp.obj[6].world_x = 10*gp.tile_size;
        gp.obj[6].world_y = 7*gp.tile_size;
    }
}
