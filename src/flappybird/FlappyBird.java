/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package flappybird;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pkg2dgamesframework.AFrameOnImage;
import pkg2dgamesframework.Animation;
import pkg2dgamesframework.GameScreen;


/**
 *
 * @author ACER
 */
public class FlappyBird extends GameScreen{

    /**
     * @param args the command line arguments
     */
    
    private BufferedImage birds;
    private Animation bird_animation;

    
    public static float gravity = 0.15f;
    
    private Bird bird;
    private Ground ground;
    private BackGround backGround;
    
    private ChimneyGroup chimneyGroup;
    
    private int Score = 0;
    
    private int beginScreen = 0;
    private int playScreen = 1;
    private int overScreen = 2;
    private int currentScreen = beginScreen;
    
    public FlappyBird() {
        super(800, 600);
        
        try {
            birds = ImageIO.read(new File("Assets/bird_sprite.png"));
            
        } catch (IOException ex) {}
        
        bird_animation = new Animation(70);
        AFrameOnImage frame;
        frame = new AFrameOnImage(0, 0, 60, 60);
        bird_animation.AddFrame(frame);
        frame = new AFrameOnImage(60, 0, 60, 60);
        bird_animation.AddFrame(frame);
        frame = new AFrameOnImage(120, 0, 60, 60);
        bird_animation.AddFrame(frame);
        frame = new AFrameOnImage(60, 0, 60, 60);
        bird_animation.AddFrame(frame);
        
        backGround = new BackGround();
        bird = new Bird(350, 250, 50, 50);
        ground = new Ground();
        
        chimneyGroup = new ChimneyGroup();
        
        BeginGame();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new FlappyBird();
    }
    
    private void resetGame() {
        bird.setPos(350, 250);
        bird.setSpeed(-0.5f);
        bird.setAlive(true);
        Score = 0;
        chimneyGroup.resetChimney(); 
    }

    @Override
    public void GAME_UPDATE(long deltaTime) {
        
        if (currentScreen == beginScreen) {
            resetGame();
        } else if (currentScreen == playScreen) { 
            if (bird.getAlive()) {
                bird_animation.Update_Me(deltaTime);
            } else {
                bird.noFly();     
            }        
            backGround.updateBackGround();
            bird.update(deltaTime);
            ground.updateGround();
            
            
            chimneyGroup.updateChimneyGroup();
            
            for (int i = 0; i < ChimneyGroup.SIZE; ++i) {
                if (bird.getRectangle().intersects(chimneyGroup.getChimney(i).getRectangle())) {
                    if (bird.getAlive()) bird.bumpSound.play();
                    bird.setAlive(false);
                    System.out.println("false");
                }
            }
            
            for (int i = 0; i < ChimneyGroup.SIZE; ++i) {
                if (bird.getPosX() > chimneyGroup.getChimney(i).getPosX() && !chimneyGroup.getChimney(i).getIsBirdPass()
                    && i% 2 == 0) {
                    ++Score;
                    bird.scoreSound.play();
                    chimneyGroup.getChimney(i).setIsBirdPass(true);
                }
            }
            
            if (bird.getPosY() + bird.getH() >= ground.getYGround()) {
                currentScreen = overScreen;
            }
        } else {
            
        }  
        
    }

    @Override
    public void GAME_PAINT(Graphics2D graphics2d) {
        
        //graphics2d.setColor(Color.decode("#b8daef"));        
        //graphics2d.fillRect(0, 0, MASTER_WIDTH, MASTER_HEIGHT);
        backGround.painBackGround(graphics2d);
        chimneyGroup.painChimneyGroup(graphics2d);
      
        
        ground.painGround(graphics2d);
                
        if (bird.getIsFlyUp()) {
            bird_animation.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, graphics2d, 0, -0.5f);
        } else {
            bird_animation.PaintAnims((int) bird.getPosX(), (int) bird.getPosY(), birds, graphics2d, 0, 0);
        }       
        
        if (currentScreen == beginScreen) {
            graphics2d.setFont(new Font("Arial", Font.BOLD, 15));
            graphics2d.setColor(Color.red);
            graphics2d.drawString("Press space button to play game", 300, 400);
        }
        if (currentScreen == overScreen) {
            graphics2d.setFont(new Font("Arial", Font.BOLD, 15));
            graphics2d.setColor(Color.red);
            graphics2d.drawString("Press space button to begin again", 300, 250);
        }
        graphics2d.setFont(new Font("Arial", Font.BOLD, 20));
        graphics2d.setColor(Color.white);
        graphics2d.drawString("Score: " + Score, 20, 50);
    }

    @Override
    public void KEY_ACTION(KeyEvent e, int Event) {
        if (Event == KEY_PRESSED) {
            
            if (currentScreen == beginScreen) {
                currentScreen = playScreen;
            } else if (currentScreen == playScreen) {
                if (bird.getAlive()) bird.fly();
            } else if (currentScreen == overScreen) {
                currentScreen = beginScreen;
            }
                      
        }
    }
    
}
