import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Update {
    

private Port[] ports = new Port[1];
private List<Bateau> btx = new ArrayList();
private Mer mer;
private Thread[] tBateau;
private Thread t;

    public Update(Port[] p,List<Bateau> b,Mer m){

        this.ports = new Port[p.length];
        //this.btx = new Bateau[p.length];

        this.ports = p;
        this.btx = b;
        this.mer = m;

        //https://www.jmdoudoux.fr/java/dej/chap-threads.htm

        this.tBateau  = new Thread[btx.size()];
        for (int i = 0; i < btx.size(); i++) {
            int indice = i;
            //System.out.println("indice="+indice);
            tBateau[i] = new Thread(){
                public void run() {
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            goToDestination(btx.get(indice).getPortArrive(), btx.get(indice),tBateau[indice]);
                            mer.repaint();
                        }
                    } ,10,10);
                }
            };
            tBateau[i].start();
        } 
    }


}
