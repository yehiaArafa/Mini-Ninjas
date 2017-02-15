package minininjas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.SwingUtilities;

public class MiniNinjas {

    static Menu menu;
    static GamePlay game;
   

    public static void main(String[] args) throws IOException {
        MainFrame MF = new MainFrame();
        MF.setFocusable(true);
        menu = new Menu();
        MF.add(menu);

        SwingUtilities.updateComponentTreeUI(MF);
        MF.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (menu.counter == 1) {
                        MF.remove(menu);
                        try {
                            game = new GamePlay();
                        } catch (IOException ex) {
                        }
                        MF.add(game);
                        game.requestFocusInWindow();
                       
                    } else if (menu.counter == 2) {
                        MF.remove(menu);      
                        //help .add goes here
                    } else if (menu.counter == 3) {
                        MF.remove(menu);
                        //options .add goes here
                    }
                }
                 
                SwingUtilities.updateComponentTreeUI(MF);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });
    }

}
