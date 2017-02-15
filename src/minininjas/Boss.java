package minininjas;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class Boss {
 private BufferedImage boss;
 public int health = 100;
 int x,y;
 private ArrayList bulletsboss=new ArrayList();
    public Image getPlayerimg(int x,int y) {
        try {
            boss = ImageIO.read(new File("Resources/ICONS/enemyboss1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return boss.getSubimage(y*150, 0, 150, 100);
    }
    public void Fire(int dir) {
        BossBullet b = new BossBullet(350,350);
        b.direction=dir;
        bulletsboss.add(b);
    }
    
    public ArrayList getBullets () {
        return bulletsboss;
    }
    
    
}
