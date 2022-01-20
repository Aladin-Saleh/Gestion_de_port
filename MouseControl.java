import java.awt.event.*;
import java.util.Random;

import javax.swing.JOptionPane;

public class MouseControl implements MouseListener {

    private Port[] ports;
    private Bateau[] bateau;

    public MouseControl(Port[] p,Bateau[] b){
        this.bateau = b;
        this.ports = p;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Nouveau bateau créer");
        bateau = new Bateau[bateau.length+1];
        bateau[bateau.length+1] = new Bateau(ports[new Random().nextInt(ports.length)]);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
