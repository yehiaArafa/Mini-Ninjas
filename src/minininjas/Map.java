package minininjas;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {

    private Scanner s;
    public ArrayList<String> reciever = new ArrayList<String>();

    String path;

    public Map(String path) throws FileNotFoundException {

        this.path = path;
        openFile();
        readFile();
        closeFile();
    }

    public void setpath(String path) {

    }

    public int getLenght() {
        return reciever.size();
    }

    public String getMap(int x, int y) {
        String index;
        index = reciever.get(y).substring(x, x + 1);
        return index;
    }

    public void setMap(int x, int y, String z) {

        String temp;
        temp = reciever.get(y);

        StringBuilder sb = new StringBuilder(temp);
        sb.setCharAt(x, 'g');
        reciever.set(y, sb.toString());

    }

    private void openFile() throws FileNotFoundException {

        s = new Scanner(new File(this.path));

    }

    private void readFile() {

        while (s.hasNext()) {
            reciever.add(s.nextLine());
        }

    }

    private void closeFile() {
        s.close();
    }

}
