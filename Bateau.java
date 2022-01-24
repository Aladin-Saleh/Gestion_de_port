import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Bateau {

    private Port depart; //= new Port();
    private Port arrive; //= new Port();
    private boolean estEnMer;

    private int x;
    private int y;

    private Thread thread;

    public Bateau(int x,int y){

    this.depart = null;
    this.arrive = null;
    this.estEnMer = true;
    this.x = x;
    this.y = y;
    }
    
    public Bateau(Port pArrive){
        if (pArrive.ajouterBateau()) {
            this.arrive = pArrive;
            this.estEnMer = true;//false;
            this.x = 400;//pArrive.getX();
            this.y = 400;//pArrive.getY();
        }
        else{
            this.estEnMer = true;
            this.x = 400;
            this.y = 400;
        }
    }

    public void accoster(Port a){
        if (a.ajouterBateau()) {
            this.arrive = a;
            this.estEnMer = false;
        }
    }


    public void quitter(Port nArrive){
        if (this.arrive != null) {
            this.arrive.retirerBateau();
            this.estEnMer = true;
            this.depart = this.arrive;
            this.arrive = nArrive;
        }
    }


    public float distance(){
        if (!estEnMer) {
            return (float)Math.sqrt((this.arrive.getX()*this.arrive.getX()) + (this.depart.getX()*this.depart.getX())) + (float)Math.sqrt((this.arrive.getY()*this.arrive.getY()) + (this.depart.getY()*this.depart.getY()));
        }
        return (float)-1;
    }

    public boolean getStatus(){
        return this.estEnMer;
    }

    public void setX(int nX){
        this.x = nX;
    }

    public void setY(int nY) {
        this.y = nY;
    }

    public int getX() {
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Port getPortArrive(){
        return this.arrive;
    }
        
    public void setPortArrive(Port nArrive){
        this.depart = this.arrive;
        this.arrive = nArrive;
    }

    public void goToDestination(Port pDestination,Port nDestination){
        int xDestination = pDestination.getX();
        int yDestination = pDestination.getY();

        int xSource = this.x;
        int ySource = this.y;
        
        if ((yDestination-ySource) > 0) {
            this.y++;
        }
        else if((yDestination-ySource) < 0){
            this.y--;
        }

        if ((xDestination-xSource) > 0) {
            this.x++;
        }
        else if((xDestination-xSource) < 0){
            this.x--;
        }
        else if((xDestination-xSource) == 0 && (yDestination-ySource) == 0 ){
            if (this.getStatus() ) {
                this.accoster(pDestination);
            }else{
                //System.out.println("Le bateau est deja accoster !");
                //System.out.println("Il ne vas pas tarder à quitter le port !");
                try {
                    this.thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.quitter(nDestination);
            }

        }

    }

    public void upThread(Port[] ports, Mer mer){
        
        Port pArrive = ports[new Random().nextInt(ports.length)];
        mer.newBateau(pArrive);

            this.thread = new Thread(){
            public void run() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println(pArrive.toString());
                        int ind = mer.getBateaux().size()-1;
                        Bateau b = mer.getBateaux().get(ind);
                        goToDestination(pArrive, b);
                        mer.repaint();
                    }
                }, 10,10);
            }
        };

        
        this.thread.start();
        
    }
}
