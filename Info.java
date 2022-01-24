import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.awt.*;

public class Info extends JComponent {
    
    private ImageIcon bateau = new ImageIcon("img/bateau.png");
    private ImageIcon[] iles = new ImageIcon[4];

    public Info(){
        this.iles[0] = new ImageIcon("img/ile1.png");
        this.iles[1] = new ImageIcon("img/ile2.png");
        this.iles[2] = new ImageIcon("img/ile3.png");
        this.iles[3] = new ImageIcon("img/ile4.png");

        
    }



    @Override
    protected void paintComponent(Graphics p) {
      Graphics gPaint = p.create();
      if (this.isOpaque()) {
        gPaint.setColor(this.getBackground());
        gPaint.fillRect(0, 0, this.getWidth(), this.getHeight());
      }

    }



}
