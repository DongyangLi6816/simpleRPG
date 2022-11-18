import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Screen setting
    final int original_tileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tile_size = original_tileSize * scale; // 48x48
    final int max_screenCol = 16;
    final int max_screenRow = 12;
    final int screen_width = tile_size * max_screenCol; // 768
    final int screen_height = tile_size * max_screenRow; // 576

    Thread game_thread;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // improve rendering performance
    }

    public void startGameThread() {
        game_thread = new Thread(this);
        game_thread.start(); //auto call run()
    }
    @Override
    public void run() {
        while(game_thread != null){
            //System.out.println("The game loop is running");
            // update info as character position
            // draw the screen with updated info
        }
    }
}
