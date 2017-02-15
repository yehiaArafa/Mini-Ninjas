package minininjas;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class PlayerSprite {

    private Image Ninja;
    private BufferedImage ninjaboy;
    private int x, y, tilex, tiley;
    private ArrayList bullets;
    private ArrayList bullets2;
    public int health = 3;
    Map map;
       
        
    public PlayerSprite() throws FileNotFoundException {
        
        map = new Map("map01.txt");
        this.x = 25;
        this.y = 50;

        tilex = 1;
        tiley = 2;

        bullets = new ArrayList<>();
        bullets2 = new ArrayList<>();
    }

    public void Fire(int dir,int typ) {
        if(typ==1) {
            Bullet b = new Bullet(tilex * 25, tiley * 25);
            b.direction = dir;
            bullets.add(b);
        } else if(typ==2) {
            Bullet2 b2 = new Bullet2(tilex * 25, tiley * 25);
            b2.direction = dir;
            bullets2.add(b2);
        }
        
        
    }

    public ArrayList getBullets() {
        return bullets;
    }
    
    public ArrayList getBullets2() {
        return bullets2;
    }

    public void Move(int dx, int dy) {
        tilex += dx;
        tiley += dy;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getTileX() {
        return tilex;
    }

    public int getTileY() {
        return tiley;
    }

    public void setX(int x) {
        this.tilex = x;
    }

    public void setY(int y) {
        this.tiley = y;
    }

    public Image getPlayerimg(int x, int y) {
        try {
            ninjaboy = ImageIO.read(new File("Resources/ICONS/ninja.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ninjaboy.getSubimage(y * 30, x * 30, 28, 28);
    }
}
