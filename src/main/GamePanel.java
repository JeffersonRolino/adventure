package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile size
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 pixels
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    Thread gameThread;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while(gameThread != null){
//            System.out.println("The game loop is running...");
            // 1. UPDATE: Update information such as character positions
            update();

            // 2. DRAW: draw the screen with the updated information
            repaint();
        }
    }

    public void update(){

    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;

        graphics2D.setColor(Color.white);

        //Draw Rectangle
        graphics2D.fillRect(100, 100, tileSize, tileSize);

        //Dispose of this graphics context and release any system resources using it.
        graphics2D.dispose();
    }
}
