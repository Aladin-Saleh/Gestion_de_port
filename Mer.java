import javax.swing.*;
import java.awt.Graphics;
import java.util.*;
import java.util.List;
import java.awt.*;

public class Mer extends JComponent {

    private int x;
    private int y;
    private ImageIcon mer = new ImageIcon("img/mer.png");
    private ImageIcon bateau = new ImageIcon("img/bateau_rouge.png");

    private int[] idIles;
    private ImageIcon[] iles = new ImageIcon[4];

    //Creation d'un tableau de Port, par défault le nombre de Port est à 1.
    private Port[] ports = new Port[1];


    private List<Bateau> bateaux = new ArrayList<>();
    private Random random = new Random();

    //Constructeur avec tableau de port
    public Mer(int x,int y,Port[] p,List<Bateau> b){
      this.iles[0] = new ImageIcon("img/ile1.png");
      this.iles[1] = new ImageIcon("img/ile2.png");
      this.iles[2] = new ImageIcon("img/ile3.png");
      this.iles[3] = new ImageIcon("img/ile4.png");



      this.ports = new Port[p.length];
      //this.bateaux = new Bateau[b.length];

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
      //gPaint.drawImage(this.mer.getImage(),x,y,1300,1300,this); 
      gPaint.setColor(new Color(62,204,204));
      gPaint.fillRect(0, 0, 1300, 1300);

      //Affichage des iles qui sont considerer comme des ports.
      for (int i = 0; i < this.ports.length; i++) {
        //Affichage d'une ile en fonction du nombre aleatoire récuperer à la position du port.
        gPaint.drawImage(iles[idIles[i]].getImage(),this.ports[i].getX(),this.ports[i].getY(),250,250,this);
        //Affichage du nombre de quai au dessus de l'ile.
        gPaint.setColor(Color.WHITE);
        Font font = new Font(" Helvetica ",Font.BOLD,20);  
        gPaint.setFont(font);
        gPaint.drawString("Quai disponible : "+this.ports[i].getNbQuais(), this.ports[i].getX(),this.ports[i].getY()+20);
      }

      //Affichage des bateaux.
      for (int j = 0; j < bateaux.size(); j++) {
        gPaint.drawString("Coord : "+this.bateaux.get(j).distanceRestante(), this.bateaux.get(j).getX(),this.bateaux.get(j).getY()+20);
        gPaint.drawImage(this.bateau.getImage(), this.bateaux.get(j).getX(),this.bateaux.get(j).getY(), 100, 100, this);    
        //gPaint.setColor(Color.RED);
        //gPaint.drawRect(this.bateaux.get(j).getX(), this.bateaux.get(j).getY(),this.bateaux.get(j).getRange(), this.bateaux.get(j).getRange());  
        //Barre de vie
        gPaint.setColor(Color.GREEN);
        gPaint.fillRect(this.bateaux.get(j).getX(), this.bateaux.get(j).getY()+100,(this.bateaux.get(j).lifePourcentage()), 15);
        
        
      }


      gPaint.setColor(this.getForeground());
    }



    public List<Bateau> getBateaux() {
        return this.bateaux;
    }


    public void newBateau(Port pArrive){
      bateaux.add(new Bateau(pArrive));
    }

}
