package minininjas;

import Audio.AudioPlayer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class GamePlay extends JPanel {

    public int typeofwep = 1;
    AudioPlayer backgroundMusic = new AudioPlayer("/MUSIC/background.mp3");
    AudioPlayer Hamada = new AudioPlayer("/MUSIC/Hamada.mp3");
    AudioPlayer Moratada = new AudioPlayer("/MUSIC/Mortada.mp3");
    AudioPlayer bulletMusic = new AudioPlayer("/SFX/ninjaattack.mp3");
    AudioPlayer bosss = new AudioPlayer("/MUSIC/boss.mp3");
    AudioPlayer ninjaHit = new AudioPlayer("/SFX/ninjahit.mp3");
    AudioPlayer win = new AudioPlayer("/MUSIC/fanfare.mp3");
    AudioPlayer enemyHit = new AudioPlayer("/SFX/enemyhit.mp3");
    AudioPlayer explode = new AudioPlayer("/SFX/explode.mp3");
    AudioPlayer lost = new AudioPlayer("/SFX/lost.mp3");

    private int arraylength;
    Map map;
    Boss boss = new Boss();
    Image wall, grass, path, finishLine, bomb, present, diamond, heart;
    ImageIcon img;
    public PlayerSprite ninjaboy;
    public ArrayList Enemies = new ArrayList<>();
    public int direction = 2;
    private Timer timer, timer2, timer3, timer4, timer5, timer6, timer7, gameTimer, timer8;
    Score score;
    boolean dFlag = true;
    int frame = 2;
    int x;
    int y;
    public int tmph = 800;
    CreateEnemies creatEnemies = new CreateEnemies();
    public int counter = 0;
    private int timeCounter = 0;
    boolean isHamada = false;
    String mapName;
    int mapCounter = 0;
    boolean isBoss = false;
    int specialweapon = 10;
    boolean winnerFlag = false;
    boolean looserFlag = false;

    public GamePlay() throws FileNotFoundException {
        playMusic();
        creatEnemies.create();
        gameTimer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                timeCounter++;
                score.setTime(timeCounter);
            }

        });

        gameTimer.start();

        boss.getPlayerimg(2, 2);
        this.setBackground(Color.BLACK);
        loadMap();
        loadImages();
        ninjaboy = new PlayerSprite();
        score = new Score();
        this.setFocusable(true);
        timer = new Timer(5, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList bullets = ninjaboy.getBullets();
                for (int j = 0; j < bullets.size(); j++) {
                    Bullet B = (Bullet) bullets.get(j);

                    if ((B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("w"))
                            || (B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("f"))) {
                        B.isVisible = false;
                        bullets.remove(j);
                    } else if ((B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("d"))
                            || (B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("b"))
                            || (B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("r"))
                            || (B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("h"))) {
                        B.isVisible = false;
                        bullets.remove(j);
                        map.setMap((B.getX() / 25), (B.getY() / 25), "g");
                        score.setScore("C");

                    } else if ((B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("w"))
                            || (B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("f"))) {
                        B.isVisible = false;
                        bullets.remove(j);
                    } else if ((B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("d"))
                            || (B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("b"))
                            || (B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("r"))
                            || (B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("h"))) {
                        B.isVisible = false;
                        bullets.remove(j);
                        map.setMap((B.getX() / 25), (B.getY() / 25), "g");
                        score.setScore("C");

                    } else if ((B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("w"))
                            || (B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("f"))) {
                        B.isVisible = false;
                        bullets.remove(j);
                    } else if ((B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("d"))
                            || (B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("b"))
                            || (B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("r"))
                            || (B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("h"))) {
                        B.isVisible = false;
                        bullets.remove(j);
                        map.setMap(((B.getX() / 25) + 1), (B.getY() / 25), "g");
                        score.setScore("C");

                    } else if ((B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("w"))
                            || (B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("f"))) {
                        B.isVisible = false;
                        bullets.remove(j);
                    } else if ((B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("d"))
                            || (B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("b"))
                            || (B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("r"))
                            || (B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("h"))) {
                        B.isVisible = false;
                        bullets.remove(j);
                        map.setMap((B.getX() / 25), ((B.getY() / 25) + 1), "g");
                        score.setScore("C");

                    } else {
                        B.Move();
                    }
                }
                ArrayList bullets2 = ninjaboy.getBullets2();
                for (int j = 0; j < bullets2.size(); j++) {
                    Bullet2 B = (Bullet2) bullets2.get(j);

                    if ((B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("w"))
                            || (B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("f"))) {
                        B.isVisible = false;
                        bullets2.remove(j);
                    } else if ((B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("d"))
                            || (B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("b"))
                            || (B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("r"))
                            || (B.direction == 0 && map.getMap((B.getX() / 25), B.getY() / 25).equals("h"))) {
                        B.isVisible = false;
                        bullets2.remove(j);
                        map.setMap((B.getX() / 25), (B.getY() / 25), "g");
                        score.setScore("C");

                    } else if ((B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("w"))
                            || (B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("f"))) {
                        B.isVisible = false;
                        bullets2.remove(j);
                    } else if ((B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("d"))
                            || (B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("b"))
                            || (B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("r"))
                            || (B.direction == 1 && map.getMap((B.getX() / 25), B.getY() / 25).equals("h"))) {
                        B.isVisible = false;
                        bullets2.remove(j);
                        map.setMap((B.getX() / 25), (B.getY() / 25), "g");
                        score.setScore("C");

                    } else if ((B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("w"))
                            || (B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("f"))) {
                        B.isVisible = false;
                        bullets2.remove(j);
                    } else if ((B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("d"))
                            || (B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("b"))
                            || (B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("r"))
                            || (B.direction == 2 && map.getMap((B.getX() / 25) + 1, B.getY() / 25).equals("h"))) {
                        B.isVisible = false;
                        bullets2.remove(j);
                        map.setMap(((B.getX() / 25) + 1), (B.getY() / 25), "g");
                        score.setScore("C");

                    } else if ((B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("w"))
                            || (B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("f"))) {
                        B.isVisible = false;
                        bullets2.remove(j);
                    } else if ((B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("d"))
                            || (B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("b"))
                            || (B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("r"))
                            || (B.direction == 3 && map.getMap(B.getX() / 25, (B.getY() / 25) + 1).equals("h"))) {
                        B.isVisible = false;
                        bullets2.remove(j);
                        map.setMap((B.getX() / 25), ((B.getY() / 25) + 1), "g");
                        score.setScore("C");

                    } else {
                        B.Move();
                    }
                }
                repaint();
            }
        });
        timer.start();
        timer2 = new Timer(5, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand2 = new Random();
                Boolean checkdir = false;
                for (int k = 0; k < creatEnemies.Enemies.size(); k++) {
                    Enemies E = (Enemies) creatEnemies.Enemies.get(k);
                    if (((map.getMap(E.getX() / 25, E.getY() / 25).equals("w")) && E.directions == 1)
                            || ((map.getMap(E.getX() / 25, E.getY() / 25).equals("d")) && E.directions == 1)
                            || ((map.getMap(E.getX() / 25, E.getY() / 25).equals("f")) && E.directions == 1)) {
                        while (E.directions == 1) {
                            E.directions = rand2.nextInt(4);
                        }
                    } else if (((map.getMap(E.getX() / 25 + 1, E.getY() / 25).equals("w")) && E.directions == 2)
                            || ((map.getMap(E.getX() / 25 + 1, E.getY() / 25).equals("d")) && E.directions == 2)
                            || ((map.getMap(E.getX() / 25 + 1, E.getY() / 25).equals("f")) && E.directions == 2)) {

                        while (E.directions == 2) {
                            E.directions = rand2.nextInt(4);
                        }
                    } else if (((map.getMap(E.getX() / 25, E.getY() / 25 + 1).equals("w")) && E.directions == 3)
                            || ((map.getMap(E.getX() / 25, E.getY() / 25 + 1).equals("d")) && E.directions == 3)
                            || ((map.getMap(E.getX() / 25, E.getY() / 25 + 1).equals("f")) && E.directions == 3)) {
                        while (E.directions == 3) {
                            E.directions = rand2.nextInt(4);
                        }
                    } else if (((map.getMap(E.getX() / 25, E.getY() / 25).equals("w")) && E.directions == 0)
                            || ((map.getMap(E.getX() / 25, E.getY() / 25).equals("d")) && E.directions == 0)
                            || ((map.getMap(E.getX() / 25, E.getY() / 25).equals("f")) && E.directions == 0)) {
                        while (E.directions == 0) {
                            E.directions = rand2.nextInt(4);
                        }
                    } else {
                        E.move();
                        if (E.getX() == ninjaboy.getTileX() * 25 && E.getY() == ninjaboy.getTileY() * 25) {
                            ninjaHit.play();
                            score.healthcounter--;

                            if (mapCounter == 3) {
                                ninjaHit.play();
                                score.ninjHcounter--;
                            }
                        }

                    }
                }
            }

        });

        timer2.start();
        timer3 = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList bullets = ninjaboy.getBullets();
                int tmpx, tmpy;
                boolean Enemykilled = false;
                for (int m = 0; m < bullets.size(); m++) {
                    Bullet B = (Bullet) bullets.get(m);
                    tmpx = B.getX();
                    tmpy = B.getY();
                    for (int n = 0; n < creatEnemies.Enemies.size(); n++) {
                        Enemies E = (Enemies) creatEnemies.Enemies.get(n);
                        if ((tmpx <= E.getX() + 10 && tmpx >= E.getX() - 10) && (tmpy <= E.getY() + 10 && tmpy >= E.getY() - 10)) {
                            //////////////////////////////////////////////////////////
                            enemyHit.play();
                            creatEnemies.Enemies.remove(E);
                            Enemykilled = true;//scoring//
                            if (E instanceof Assasin1) {
                                score.setScore("B");
                            } else if (E instanceof Assasin2) {
                                score.setScore("A");
                            }
                            break;
                        }
                    }
                    if (Enemykilled) {
                        B.isVisible = false;
                        bullets.remove(B);
                        break;
                    }
                }
            }
        });
        timer3.start();
        timer8 = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList bullets2 = ninjaboy.getBullets2();
                int tmpx, tmpy;
                boolean Enemykilled = false;
                for (int m = 0; m < bullets2.size(); m++) {
                    Bullet2 B = (Bullet2) bullets2.get(m);
                    tmpx = B.getX();
                    tmpy = B.getY();
                    for (int n = 0; n < creatEnemies.Enemies.size(); n++) {
                        Enemies E = (Enemies) creatEnemies.Enemies.get(n);
                        if ((tmpx <= E.getX() + 10 && tmpx >= E.getX() - 10) && (tmpy <= E.getY() + 10 && tmpy >= E.getY() - 10)) {
                            creatEnemies.Enemies.remove(E);
                            Enemykilled = true;//scoring//
                            if (E instanceof Assasin1) {
                                score.setScore("B");
                            } else if (E instanceof Assasin2) {
                                score.setScore("A");
                            }
                            break;
                        }
                    }
                }
            }
        });
        timer8.start();
        Random randboss = new Random();
        timer4 = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList bossbullets = boss.getBullets();
                boss.Fire(randboss.nextInt(9));
                repaint();
            }
        });

        timer5 = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ArrayList bossbullets = boss.getBullets();
                for (int j = 0; j < bossbullets.size(); j++) {
                    BossBullet BB = (BossBullet) bossbullets.get(j);
                    BB.Move(BB.direction);
                    /*if (BB.getX() < -275 || BB.getX() > 275 || BB.getY() < -300 || BB.getY() > 400) {
                     bossbullets.remove(BB);
                     }*/
                }
            }
        });

        timer6 = new Timer(1, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList bullets = ninjaboy.getBullets();
                for (int i = 0; i < bullets.size(); i++) {
                    Bullet b = (Bullet) bullets.get(i);
                    if ((b.getX() >= 350 && b.getX() <= 370) && (b.getY() >= 350 && b.getY() <= 370)) {
                        boss.health--;
                        ////////////here
                        explode.play();
                        score.setbossHealth(boss.health, true);
                        score.setNinjaHealth(50);
                        bullets.remove(b);
                    }
                }
                ArrayList bullets2 = ninjaboy.getBullets2();
                for (int i = 0; i < bullets2.size(); i++) {
                    Bullet2 b = (Bullet2) bullets2.get(i);
                    if ((b.getX() >= 350 && b.getX() <= 370) && (b.getY() >= 350 && b.getY() <= 370)) {
                        boss.health = boss.health - 5;
                        explode.play();
                        ////////////here
                        score.setbossHealth(boss.health, true);
                        score.setNinjaHealth(50);
                        bullets2.remove(b);
                    }
                }
            }
        });

        timer7 = new Timer(120, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList bossbullets = boss.getBullets();

                for (int i = 0; i < bossbullets.size(); i++) {
                    BossBullet E = (BossBullet) bossbullets.get(i);
                    if ((E.getX() / 25) * 25 == ninjaboy.getTileX() * 25 && (E.getY() / 25) * 25 == ninjaboy.getTileY() * 25) {
                        ninjaHit.play();
                        score.ninjHcounter--;

                    }
                }
                if ((ninjaboy.getTileX() * 25 >= 310 && ninjaboy.getTileX() * 25 <= 400)
                        && (ninjaboy.getTileY() * 25 >= 300 && ninjaboy.getTileY() * 25 <= 390)) {
                    score.ninjHcounter = 0;

                }
            }

        });

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (dFlag) {

                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (ninjaboy.getTileX() > 1) {
                            if ((!map.getMap(ninjaboy.getTileX() - 1, ninjaboy.getTileY()).equals("w"))
                                    && (!map.getMap(ninjaboy.getTileX() - 1, ninjaboy.getTileY()).equals("d"))) {
                                ninjaboy.Move(-1, 0);
                                direction = 0;
                                repaint();
                            }
                            if (map.getMap(ninjaboy.getTileX() - 1, ninjaboy.getTileY()).equals("f")) {
                                score.setScore(Integer.toString(timeCounter));
                                mapCounter++;

                                win.play();

                                if (mapCounter == 3) {
                                    stopMusic();

                                    playBossMusic();
                                    timer4.start();
                                    timer5.start();
                                    timer6.start();
                                    timer7.start();
                                    score.setbossHealth(boss.health, true);
                                    score.setNinjaHealth(50);
                                }
                                creatEnemies.Enemies.clear();

                                creatEnemies.mapCounter = mapCounter;
                                try {
                                    creatEnemies.create();
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    loadMap();
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                gameTimer.restart();
                                timeCounter = -1;
                                repaint();

                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("b")) {
                                ninjaHit.play();
                                score.healthcounter--;
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");

                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("p")) {
                                //set present score
                                score.setScore("E");
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");

                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("r")) {
                                //set ring score
                                score.setScore("F");
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("h")) {
                                if (score.healthcounter < 3) {
                                    score.healthcounter++;
                                }
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (ninjaboy.getTileX() < 28) {
                            if ((!map.getMap(ninjaboy.getTileX() + 1, ninjaboy.getTileY()).equals("w"))
                                    && (!map.getMap(ninjaboy.getTileX() + 1, ninjaboy.getTileY()).equals("d"))) {
                                ninjaboy.Move(1, 0);
                                direction = 2;
                                repaint();
                            }
                            if (map.getMap(ninjaboy.getTileX() + 1, ninjaboy.getTileY()).equals("f")) {
                                score.setScore(Integer.toString(timeCounter));
                                mapCounter++;

                                win.play();
                                if (mapCounter == 3) {
                                    stopMusic();
                                    playBossMusic();

                                    timer4.start();
                                    timer5.start();
                                    timer6.start();
                                    timer7.start();
                                    score.setbossHealth(boss.health, true);
                                    score.setNinjaHealth(50);
                                }

                                creatEnemies.Enemies.clear();

                                creatEnemies.mapCounter = mapCounter;
                                try {
                                    creatEnemies.create();
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    loadMap();
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                repaint();
                                gameTimer.restart();
                                timeCounter = -1;
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("b")) {
                                ninjaHit.play();
                                score.healthcounter--;
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("p")) {
                                //set present score
                                score.setScore("F");
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("r")) {
                                score.setScore("E");
                                //set ring score
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("h")) {
                                if (score.healthcounter < 3) {
                                    score.healthcounter++;
                                }
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                        }

                    }
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        if (ninjaboy.getTileY() > 1) {
                            if ((!map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY() - 1).equals("w"))
                                    && (!map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY() - 1).equals("d"))) {
                                ninjaboy.Move(0, -1);
                                direction = 1;
                                repaint();
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY() - 1).equals("f")) {
                                score.setScore(Integer.toString(timeCounter));
                                mapCounter++;

                                win.play();
                                if (mapCounter == 3) {
                                    stopMusic();

                                    playBossMusic();
                                    timer4.start();
                                    timer5.start();
                                    timer6.start();
                                    timer7.start();
                                    score.setbossHealth(boss.health, true);
                                    score.setNinjaHealth(50);
                                }
                                if (mapCounter == 2) {
                                    ninjaboy.setX(1);
                                    ninjaboy.setY(2);

                                }
                                creatEnemies.Enemies.clear();

                                creatEnemies.mapCounter = mapCounter;
                                try {
                                    creatEnemies.create();
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                try {
                                    loadMap();
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                gameTimer.restart();
                                timeCounter = -1;
                                repaint();
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("b")) {
                                ninjaHit.play();
                                score.healthcounter--;
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("p")) {
                                score.setScore("E");

                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("r")) {
                                score.setScore("E");

                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("h")) {
                                if (score.healthcounter < 3) {
                                    score.healthcounter++;
                                }
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (ninjaboy.getTileY() < 28) {
                            if ((!map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY() + 1).equals("w"))
                                    && (!map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY() + 1).equals("d"))) {
                                ninjaboy.Move(0, 1);
                                direction = 3;

                                repaint();
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY() + 1).equals("f")) {
                                score.setScore(Integer.toString(timeCounter));
                                mapCounter++;

                                backgroundMusic.stop();
                                win.play();
                                for (int i = 0; i < 900000000; i++) {
                                }
                                backgroundMusic.play();
                                if (mapCounter == 3) {
                                    stopMusic();

                                    playBossMusic();
                                    timer4.start();
                                    timer5.start();
                                    timer6.start();
                                    timer7.start();
                                    score.setbossHealth(boss.health, true);
                                    score.setNinjaHealth(50);
                                }
                                if (mapCounter == 1) {
                                    ninjaboy.setX(3);
                                    ninjaboy.setY(26);
                                }

                                creatEnemies.Enemies.clear();

                                creatEnemies.mapCounter = mapCounter;
                                try {
                                    creatEnemies.create();
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                try {
                                    loadMap();
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(GamePlay.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                gameTimer.restart();
                                timeCounter = -1;
                                repaint();
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("b")) {
                                ninjaHit.play();
                                score.healthcounter--;
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("p")) {
                                score.setScore("E");

                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("r")) {

                                score.setScore("E");
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                            if (map.getMap(ninjaboy.getTileX(), ninjaboy.getTileY()).equals("h")) {
                                if (score.healthcounter < 3) {
                                    score.healthcounter++;
                                }
                                map.setMap(ninjaboy.getTileX(), ninjaboy.getTileY(), "g");
                            }
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                        if (typeofwep == 1) {
                            typeofwep = 2;
                        } else {
                            typeofwep = 1;
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        /*Animation*/
                        frame++;
                        if (frame > 2) {
                            frame = 0;
                        }
                        /*Bullet*/
                        if (specialweapon > 0 && typeofwep == 2) {
                            ninjaboy.Fire(direction, 2);
                            specialweapon--;
                        } else {
                            ninjaboy.Fire(direction, 1);
                        }
                        repaint();
                        if (isBoss) {
                            Moratada.play();
                        } else {
                            bulletMusic.play();
                        }

                    }
                    if (e.getKeyCode() == KeyEvent.VK_H) {
                        if (!isHamada) {
                            backgroundMusic.stop();
                            Hamada.play();
                            Hamada.loop();
                            isHamada = true;
                        } else {
                            Hamada.stop();
                            backgroundMusic.play();
                            backgroundMusic.loop();
                            isHamada = false;
                        }

                    }
                    if (e.getKeyCode() == KeyEvent.VK_R) {
                        if (!isBoss) {
                            isBoss = true;
                        } else {
                            isBoss = false;
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                frame = 2;
                repaint();
            }
        });

    }

    public void drawninjaboy(Graphics g) {
        g.drawImage(ninjaboy.getPlayerimg(direction, frame), ninjaboy.getTileX() * 25, ninjaboy.getTileY() * 25, null);
    }

    public void drawBoss(Graphics g) {

        g.drawImage(boss.getPlayerimg(0, 0 + (int) (Math.random() * 6)), 300, 300, null);
    }

    public void loadMap() throws FileNotFoundException {

        if (mapCounter == 0) {

            map = new Map("map01.txt");
            mapName = "map01.txt";
            this.arraylength = map.getLenght();

        } else if (mapCounter == 1) {
            map.reciever.clear();
            map = new Map("map02.txt");
            mapName = "map02.txt";
            this.arraylength = map.getLenght();
        } else if (mapCounter == 2) {
            map.reciever.clear();
            map = new Map("map03.txt");
            mapName = "map03.txt";
            this.arraylength = map.getLenght();
        } else if (mapCounter == 3) {
            map.reciever.clear();
            map = new Map("mapboss2.txt");
            mapName = "bossmap.txt";
            this.arraylength = map.getLenght();
        }
    }

    public void loadImages() {

        img = new ImageIcon(getClass().getResource("/ICONS/wall_25.jpg"));
        wall = img.getImage();

        img = new ImageIcon(getClass().getResource("/ICONS/grass_25.jpg"));
        grass = img.getImage();

        img = new ImageIcon(getClass().getResource("/ICONS/path.png"));
        path = img.getImage();

        img = new ImageIcon(getClass().getResource("/ICONS/finish.png"));
        finishLine = img.getImage();

        img = new ImageIcon(getClass().getResource("/ICONS/bomb.png"));
        bomb = img.getImage();

        img = new ImageIcon(getClass().getResource("/ICONS/gift.png"));
        present = img.getImage();

        img = new ImageIcon(getClass().getResource("/ICONS/diamond.png"));
        diamond = img.getImage();

        img = new ImageIcon(getClass().getResource("/ICONS/heart.png"));
        heart = img.getImage();
    }

    public void playMusic() {
        backgroundMusic.play();
        backgroundMusic.loop();
    }

    public void stopMusic() {
        backgroundMusic.stop();
    }

    public void playBossMusic() {
        bosss.play();
        bosss.loop();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);
        drawninjaboy(g);
        drawBullets(g);
        drawEnemies(g);
        score.display(g);
        checkhealth();

        if (mapCounter == 3) {

            drawBossBullets(g);
            drawBoss(g);
            checkNhealth();
            checkBhealth();
        }

        if (winnerFlag) {
            bosss.stop();
            win.play();
            winner(g);
        }
        if (looserFlag) {
            bosss.stop();
            stopMusic();
            lost.play();
            looser(g);
        }
    }

    public void winner(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Font fnt1 = new Font("arial", Font.BOLD, 90);
        g2.setFont(fnt1);
        g2.setColor(Color.RED);
        g2.drawString("WINNER", 200, 250);
        Font fnt2 = new Font("arial", Font.BOLD, 50);
        g2.setFont(fnt2);
        g2.drawString("Score : ", 250, 330);
        g2.drawString(Integer.toString(score.getScore() + 2000), 440, 330);
    }

    public void looser(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        Font fnt1 = new Font("arial", Font.BOLD, 90);
        g2.setFont(fnt1);
        g2.drawString("You Lost", 160, 250);
        Font fnt2 = new Font("arial", Font.BOLD, 50);
        g2.setFont(fnt2);
        g2.drawString("Score : ", 260, 350);
        g2.drawString(Integer.toString(score.getScore()), 450, 350);

    }

    public void checkhealth() {
        if (score.healthcounter == 0) {
            timer.stop();
            timer2.stop();
            timer3.stop();
            timer4.stop();
            timer5.stop();
            timer6.stop();
            timer7.stop();
            gameTimer.stop();
            dFlag = false;
            //lost from enemy
            looserFlag = true;
        }
    }

    public void checkNhealth() {
        if (score.ninjHcounter == 0) {
            timer.stop();
            timer2.stop();
            timer3.stop();
            timer4.stop();
            timer5.stop();
            timer6.stop();
            timer7.stop();
            gameTimer.stop();
            dFlag = false;
            looserFlag = true;
        }

    }

    public void checkBhealth() {
        if (score.bHealth.equals("0")) {
            timer.stop();
            timer2.stop();
            timer3.stop();
            timer4.stop();
            timer5.stop();
            timer6.stop();
            timer7.stop();
            gameTimer.stop();
            dFlag = false;
            winnerFlag = true;

        }

    }

    public void drawBossBullets(Graphics g) {
        ArrayList bossbullets = boss.getBullets();
        for (int j = 0; j < bossbullets.size(); j++) {
            BossBullet BB = (BossBullet) bossbullets.get(j);
            g.drawImage(BB.getImage(), BB.getX(), BB.getY(), null);

        }
    }

    public void drawBullets(Graphics g) {
        ArrayList bullets = ninjaboy.getBullets();
        for (int j = 0; j < bullets.size(); j++) {
            Bullet B = (Bullet) bullets.get(j);
            g.drawImage(B.getImage(), B.getX(), B.getY(), null);
        }
        ArrayList bullets2 = ninjaboy.getBullets2();
        for (int j = 0; j < bullets2.size(); j++) {
            Bullet2 B = (Bullet2) bullets2.get(j);
            g.drawImage(B.getImage(), B.getX(), B.getY(), null);
        }
    }

    public void drawEnemies(Graphics g) {
        for (int j = 0; j < creatEnemies.Enemies.size(); j++) {
            Enemies E = (Enemies) creatEnemies.Enemies.get(j);
            g.drawImage(E.getImage(), E.getX(), E.getY(), null);
        }
    }

    public void drawMap(Graphics g) {
        for (int i = 0; i < this.arraylength; i++) {
            for (int j = 0; j < this.arraylength; j++) {

                if (map.getMap(j, i).equals("w")) {
                    g.drawImage(wall, j * 25, i * 25, null);
                } else if (map.getMap(j, i).equals("g")) {
                    g.drawImage(grass, j * 25, i * 25, null);
                } else if (map.getMap(j, i).equals("d")) {
                    g.drawImage(path, j * 25, i * 25, null);
                } else if (map.getMap(j, i).equals("f")) {
                    g.drawImage(finishLine, j * 25, i * 25, null);
                } else if (map.getMap(j, i).equals("p")) {
                    g.drawImage(grass, j * 25, i * 25, null);
                    g.drawImage(present, j * 25, i * 25, null);
                } else if (map.getMap(j, i).equals("b")) {
                    g.drawImage(grass, j * 25, i * 25, null);
                    g.drawImage(bomb, j * 25, i * 25, null);
                } else if (map.getMap(j, i).equals("r")) {
                    g.drawImage(grass, j * 25, i * 25, null);
                    g.drawImage(diamond, j * 25, i * 25, null);
                } else if (map.getMap(j, i).equals("h")) {
                    g.drawImage(grass, j * 25, i * 25, null);
                    g.drawImage(heart, j * 25, i * 25, null);
                }

            }

        }

    }

}
