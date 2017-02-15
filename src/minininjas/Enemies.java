
package minininjas;

import java.awt.Image;



abstract public class Enemies {
    
 public int x;
 public int y;
 public int directions;
 public Image enemyPic;
 
    
 abstract public int getX();
 abstract public int getY();
 abstract public void setX(int x);
 abstract public void setY(int y);
 abstract Image getImage();
 abstract void move();

    
    
}
