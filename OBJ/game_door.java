package OBJ;

import javax.imageio.ImageIO;
import java.io.IOException;

public class game_door extends game_object{
    public game_door(){
        name = "Door";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Object/door.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
