import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class MouseControl implements MouseListener {

    private List<Port> ports;
    private Mer mer;
    private Thread nThread;
    private List<Bateau> bateaux = new ArrayList();
        
    

    public MouseControl(List<Port> p,Mer m,List<Bateau> b){
        this.bateaux = b;
        this.ports = p;
        this.mer = m; 
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Click gauche permet de créer un nouveau bateau.
        if (e.getButton() == MouseEvent.BUTTON1) {
            
            int x1 = e.getX();
            int y1 = e.getY();
            boolean isPossible = true;
            int index = 0;
            
            try{
                while(isPossible && index<this.ports.size()){

                    int x = this.ports.get(index).getX();
                    int y = this.ports.get(index).getY();

                    System.out.println("\nX : "+x+" Y : "+y+"\nX : "+(x+250)+" Y : "+(y+250));
                    System.out.println("\nX1 : "+x1+" Y1 : "+y1+"\nX : "+(x1+250)+" Y : "+(y1+250));
    
                    if(x1>= x-250 && x1 <= x+250 && y1 >= y-250 && y1 <= y+250){
                        isPossible = false;
                    }
                    index++;
                }
    
                if(isPossible){
    
                    Port port = new Port(e.getX(),e.getY(),new Random().nextInt(9)+1);
                    mer.ajouterPort(port);
                    this.ports.add(port);
                    System.out.println("Nouveau port creer");
    
                }
            }catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        
        if(e.getButton() == MouseEvent.BUTTON3){
            System.out.println("Nouveau bateau creer");
            Port pArrive = ports.get(new Random().nextInt(ports.size()));
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
