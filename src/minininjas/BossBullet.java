
package minininjas;

import Audio.AudioPlayer;
import java.awt.Image;
import javax.swing.ImageIcon;

public class BossBullet {
    private int x,y;
    private Image bulletpic;
    public boolean isVisible = true;
    public int direction;
  
    AudioPlayer bulletMusic = new AudioPlayer("/SFX/ninjaattack.mp3");
    private int w;
    private int z;
    
    public BossBullet(int sx, int sy) {
        this.x = sx;
        this.y = sy;
        ImageIcon newBullet = new ImageIcon("Resources/ICONS/enemy3.gif");
        bulletpic = newBullet.getImage();
        isVisible = true;
    }

    

    public  BossBullet(){}
    public void Move(int dir) {
        this.direction = dir;
        
        if(direction==3) this.y = this.y+2;
        if(direction==2) this.x = this.x+2;
        if(direction==1) this.y = this.y-2;
        if(direction==0) this.x = this.x-2;
        if(direction==4) {this.x =this.x+2; this.y=this.y+2;}
        if(direction==5) {this.x =this.x-2; this.y=this.y+2;}
        if(direction==6) {this.x =this.x-2; this.y=this.y-2;}
        if(direction==7) {this.x =this.x+2; this.y=this.y-2;}
        
    }
    
    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public int getW() {return this.w;}
    public int getZ() {return this.z;}
    
    public Image getImage () {
        return this.bulletpic;
    }
    public void setW(int w){
    this.w=w;
    }
    public void setZ(int z){
    this.z=z;
    }
    public void playBullet(){
      
      bulletMusic.play();
    }

    BossBullet get(int j) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    

