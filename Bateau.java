import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Bateau {

    private Port depart; //= new Port();
    private Port arrive; //= new Port();

    //Permet d'identifier si le bateau est en mer ou non.
    private boolean estEnMer;
    //Permet d'identifier si le bateau est en guerre ou non.
    private boolean estEnGuerre;
    

    //Coordonnées X et Y du bateau sur l'interface.
    private int x;
    private int y;


    //Nombre de point de vie du bateau.
    private int pointDeVie;
    private int currentPointDeVie;
    //Degat que le bateau inflige par seconde.
    private int degatsParSeconde;
    //Valeur à laquelle le bateau considère qu'il peut entrer en guerre.
    private int range;

    private Thread thread;

    public Bateau(int x,int y){
        this.range = new Random().nextInt(150);
        this.pointDeVie = new Random().nextInt(2500);
        this.currentPointDeVie = this.pointDeVie;
        this.degatsParSeconde = new Random().nextInt(200);
        this.depart = null;
        this.arrive = null;
        this.estEnMer = true;
        this.x = x;
        this.y = y;
    }

    public Bateau(Port pDepart,Port pArrive){
        this.range = new Random().nextInt(150);
        this.pointDeVie = new Random().nextInt(2500);
        this.currentPointDeVie = this.pointDeVie;
        this.degatsParSeconde = new Random().nextInt(200);
        if (pArrive.ajouterBateau()) {
            this.depart = pDepart;
            this.arrive = pArrive;
            this.estEnMer = true;//false;
            this.x = pDepart.getX();//pArrive.getX();
            this.y = pDepart.getY();//pArrive.getY();
        }
        else{
            this.estEnMer = true;
            this.x = 400;
            this.y = 400;
        }
    }

    public Bateau(Port pArrive){
        this.range = new Random().nextInt(150);
        this.pointDeVie = new Random().nextInt(2500);
        this.currentPointDeVie = this.pointDeVie;
        this.degatsParSeconde = new Random().nextInt(200);
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

    //Formule à refaire.
    public float distance(){
        if (!estEnMer) {
            return (float)Math.sqrt((this.arrive.getX()*this.arrive.getX()) + (this.depart.getX()*this.depart.getX()) + (this.arrive.getY()*this.arrive.getY()) + (this.depart.getY()*this.depart.getY()));
        }
        return (float)-1;
    }


    public float distanceRestante(){
        if (estEnMer) {
            return (float)Math.sqrt((this.arrive.getX()*this.arrive.getX()) + (this.x*this.x) + (this.arrive.getY()*this.arrive.getY()) + (this.y*this.y));
        }
        return (float)-1;
    }

    public float distanceBateau(int x,int y){
        return (float)Math.sqrt(Math.pow(this.x - x,2) + Math.pow(this.y - y,2));
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
                    this.thread.sleep(new Random().nextInt(3000));
                }
                catch (Exception err) {
                    System.err.println("Erreur lors de la pause du thread !");
                }
            }else{
                
                this.quitter(nDestination);
            }
        }
    }

    public void upThread(Port[] ports,Mer mer,List<Bateau> bateauEnnemi){
        this.thread = new Thread(){
            public void run() {
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Port nouvelleDestination = ports[new Random().nextInt(ports.length)];
                        System.out.println(estEnGuerre);
                        detectEnnemie(bateauEnnemi);
                        if (estEnGuerre == false) {
                            goToDestination(nouvelleDestination);
                        }
                        else{
                            currentPointDeVie--;
                        }
                        mer.repaint();
                    }
                }, 10,10);
            }
        };
        this.thread.start();
    }
    

    //Non fonctionnel pour l'instant.
    public void detectEnnemie(List<Bateau> bateauEnnemi){
        for (int i = 0; i < bateauEnnemi.size(); i++) {
            if (bateauEnnemi.indexOf(this) != i) {
            //System.out.println(bateauEnnemi.indexOf(this));
            float distance = distanceBateau(bateauEnnemi.get(i).getX(), bateauEnnemi.get(i).getY());
                if (distance <= this.range) {
                    System.out.println("Guerre entre le bateau " + bateauEnnemi.indexOf(this) + " et le bateau " +i);     
                    this.changeEtat();
                    bateauEnnemi.get(i).changeEtat();

                }

            } 
            
        }    
        
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


    public void changeEtat(){
        this.estEnGuerre = true;
    }


    public int getRange(){
        return this.range;
    }


    public int lifePourcentage(){
        return (this.currentPointDeVie/pointDeVie)*100;
    }
}
