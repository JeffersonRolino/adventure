package tiles;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new Tile[10];
        getTileImage();
    }

    public void getTileImage(){
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass.png")));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water.png")));

        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.drawImage(tiles[0].image, 0, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawImage(tiles[1].image, 48, 0, gamePanel.tileSize, gamePanel.tileSize, null);
        graphics2D.drawImage(tiles[2].image, 96, 0, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
