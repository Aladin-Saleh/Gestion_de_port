import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;
import java.awt.*;



public class Port extends JPanel{

private int x;
private int y;
private Quai quais;
private int nbQuais;

public Port(int x1,int y1){
    this.x = x1;
    this.y = y1;
    this.quais = new Quai();
}


public Port(int x1,int y1,int nbQuais){
    this.x = x1;
    this.y = y1;
    this.nbQuais = nbQuais;
    this.quais = new Quai(nbQuais);
}

public void retirerBateau(){
    this.quais.retirerBateau();
}

public boolean ajouterBateau(){
    if (this.quais.ajouterBateau()) {
        return true;
    }
    return false;
}

public int getX() {
    return this.x;
}

public int getY() {
    return this.y;
}

public int getNbQuais() {
    return this.nbQuais - this.quais.getQuaisOccupe();
}

}





