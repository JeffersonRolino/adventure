package main;

import entities.Player;
import objects.SuperObject;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile size
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 pixels
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    // FPS
    final int FPS = 60;

    //SYSTEM AND MANAGERS
    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Sound sound = new Sound();
    public CollisionDetector collisionDetector = new CollisionDetector(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    Thread gameThread;


    //ENTITY AND OBJECTS
    public Player player = new Player(this, keyHandler);
    public SuperObject[] obj = new SuperObject[10];

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        assetSetter.setObject();
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; // 0.016666 seconds
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime();

            deltaTime += (currentTime - lastTime) / drawInterval;
//            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(deltaTime >= 1){
                // 1. UPDATE: Update information such as character positions
                update();
                // 2. DRAW: draw the screen with the updated information
                repaint();

                deltaTime--;
                drawCount++;
            }

//            if(timer >= 1000000000){
//                System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;

        //Drawing tile first for rendering order purposes...
        tileManager.draw(graphics2D);

        //Object
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(graphics2D, this);
            }
        }

        //Player
        player.draw(graphics2D);

        //Dispose of this graphics context and release any system resources using it.
        graphics2D.dispose();
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSoundEffect(int i){
        sound.setFile(i);
        sound.play();
    }
}
