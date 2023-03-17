/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappybird;

import java.awt.Rectangle;
import java.io.File;
import pkg2dgamesframework.Objects;
import pkg2dgamesframework.SoundPlayer;

/**
 *
 * @author ACER
 */
public class Bird extends Objects{
    
    private float speed = 0;
    
    private boolean isFlyUp = false;
    
    private Rectangle rectangle;
    
    private boolean isAlive = true;
    
    public SoundPlayer flapSound, bumpSound, scoreSound;

    public Bird(int x, int y, int w, int h) {
        super(x, y, w, h);
        rectangle = new Rectangle(x, y, w, h);
        
        flapSound = new SoundPlayer(new File("Assets/flap.wav"));
        bumpSound = new SoundPlayer(new File("Assets/bump.wav"));
        scoreSound = new SoundPlayer(new File("Assets/getscore.wav"));
    }
    
    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    
    public boolean getAlive() {
        return isAlive;
    }
    
    public void update(float deltaTime) {
        speed += FlappyBird.gravity;
        
        this.setPosY(this.getPosY() + speed);
        this.rectangle.setLocation((int) this.getPosX(), (int) this.getPosY());
        
        if (speed < 0) {
            isFlyUp = true;
        } else {
            isFlyUp = false;
        }
    }
    
    public void fly() {
        speed = -3;
        flapSound.play();
    }
    
    public void noFly() {
        speed = 11;
    }
    
    public boolean getIsFlyUp() {
        return isFlyUp;
    }
    
    public Rectangle getRectangle() {
        return rectangle;
    }
}
