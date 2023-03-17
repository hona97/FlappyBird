/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappybird;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ACER
 */
public class BackGround {

    private BufferedImage backGroundImage;

    private int x1, y1, x2, y2;

    public BackGround() {
        try {
            backGroundImage = ImageIO.read(new File("Assets/background.png"));
        } catch (IOException ex) {
        }

        x1 = 0;
        y1 = 0;
        x2 = x1 + 900;
        y2 = 0;
    }

    public void painBackGround(Graphics2D graphics2d) {
        graphics2d.drawImage(backGroundImage, x1, y1, null);
        graphics2d.drawImage(backGroundImage, x2, y2, null);
    }

    public void updateBackGround() {
        x1 -= 2;
        x2 -= 2;

        if (x2 < 0) {
            x1 = x2 + 900;
        }
        if (x1 < 0) {
            x2 = x1 + 900;
        }
    }

}
