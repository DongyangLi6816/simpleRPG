package OBJ;

import javax.imageio.ImageIO;
import java.io.IOException;

public class game_boots extends game_object{
    public game_boots(){
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Object/boots.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
