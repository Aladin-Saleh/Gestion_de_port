import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Bateau {

    private Port depart; //= new Port();
    private Port arrive; //= new Port();

    private boolean estEnMer;
    

    //Coordonnées X et Y du bateau sur l'interface.
    private int x;
    private int y;


    //Nombre de point de vie du bateau.
    private int pointDeVie;
    //Degat que le bateau inflige par seconde.
    private int degatsParSeconde;
    //Valeur à laquelle le bateau considère qu'il peut entrer en guerre.
    private int range;

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

    public void goToDestination(Port nDestination){
        int xDestination = this.arrive.getX();
        int yDestination = this.arrive.getY();
        
        if ((yDestination-this.y) > 0) {
            this.y++;
        }
        else if((yDestination-this.y) < 0){
            this.y--;
        }

        if ((xDestination-this.x) > 0) {
            this.x++;
        }
        else if((xDestination-this.x) < 0){
            this.x--;
        }
        else if((xDestination-this.x) == 0 && (yDestination-this.y) == 0 ){
            if (this.getStatus() ) {
                this.accoster(this.arrive);
                try {
                    this.thread.sleep(new Random().nextInt(2000));
                }
                catch (Exception err) {
                    System.err.println("Erreur lors de la pause du thread !");
                }
            }else{
                
                this.quitter(nDestination);
            }
        }
    }

    public void upThread(Port[] ports,Mer mer){
        this.thread = new Thread(){
            public void run() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Port nouvelleDestination = ports[new Random().nextInt(ports.length)];
                        goToDestination(nouvelleDestination);
                        mer.repaint();
                    }
                }, 10,10);
            }
        };
        this.thread.start();
    }

    public void detectEnemie(List<Bateau> bateauEn){

    }

}
