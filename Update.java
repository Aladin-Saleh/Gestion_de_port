import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Update {
    

private Port[] ports = new Port[1];
private Bateau[] btx;
private Mer mer;

    public Update(Port[] p,Bateau[] b,Mer m){

        this.ports = new Port[p.length];
        this.btx = new Bateau[p.length];

        this.ports = p;
        this.btx = b;
        this.mer = m;



        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int seconde = 0;

            @Override
            public void run() {
                for (int i = 0; i < b.length; i++) {
                    goToDestination(b[i].getPortArrive(), b[i]);
                }
                m.repaint();
                
            };

        },10 ,10);

    }


    public void goToDestination(Port pDestination,Bateau bateau){
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
            if (bateau.getStatus()) {
                bateau.accoster(pDestination);
            }else{
                System.out.println("Le bateau est deja accoster !");
                System.out.println("Il ne vas pas tarder à quitter le port !");
                bateau.quitter(ports[new Random().nextInt(ports.length)]);
            }

        }

        System.out.println(bateau.getStatus());


    }


}
