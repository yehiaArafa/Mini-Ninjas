
package minininjas;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Assasin1 extends Enemies {

    
    
    
  public Assasin1(){
  ImageIcon img = new ImageIcon("Resources/ICONS/enemy1.png");
  this.enemyPic = img.getImage();
    }
    
    
    
    @Override
    public int getX() {
      return this.x;       
    }

    @Override
    public int getY() {
       return this.y;
    }

    @Override
    public void setX(int x) {
       this.x=x;
    }

    @Override
    public void setY(int y) {
       this.y=y;
    }

    @Override
    Image getImage() {
       return this.enemyPic;
    }

    @Override
    void move() {
        if(this.directions==3) this.y = this.y+1;
        if(this.directions==2) this.x = this.x+1;
        if(this.directions==1) this.y = this.y-1;
        if(this.directions==0) this.x = this.x-1;
    }
    
}
