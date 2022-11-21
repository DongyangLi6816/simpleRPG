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
    public int checkCollision_obj(Entity entity, boolean is_player){
        int index = Integer.MAX_VALUE;
        for (int i  = 0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                // get entity's solid area
                entity.solid_area.x = entity.map_x + entity.solid_area.x;
                entity.solid_area.y = entity.map_y + entity.solid_area.y;
                // object's solid area
                gp.obj[i].solid_area.x = gp.obj[i].world_x +gp.obj[i].solid_area.x;
                gp.obj[i].solid_area.y = gp.obj[i].world_y +gp.obj[i].solid_area.y;

                switch(entity.direction) {
                    case "up":
                        entity.solid_area.y -= entity.speed;
                        if (entity.solid_area.intersects(gp.obj[i].solid_area)) {
                            if(gp.obj[i].collision){
                                entity.collision_on = true;
                            }
                            if(is_player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solid_area.y += entity.speed;
                        if (entity.solid_area.intersects(gp.obj[i].solid_area)) {
                            if(gp.obj[i].collision){
                                entity.collision_on = true;
                            }
                            if(is_player){
                                index = i;
                            }
                        }
                    case "left":
                        entity.solid_area.x -= entity.speed;
                        if (entity.solid_area.intersects(gp.obj[i].solid_area)) {
                            if(gp.obj[i].collision){
                                entity.collision_on = true;
                            }
                            if(is_player){
                                index = i;
                            }
                        }
                    case "right":
                        entity.solid_area.x += entity.speed;
                        if (entity.solid_area.intersects(gp.obj[i].solid_area)) {
                            if(gp.obj[i].collision){
                                entity.collision_on = true;
                            }
                            if(is_player){
                                index = i;
                            }
                        }
                }
                entity.solid_area.x = entity.default_solid_area_x;
                entity.solid_area.y = entity.default_solid_area_y;
                gp.obj[i].solid_area.x = gp.obj[i].default_solid_area_x;
                gp.obj[i].solid_area.y = gp.obj[i].default_solid_area_y;
            }
        }
        return index;
    }
}
