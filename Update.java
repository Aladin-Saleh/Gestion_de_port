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


    public void goToDestination(Port pDestination,Bateau bateau,Thread t){
        int xDestination = pDestination.getX();
        int yDestination = pDestination.getY();

        int xSource = bateau.getX();
        int ySource = bateau.getY();
        
        if ((yDestination-ySource) > 0) {
            bateau.setY(bateau.getY()+1);
        }
        else if((yDestination-ySource) < 0){
            bateau.setY(bateau.getY()-1);
        }

        if ((xDestination-xSource) > 0) {
            bateau.setX(bateau.getX()+1);
        }
        else if((xDestination-xSource) < 0){
            bateau.setX(bateau.getX()-1);
        }
        else if((xDestination-xSource) == 0 && (yDestination-ySource) == 0 ){
            if (bateau.getStatus() ) {
                bateau.accoster(pDestination);
            }else{
                //System.out.println("Le bateau est deja accoster !");
                //System.out.println("Il ne vas pas tarder à quitter le port !");
                /*try {
                    t.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                int val = new Random().nextInt(ports.length);
                bateau.quitter(ports[val]);
                
            }

        }

    }


    public void upThread(){
        this.tBateau  = new Thread[btx.size()];
        tBateau[btx.size()-1] = new Thread(){
            public void run() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int ind = mer.getBateaux().size()-1;
                        Bateau b = mer.getBateaux().get(ind);
                        goToDestination(b.getPortArrive(), b, t);
                        mer.repaint();
                    }
                }, 10,10);
            }
        };

        
        tBateau[btx.size()-1].start();
        
    }


}
