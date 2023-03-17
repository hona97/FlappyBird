/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappybird;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import pkg2dgamesframework.QueueList;

/**
 *
 * @author ACER
 */
public class ChimneyGroup {

    private QueueList<Chimney> chimneys;

    private BufferedImage chimneyImage1, chimneyImage2;
    
    public static int SIZE = 6;
    
    private int topChimney = -350;
    private int bottomChimney = 200;

    public ChimneyGroup() {
        try {
            chimneyImage1 = ImageIO.read(new File("Assets/chimney.png"));
            chimneyImage2 = ImageIO.read(new File("Assets/chimney_.png"));
        } catch (IOException ex) {
        }

        chimneys = new QueueList<Chimney>();

        Chimney chimney;

        for (int i = 0; i < SIZE/2; ++i) {
            int deltaY = getRandomY();
            chimney = new Chimney(830 + i * 300, bottomChimney + deltaY, 74, 400);
            chimneys.push(chimney);

            chimney = new Chimney(830 + i * 300, topChimney + deltaY, 74, 400);
            chimneys.push(chimney);
        }
    }
    
    public void resetChimney() {
        chimneys = new QueueList<Chimney>();

        Chimney chimney;

        for (int i = 0; i < SIZE/2; ++i) {
            int deltaY = getRandomY();
            chimney = new Chimney(830 + i * 300, bottomChimney + deltaY, 74, 400);
            chimneys.push(chimney);

            chimney = new Chimney(830 + i * 300, topChimney + deltaY, 74, 400);
            chimneys.push(chimney);
        }
    }

    public void updateChimneyGroup() {
        for (int i = 0; i < SIZE; ++i) {
            chimneys.get(i).updateChimney();
        }

        if (chimneys.get(0).getPosX() < -74) {
            
            int deltaY = getRandomY();
                    
            Chimney chimneyTemp;
            chimneyTemp = chimneys.pop();
            chimneyTemp.setPosX(chimneys.get(4).getPosX() + 300);
            chimneyTemp.setPosY(bottomChimney + deltaY);
            chimneyTemp.setIsBirdPass(false);
            chimneys.push(chimneyTemp);
            
            chimneyTemp = chimneys.pop();
            chimneyTemp.setPosX(chimneys.get(4).getPosX());
            chimneyTemp.setPosY(topChimney + deltaY);
            chimneyTemp.setIsBirdPass(false);
            chimneys.push(chimneyTemp);
        }
    }

    public void painChimneyGroup(Graphics2D graphics2d) {
        for (int i = 0; i < 6; ++i) {
            if (i % 2 == 0) {
                graphics2d.drawImage(chimneyImage1, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);
            } else {
                graphics2d.drawImage(chimneyImage2, (int) chimneys.get(i).getPosX(), (int) chimneys.get(i).getPosY(), null);

            }
        }
    }
    
    public Chimney getChimney(int i) {
        return chimneys.get(i);
    }
    
    public int getRandomY() {
        Random random = new Random();
        int a;
        a = random.nextInt(10);
        return a*35;
    }
}
