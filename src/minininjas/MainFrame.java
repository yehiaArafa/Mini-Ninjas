package minininjas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() throws IOException {

       // this.setSize(485, 508); // tile 32 pixels

      //  this.setSize(700, 720); // tile 20 pixels 605/628
        
         this.setSize(750, 840); // tile 20 pixels 605/628
     
        Toolkit tk = Toolkit.getDefaultToolkit();

        Dimension dm = tk.getScreenSize();

        int Xpos = ((dm.width / 2) - (this.getWidth() / 2));
        int Ypos = ((dm.height / 2) - (this.getHeight() / 2));
        this.setLocation(Xpos, Ypos);
        this.setTitle("Mini NiNjas");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setResizable(false);
        this.setVisible(true);

    }

}
