package minininjas;

import Audio.AudioPlayer;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bullet {
    private int x,y;
    private Image bulletpic;
    public boolean isVisible = true;
    public int direction;
    
    public Bullet(int sx, int sy) {
        this.x = sx;
        this.y = sy;
        ImageIcon newBullet = new ImageIcon("Resources/ICONS/Bullet.png");
        bulletpic = newBullet.getImage();
        isVisible = true;
    }
    
    public void Move() {
        if(direction==3) this.y = this.y+2;
        if(direction==2) this.x = this.x+2;
        if(direction==1) this.y = this.y-2;
        if(direction==0) this.x = this.x-2;
    }
    
    public int getX() {return this.x;}
    public int getY() {return this.y;}
    
    public Image getImage () {
        return this.bulletpic;
    }
}
