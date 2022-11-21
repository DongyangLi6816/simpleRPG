package main;

import Entity.Entity;

public class CollisionCheck {
    GamePanel gp;

    public CollisionCheck(GamePanel gp){
        this.gp = gp;
    }

    public void checkCollision(Entity entity){
        int left_collision_x = entity.map_x + entity.solid_area.x;
        int right_collision_x = entity.map_x + entity.solid_area.x + entity.solid_area.width;
        int up_collision_y = entity.map_y + entity.solid_area.y;
        int down_collision_y = entity.map_y + entity.solid_area.y + entity.solid_area.height;

        int left_col = left_collision_x/gp.tile_size;
        int right_col = right_collision_x/gp.tile_size;
        int up_row = up_collision_y/gp.tile_size;
        int down_row = down_collision_y/gp.tile_size;

        int background_1, background_2;

        switch (entity.direction){
            case "up":
                up_row = (up_collision_y - entity.speed)/gp.tile_size;
                background_1 = gp.background.imported_map[left_col][up_row];
                background_2 = gp.background.imported_map[right_col][up_row];
                if(gp.background.backgrounds[background_1].collision || gp.background.backgrounds[background_2].collision){
                    entity.collision_on = true;
                }
                break;
            case "down":
                down_row = (down_collision_y + entity.speed)/gp.tile_size;
                background_1 = gp.background.imported_map[left_col][down_row];
                background_2 = gp.background.imported_map[right_col][down_row];
                if(gp.background.backgrounds[background_1].collision || gp.background.backgrounds[background_2].collision){
                    entity.collision_on = true;
                }
                break;
            case "left":
                left_col = (left_collision_x - entity.speed)/gp.tile_size;
                background_1 = gp.background.imported_map[left_col][up_row];
                background_2 = gp.background.imported_map[left_col][down_row];
                if(gp.background.backgrounds[background_1].collision || gp.background.backgrounds[background_2].collision){
                    entity.collision_on = true;
                }
                break;
            case "right":
                right_col = (right_collision_x + entity.speed)/gp.tile_size;
                background_1 = gp.background.imported_map[right_col][up_row];
                background_2 = gp.background.imported_map[right_col][down_row];
                if(gp.background.backgrounds[background_1].collision || gp.background.backgrounds[background_2].collision){
                    entity.collision_on = true;
                }
                break;
        }

    }
}
