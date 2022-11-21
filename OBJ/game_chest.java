package OBJ;

import javax.imageio.ImageIO;
import java.io.IOException;

public class game_chest extends game_object{
    public game_chest(){
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Object/chest.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
