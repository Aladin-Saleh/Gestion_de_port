import javax.swing.*;
import java.awt.*;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;

public class Screen extends JFrame{
    

    public Screen(){
        this.setLocation(0, 0);
        this.setSize(900,900);
        this.setResizable(false);
        this.setTitle("Les super bateaux wohoo !");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout gLayout = new GridLayout(1,2);
        this.setLayout(gLayout);

        //Définition de port fixe.
        Port[] ports = new Port[5];
        ports[0] = new Port(50,600,5);
        ports[1] = new Port(500,0,12);
        ports[2] = new Port(400,200,6);
        ports[3] = new Port(300,700,8);
        ports[4] = new Port(100,50,7);


        
        
        List<Bateau> bateau = new ArrayList();
        bateau.add(new Bateau(ports[0]));
        //bateau.add(new Bateau(ports[3]));
        //bateau.add(new Bateau(ports[2]));
        //bateau.add(new Bateau(ports[0]));
        //bateau.add(new Bateau(ports[1]));
        //bateau.add(new Bateau(ports[4]));
        //bateau.add(new Bateau(ports[2]));
        //bateau.add(new Bateau(ports[4]));
        //bateau.add(new Bateau(ports[1]));
        //bateau.add(new Bateau(ports[4]));
        //bateau.add(new Bateau(ports[3]));
        //bateau.add(new Bateau(ports[3]));
        //bateau.add(new Bateau(ports[1]));
        //bateau.add(new Bateau(ports[0]));
        
        Info info = new Info();
        InfoContainer iContainer = new InfoContainer();
        iContainer.setSize(100,900);
        
        Mer mer = new Mer(0,0,ports,bateau);
        //Update u = new Update(ports, bateau, mer);
        this.addMouseListener(new MouseControl(ports,mer));
        this.add(mer);
        //this.add(iContainer);

        this.setVisible(true);

        for (Bateau bateau2 : bateau) {
            bateau2.upThread(ports, mer);
        }
    }
    
}
