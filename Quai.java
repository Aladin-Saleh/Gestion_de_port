public class Quai {
    
private int nbQuais;
private int nbQuaisOccupe;

public Quai(){
    this.nbQuais = 3;
    this.nbQuaisOccupe = 0;
}

public Quai(int nbQuai){
    if(nbQuai < 0){
        this.nbQuais = 3;
        this.nbQuaisOccupe = 0;
    }
    this.nbQuais = nbQuai;
    this.nbQuaisOccupe = 0;
}

public boolean ajouterBateau(){
    if (this.nbQuaisOccupe < this.nbQuais) {
        this.nbQuaisOccupe++;
        return true;
    }
    return false;
}

public void retirerBateau(){
    this.nbQuaisOccupe--;
}

public int getQuaisOccupe(){
    return this.nbQuaisOccupe;
}


}


