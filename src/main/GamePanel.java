package main;

import java.awt.Dimension;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Items.Item;
import Level.Porte;
import Level.Room;
import entity.Chest;
import entity.Entity;
import entity.Player;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Panel principal du jeu contenant la map principale
 *
 */
public class GamePanel extends JPanel implements Runnable{
	
	//Param�tres de l'�cran
	final int ORIGINAL_TILE_SIZE = 16; 							// une tuile de taille 16x16
	final int SCALE = 3; 										// �chelle utilis�e pour agrandir l'affichage
	public final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE; 	// 48x48
	public final int MAX_SCREEN_COL = 15;
	public final int MAX_SCREE_ROW = 11; 					 	// ces valeurs donnent une r�solution 4:3
	public final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // 768 pixels
	public final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREE_ROW;	// 576 pixels

	// FPS : taux de rafraichissement
	int m_FPS;
	
	// Cr�ation des diff�rentes instances (Player, KeyHandler, TileManager, GameThread ...)
	KeyHandler m_keyH;
	Thread m_gameThread;
	Player player;
		
	private Room m_room;

	/**
	 * Constructeur
	 */
	public GamePanel() {
		m_FPS = 60;				
		
			
		//Changements
		//Création de l'handler et du player
		m_keyH = new KeyHandler();
		this.player = new Player(this, m_keyH);
		m_keyH.setPlayer(this.player);
		this.player.setStep(ORIGINAL_TILE_SIZE*SCALE);

		ArrayList<Entity> empty = new ArrayList<Entity>();

		//Création de la room 1
		TileManager tileManager = new TileManager(this,"/maps/map.txt");
		m_room = new Room(1, this.player, tileManager,empty);
		
		//Création de la room 2
		TileManager tileManager2 = new TileManager(this, "/maps/map2.txt");
		Room r2 = new Room(2,this.player,tileManager2,empty);
		//Création de la room 3
		TileManager tileManager3 = new TileManager(this, "/maps/map3.txt");
		Room r3 = new Room(3,this.player,tileManager3,empty);
		//Création de la room 4
		TileManager tileManager4 = new TileManager(this, "/maps/map4.txt");
		Room r4 = new Room(4,this.player,tileManager4,empty);
		//Création de la room 5
		TileManager tileManager5 = new TileManager(this, "/maps/map5.txt");
		Room r5 = new Room(5,this.player,tileManager5,empty);
		//Création de la room 6
		TileManager tileManager6 = new TileManager(this, "/maps/map6.txt");
		//Création entités de la map 6
		ArrayList<Entity> entities = new ArrayList<Entity>();
		Item contenu = new Item(1,"sword",9*this.player.getStep(),5*this.player.getStep(),16,16);
		
		try {
			BufferedImage img = ImageIO.read(getClass().getResource("/tiles/chest.png"));
			Entity e1 = new Chest(1,"chest",9*this.player.getStep(),5*this.player.getStep(),img,contenu);
			e1.m_gp = this;
			entities.add(e1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		Room r6 = new Room(6,this.player,tileManager6,entities);
		//Création de la room 7
		TileManager tileManager7 = new TileManager(this, "/maps/map7.txt");
		Room r7 = new Room(7,this.player,tileManager7,empty);
		//Création de la room 8
		TileManager tileManager8 = new TileManager(this, "/maps/map8.txt");
		Room r8 = new Room(8,this.player,tileManager8,empty);
		//Création de la room 9
		TileManager tileManager9 = new TileManager(this, "/maps/map9.txt");
		Room r9 = new Room(9,this.player,tileManager9,empty);

		//Création de la room Nether 1
		TileManager tileManager11 = new TileManager(this, "/maps/map11.txt");
		Room r11 = new Room(11,this.player,tileManager11,empty);
		//Création de la room Nether 2
		TileManager tileManager12 = new TileManager(this, "/maps/map12.txt");
		Room r12 = new Room(12,this.player,tileManager12,empty);
		//Création de la room Nether 3
		TileManager tileManager13 = new TileManager(this, "/maps/map13.txt");
		Room r13 = new Room(13,this.player,tileManager13,empty);

		
		Porte p = new Porte(1,m_room, r2);
		Porte p2 = new Porte(2,r2,r3);
		Porte p3 = new Porte(3,r3, r4);
		Porte p4 = new Porte(4,r4,r5);
		Porte p5 = new Porte(5,r5, r6);
		Porte p6 = new Porte(6,r5,r7);
		Porte p7 = new Porte(7,r7, r8);
		Porte p8 = new Porte(8,r8,r9);
		Porte p9 = new Porte(9,r9,r11);
		Porte p10 = new Porte(10,r11,r12);
		Porte p11 = new Porte(11,r12,r13);
		
		m_room.setPorte(p, "bas");
		r2.setPorte(p, "haut");
		r2.setPorte(p2,"droite");
		r3.setPorte(p2, "gauche");
		r3.setPorte(p3,"droite");
		r4.setPorte(p3, "gauche");
		r4.setPorte(p4,"droite");
		r5.setPorte(p4, "gauche");
		r5.setPorte(p5,"droite");
		r5.setPorte(p6,"bas");
		r6.setPorte(p5, "gauche");
		r7.setPorte(p6,"haut");
		r7.setPorte(p7,"bas");
		r8.setPorte(p7,"haut");
		r8.setPorte(p8,"gauche");
		r9.setPorte(p8, "droite");

		r9.setPorte(p9, "nether");
		r11.setPorte(p9, "nether");
		r11.setPorte(p10,"droite");
		r12.setPorte(p10,"gauche");
		r12.setPorte(p11,"droite");
		r13.setPorte(p11,"gauche");

		//Fin création niveau

		
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(m_keyH);
		this.setFocusable(true);
	}
	
	/**
	 * Lancement du thread principal
	 */
	public void startGameThread() {
		m_gameThread = new Thread(this);
		m_gameThread.start();
	}
	
	public void run() {
		
		double drawInterval = 1000000000/m_FPS; // rafraichissement chaque 0.0166666 secondes
		double nextDrawTime = System.nanoTime() + drawInterval; 
		
		while(m_gameThread != null) { //Tant que le thread du jeu est actif
			
			//Permet de mettre � jour les diff�rentes variables du jeu
			this.update();
			
			//Dessine sur l'�cran le personnage et la map avec les nouvelles informations. la m�thode "paintComponent" doit obligatoirement �tre appel�e avec "repaint()"
			this.repaint();
			
			//Calcule le temps de pause du thread
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				
				Thread.sleep((long)remainingTime);
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * Mise � jour des donn�es des entit�s
	 */
	public void update() {
		player.update();
	}
	
	/**
	 * Affichage des �l�ments
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		m_room.getTileManager().draw(g2);
		player.draw(g2);
		for (int i = 0; i < this.m_room.getEntities().size(); i++){
			this.m_room.getEntities().get(i).draw(g2);
		}
		g2.dispose();
	}
	
	public Room getRoom(){
		return m_room;
	}

	public void setRoom(Room r){
		this.m_room = r;
	}

	
}
