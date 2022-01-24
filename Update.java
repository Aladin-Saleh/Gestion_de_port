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
                            //System.out.println(tBateau.length);
                            //System.out.println(tBateau.length+"indice="+indice);
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
                System.out.println("Le bateau est deja accoster !");
                System.out.println("Il ne vas pas tarder à quitter le port !");
                try {
                    t.sleep(new Random().nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bateau.quitter(ports[new Random().nextInt(ports.length)]);
                
            }

        }

    }


    public void upThread(){
        Port pArrive = ports[new Random().nextInt(ports.length)];
        mer.newBateau(pArrive);

        this.tBateau  = new Thread[btx.size()];

        this.tBateau[btx.size()-1] = new Thread(){
            public void run() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        int ind = mer.getBateaux().size()-1;
                        Bateau b = mer.getBateaux().get(ind);
                        goToDestination(pArrive, b, tBateau[btx.size()-1]);
                        mer.repaint();
                    }
                }, 10,10);
            }
        };

        
        this.tBateau[btx.size()-1].start();
        
    }


}
