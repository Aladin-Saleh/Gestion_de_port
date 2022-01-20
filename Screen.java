import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;

public class Screen extends JFrame{
    

    public Screen(){
        this.setLocation(0, 0);
        this.setSize(900,900);
        this.setResizable(false);
        this.setTitle("Les super bateaux wohoo !");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Définition de port fixe.
        Port[] ports = new Port[5];
        ports[0] = new Port(50,600,5);
        ports[1] = new Port(500,0,12);
        ports[2] = new Port(400,200,6);
        ports[3] = new Port(300,700,8);
        ports[4] = new Port(100,50,7);


        
        
        Bateau[] bateau = new Bateau[6];
        bateau[0] = new Bateau(ports[0]);
        bateau[1] = new Bateau(ports[3]);
        bateau[2] = new Bateau(ports[2]);
        bateau[3] = new Bateau(ports[0]);
        bateau[4] = new Bateau(ports[1]);
        bateau[5] = new Bateau(ports[4]);
        
        
        Mer mer = new Mer(0,0,ports,bateau);
        this.add(mer);
        new Update(ports, bateau, mer);
        this.addMouseListener(new MouseControl(ports, bateau));


        this.setVisible(true);
    }
    
}
