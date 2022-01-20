public class Bateau {

private Port depart; //= new Port();
private Port arrive; //= new Port();
private boolean estEnMer;

private int x;
private int y;

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
        this.estEnMer = true;
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


}
