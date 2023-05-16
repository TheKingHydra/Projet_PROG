package main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

import Level.Porte;
import Level.Room;
import entity.Player;
import tile.TileManager;

import java.awt.Graphics;
import java.awt.Graphics2D;

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


		//Création de la room 1
		TileManager tileManager = new TileManager(this,"/maps/map.txt");
		m_room = new Room(1, this.player, tileManager);
		
		//Création de la room 2
		TileManager tileManager2 = new TileManager(this, "/maps/map2.txt");
		Room r2 = new Room(2,this.player,tileManager2);
		//Création de la room 3
		TileManager tileManager3 = new TileManager(this, "/maps/map3.txt");
		Room r3 = new Room(3,this.player,tileManager3);
		TileManager tileManager4 = new TileManager(this, "/maps/map2.txt");
		Room r4 = new Room(4,this.player,tileManager4);
		TileManager tileManager5 = new TileManager(this, "/maps/map2.txt");
		Room r5 = new Room(5,this.player,tileManager5);
		TileManager tileManager6 = new TileManager(this, "/maps/map2.txt");
		Room r6 = new Room(6,this.player,tileManager6);
		TileManager tileManager7 = new TileManager(this, "/maps/map2.txt");
		Room r7 = new Room(7,this.player,tileManager7);
		TileManager tileManager8 = new TileManager(this, "/maps/map2.txt");
		Room r8 = new Room(8,this.player,tileManager8);
		TileManager tileManager9 = new TileManager(this, "/maps/map2.txt");
		Room r9 = new Room(9,this.player,tileManager9);
		
		Porte p = new Porte(1,m_room, r2);
		Porte p2 = new Porte(2,r2,r3);
		Porte p3 = new Porte(3,r3, r4);
		Porte p4 = new Porte(4,r4,r5);
		Porte p5 = new Porte(5,r5, r6);
		Porte p6 = new Porte(6,r5,r7);
		Porte p7 = new Porte(7,r7, r8);
		Porte p8 = new Porte(8,r8,r9);
		m_room.setPorte(p, "bas");
		m_room.updatePortes();
		r2.setPorte(p, "haut");
		r2.setPorte(p2,"droite");
		r2.updatePortes();
		r3.setPorte(p2, "gauche");
		r3.setPorte(p3,"droite");
		r3.updatePortes();
		r4.setPorte(p3, "gauche");
		r4.setPorte(p4,"droite");
		r4.updatePortes();
		r5.setPorte(p4, "gauche");
		r5.setPorte(p5,"droite");
		r5.setPorte(p6,"bas");
		r5.updatePortes();
		r6.setPorte(p5, "gauche");
		r6.updatePortes();
		r7.setPorte(p6,"haut");
		r7.setPorte(p7,"bas");
		r7.updatePortes();
		r8.setPorte(p7,"haut");
		r8.setPorte(p8,"gauche");
		r8.updatePortes();
		r9.setPorte(p8, "droite");
		r9.updatePortes();

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
		g2.dispose();
	}
	
	public Room getRoom(){
		return m_room;
	}

	public void setRoom(Room r){
		this.m_room = r;
	}

	
}
