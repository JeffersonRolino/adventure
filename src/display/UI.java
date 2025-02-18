package display;

import main.GamePanel;
import objects.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class UI {
    GamePanel gamePanel;
    Font arial, arial_80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
    DecimalFormat decimalFormat = new DecimalFormat("#0.00", symbols);

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.image;
    }

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D graphics2D){
        if(gameFinished){
            graphics2D.setFont(arial);
            graphics2D.setColor(Color.white);

            String text;
            int textLenght;
            int x;
            int y;

            text = "You found the treasure!";
            textLenght = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth / 2 - textLenght / 2;
            y = gamePanel.screenHeight / 2 - (gamePanel.tileSize * 3);
            graphics2D.drawString(text, x, y);

            graphics2D.setFont(arial_80B);
            graphics2D.setColor(Color.yellow);

            text = "Congratulations!";
            textLenght = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth / 2 - textLenght / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize * 2);
            graphics2D.drawString(text, x, y);

            graphics2D.setFont(arial);
            graphics2D.setColor(Color.white);

            text = "Your Time is: " + decimalFormat.format(playTime) + "!";
            textLenght = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
            x = gamePanel.screenWidth / 2 - textLenght / 2;
            y = gamePanel.screenHeight / 2 + (gamePanel.tileSize * 4);
            graphics2D.drawString(text, x, y);

            gamePanel.gameThread = null;
        }
        else {
            graphics2D.setFont(arial);
            graphics2D.setColor(Color.white);
            graphics2D.drawImage(keyImage, gamePanel.tileSize / 2, gamePanel.tileSize / 2, gamePanel.tileSize, gamePanel.tileSize, null);
            graphics2D.drawString("x " + gamePanel.player.hasKey, 74, 65);

            //TIME
            playTime += (double)1/60;
            graphics2D.drawString("Time: " + decimalFormat.format(playTime), gamePanel.tileSize * 11, 65);

            //MESSAGE
            if(messageOn){
                graphics2D.setFont(graphics2D.getFont().deriveFont(30F));
                graphics2D.drawString(message, gamePanel.tileSize / 2, gamePanel.tileSize * 5);

                messageCounter++;

                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
