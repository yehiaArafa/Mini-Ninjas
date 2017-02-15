package minininjas;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class CreateEnemies {

    Random rand1 = new Random();
    Random rand2 = new Random();
    private boolean flagrand = false;
    private int tmpx = 0, tmpy = 0, choose;
    public ArrayList Enemies = new ArrayList<>();
    Map map;
    public int mapCounter = 0;
    public int enemyCounter;

    public CreateEnemies() throws FileNotFoundException {

    }

    public void create() throws FileNotFoundException {

        if (mapCounter == 0) {
            enemyCounter = 20;
            map = new Map("map01.txt");
        } else if (mapCounter == 1) {
            enemyCounter = 40;
            map = new Map("map02.txt");

        } else if (mapCounter == 2) {
            map = new Map("map03.txt");
            enemyCounter = 60;
        } else if (mapCounter == 3) {
            enemyCounter = 10;
            map = new Map("mapboss2.txt");

        }

        for (int i = 0; i < enemyCounter; i++) {
            System.out.println(mapCounter);
            while (!flagrand) {
                tmpx = rand2.nextInt(700) + 25;
                tmpy = rand2.nextInt(700) + 25;
                if ((!((map.getMap(tmpx / 25, tmpy / 25).equals("w"))
                        || (map.getMap(tmpx / 25, tmpy / 25).equals("d"))))) {
                    flagrand = true;
                } else {
                    flagrand = false;
                }
            }
            choose = rand1.nextInt(2);
            if (choose == 0) {
                Assasin1 assasin1 = new Assasin1();
                tmpx = tmpx / 25;
                tmpy = tmpy / 25;
                assasin1.setX(tmpx * 25);
                assasin1.setY(tmpy * 25);
                flagrand = false;
                assasin1.directions = rand2.nextInt(3);
                Enemies.add(assasin1);
            } else if (choose == 1) {
                Assasin2 assasin2 = new Assasin2();
                tmpx = tmpx / 25;
                tmpy = tmpy / 25;
                assasin2.setX(tmpx * 25);
                assasin2.setY(tmpy * 25);
                flagrand = false;
                assasin2.directions = rand2.nextInt(3);
                Enemies.add(assasin2);
            }
        }

    }

}
