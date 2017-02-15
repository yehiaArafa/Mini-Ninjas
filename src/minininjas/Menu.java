package minininjas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.*;
import Audio.AudioPlayer;
import java.awt.AlphaComposite;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.imageio.ImageIO;

public class Menu extends JPanel {

    private int dx = this.getWidth() / 2 + 250;
    private int dy = 365;
    public int counter = 1;
    private Rectangle playButton = new Rectangle(this.getWidth() / 2 + 280, 370, 150, 50);
    private Rectangle scoreButton = new Rectangle(this.getWidth() / 2 + 280, 440, 150, 50);
    private Rectangle helpButton = new Rectangle(this.getWidth() / 2 + 280, 510, 150, 50);
    private Rectangle quitButton = new Rectangle(this.getWidth() / 2 + 280, 580, 150, 50);
    private Rectangle chooseRect;
    private Image logo;
    private ImageIcon img;

    private AudioPlayer menuSelectSFX = new AudioPlayer("/SFX/menuselect.mp3");

    public Menu() throws IOException {

        img = new ImageIcon(getClass().getResource("/ICONS/logo.png"));
        logo = img.getImage();

        this.setBackground(Color.BLACK);
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "choosed");
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "chooseu");
        this.getActionMap().put("choosed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                menuSelectSFX.play();
                counter++;
                if (counter > 4 && counter < 9) {
                    counter = counter - 4;
                }

                if (counter == 1) { //Play
                    setDy(365);
                } else if (counter == 2) { //Score
                    setDy(435);
                } else if (counter == 3) { //Help
                    setDy(505);
                } else if (counter == 4) { //Quit
                    setDy(575);
                }

                repaint();
            }

        });

        this.getActionMap().put("chooseu", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                menuSelectSFX.play();
                counter--;
                if (counter < 1) {
                    counter = counter + 4;
                }

                if (counter == 1) { //Play
                    setDy(365);
                } else if (counter == 2) { //Score
                    setDy(435);
                } else if (counter == 3) { //Help
                    setDy(505);
                } else if (counter == 4) { //Quit
                    setDy(575);
                }

                repaint();
            }

        });

    }

    private void setDy(int y) {
        this.dy = y;
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);

        g.drawImage(logo, 140, 120, null);

        g.setColor(Color.WHITE);

        Font fnt2 = new Font("arial", Font.PLAIN, 30);
        g.setFont(fnt2);

        g2.drawString("Play", playButton.x + 45, playButton.y + 35);

        g2.drawString("Score", scoreButton.x + 40, scoreButton.y + 35);

        g2.drawString("Help", helpButton.x + 45, helpButton.y + 35);

        g2.drawString("Quit", quitButton.x + 45, quitButton.y + 35);

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, 0.3f));

        g2.setColor(Color.GREEN);

        g2.fillRect(dx, dy, 210, 60);
    }

}
