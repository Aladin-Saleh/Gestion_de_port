import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class MouseControl implements MouseListener {

    private Port[] ports;
    private Mer mer;
    private Thread nThread;
    private List<Bateau> bateaux = new ArrayList();
        

    public MouseControl(Port[] p,Mer m,List<Bateau> b){
        this.bateaux = b;
        this.ports = p;
        this.mer = m; 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Click gauche permet de créer un nouveau bateau.
        if (e.getButton() == MouseEvent.BUTTON1) {
        }
        
        if(e.getButton() == MouseEvent.BUTTON3){
            System.out.println("Nouveau bateau créer");
            Port pArrive = ports[new Random().nextInt(ports.length)];
            mer.newBateau(pArrive);
            mer.getBateaux().get(mer.getBateaux().size()-1).upThread(this.ports, mer,bateaux);
        }
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
