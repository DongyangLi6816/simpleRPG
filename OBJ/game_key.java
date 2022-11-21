package OBJ;

import javax.imageio.ImageIO;
import java.io.IOException;

public class game_key extends game_object {
    public game_key(){
        name = "Key";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("res/Object/key.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
