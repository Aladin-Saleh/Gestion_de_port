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
    private Update update;
    private Thread nThread;


    public MouseControl(Port[] p,Mer m,Update updt){
        this.ports = p;
        this.mer = m; 
        this.update = updt;
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        //Click gauche permet de créer un nouveau bateau.
        if (e.getButton() == MouseEvent.BUTTON1) {
        }
        
        if(e.getButton() == MouseEvent.BUTTON3){
            System.out.println("Nouveau bateau créer");
            update.upThread();

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
