import javax.swing.*;
import java.awt.Graphics;
import java.util.Random;
import java.awt.*;

public class Mer extends JComponent {

    private int x;
    private int y;
    private ImageIcon mer = new ImageIcon("img/mer.png");
    private ImageIcon bateau = new ImageIcon("img/bateau.png");

    private int[] idIles;
    private ImageIcon[] iles = new ImageIcon[4];

    //Creation d'un tableau de Port, par défault le nombre de Port est à 1.
    private Port[] ports = new Port[1];
    private Bateau[] bateaux = new Bateau[1];
    private Random random = new Random();


    //Constructeur avec tableau de port
    public Mer(int x,int y,Port[] p,Bateau[] b){
      this.iles[0] = new ImageIcon("img/ile1.png");
      this.iles[1] = new ImageIcon("img/ile2.png");
      this.iles[2] = new ImageIcon("img/ile3.png");
      this.iles[3] = new ImageIcon("img/ile4.png");



      this.ports = new Port[p.length];
      this.bateaux = new Bateau[b.length];

      this.bateaux = b;
      this.ports = p;

      this.x = x;
      this.y = y;

      //Géneration aléatoire d'un nombre entre 0 et le nombre d'iles.
      this.idIles = new int[this.ports.length];
      for (int i = 0; i < p.length; i++) {
        idIles[i] = random.nextInt(iles.length);
      }


    }



    
    @Override
    protected void paintComponent(Graphics p) {
      Graphics gPaint = p.create();
      if (this.isOpaque()) {
        gPaint.setColor(this.getBackground());
        gPaint.fillRect(0, 0, this.getWidth(), this.getHeight());
      }

      //Affichage de la mer en fond d'ecran.
      gPaint.drawImage(this.mer.getImage(),x,y,900,900,this); 


      //Affichage des iles qui sont considerer comme des ports.
      for (int i = 0; i < this.ports.length; i++) {
        //Affichage d'une ile en fonction du nombre aleatoire récuperer à la position du port.
        gPaint.drawImage(iles[idIles[i]].getImage(),this.ports[i].getX(),this.ports[i].getY(),150,150,this);
        //Affichage du nombre de quai au dessus de l'ile.
        gPaint.setColor(Color.WHITE);
        Font font = new Font(" Helvetica ",Font.BOLD,20);  
        gPaint.setFont(font);
        gPaint.drawString("Quai disponible : "+this.ports[i].getNbQuais(), this.ports[i].getX(),this.ports[i].getY()+20);
      }

      //Affichage des bateaux.
      for (int j = 0; j < bateaux.length; j++) {
        //gPaint.drawString("Quai : "+this.bateaux[j].distance(), this.bateaux[j].getX(),this.bateaux[j].getY()+20);
        gPaint.drawImage(this.bateau.getImage(), this.bateaux[j].getX(),this.bateaux[j].getY(), 50, 50, this);        
      }



      gPaint.setColor(this.getForeground());
    }



}
