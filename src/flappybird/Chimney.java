/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappybird;

import java.awt.Rectangle;
import pkg2dgamesframework.Objects;

/**
 *
 * @author ACER
 */
public class Chimney extends Objects {

    private Rectangle rectangle;
    
    private boolean isBirdPass = false;

    public Chimney(int x, int y, int w, int h) {
        super(x, y, w, h);
        rectangle = new Rectangle(x, y, w, h);
    }

    public void updateChimney() {
        setPosX(getPosX() - 2);
        this.rectangle.setLocation((int) this.getPosX(), (int) this.getPosY());
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
    
    public void setIsBirdPass(boolean pass) {
        isBirdPass = pass;
    }
    
    public boolean getIsBirdPass() {
        return isBirdPass;
    }
}
