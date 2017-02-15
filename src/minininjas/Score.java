package minininjas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Score {

    private static int score = 0;
    Image health;
    ImageIcon img;
    int healthcounter = 4;
    String ninjaHealth="";
    private String times="";
    public String bHealth="";
    public int ninjHcounter=50;
    private boolean flag=false;

    public Score() {

        img = new ImageIcon(getClass().getResource("/ICONS/heart.png"));
        health = img.getImage();

    }

    public void setTime(int temp){
    this.times=Integer.toString(temp);
      
    }
     public void setbossHealth(int bHealth,boolean flag){
         this.bHealth=Integer.toString(bHealth);
         this.flag=flag;
     }
     
    public void setNinjaHealth(int nHealth){
      this.ninjaHealth=Integer.toString(nHealth);
     }
   
    
    public void setScore(String cases) {

        switch (cases) {
            case "A":
                score += 50;
                break;
            case "B":
                score += 100;
                break;
            case "C":
                score += 10;
                break;
            case "D":
                score += 200;
                break;
            case "F":
                score += 250;
                break;
            case "E":
                score += 850;
                break;
            default:
                
                float time=Integer.parseInt(cases);
                float temp = Math.round((1/time) *1000);         
                score+=(int)temp;
                break;

        }

    }

    public int getScore() {
        return this.score;
    }

    public int getNinjaBHealth(){
      return this. ninjHcounter;
    }
    public void display(Graphics g) {
        
       if(flag){
        
         
           
           g.setColor(Color.white);
          Font fnt1 = new Font("arial", Font.BOLD, 25);
         g.setFont(fnt1);
         
         
         g.drawString("Ninja Health :",10, 780);
         g.drawString(Integer.toString(getNinjaBHealth()), 180, 780);
         g.drawString("Boss Health : ",300, 780);
        g.drawString(bHealth, 470, 780);
       }
       else{
        
        g.setColor(Color.white);
        
          Font fnt1 = new Font("arial", Font.BOLD, 25);
          g.setFont(fnt1);
        
        g.drawString("Score", 10, 780);
        
        g.drawString(Integer.toString(getScore()), 100, 780);

        g.drawString("Health", 170, 780);
       
          int x = 230;
        for (int i = 0; i < healthcounter; i++) {
            g.drawImage(health, x += 30, 760, null);
 
        }

        g.drawString("Time : ", 400, 780);
        
        g.drawString(this.times,480, 780);
       
       }   

}
}