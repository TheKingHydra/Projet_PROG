package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import tile.TileManager;

/**

 * Défintition du comportement d'un joueur

 *
 */
public class Player extends Entity{

	GamePanel m_gp;
	KeyHandler m_keyH;

	int monnaie;

	int step;

	
	/**
	 * Constructeur de Player
	 * @param a_gp GamePanel, pannel principal du jeu
	 * @param a_keyH KeyHandler, gestionnaire des touches 
	 */
	public Player(GamePanel a_gp, KeyHandler a_keyH) {
		this.m_gp = a_gp;
		this.m_keyH = a_keyH;
		this.setDefaultValues();
		this.getPlayerImage();
		this.monnaie = 0;
	}
	
	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	protected void setDefaultValues() {
		m_x = 48;
		m_y = 48;
		m_speed = 4;
	}
	
	/**

	 * Récupération de l'image du personnage

	 */
	public void getPlayerImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/Player/superhero.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Mise � jour des donn�es du joueur
	 */
	public void update() {
		
		
		
	}
	
	/**
	 * Affichage du l'image du joueur dans la fen�tre du jeu
	 * @param a_g2 Graphics2D 
	 */
	public void draw(Graphics2D a_g2) {

		// recupère l'image du joueur

		BufferedImage l_image = m_idleImage;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, m_x, m_y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
	}
	


	public void set_monnaie(int n){
		monnaie = n;
	}

	public int get_monnaie(){
		return(monnaie);
	}

	public void add_monnaie(int nb){
		monnaie += nb;
		if(monnaie>=10){
			System.out.println("Le solde de monnaie a dépassé 10.");
		}
	}


	public void setStep(int step){
		this.step = step;
	}

	public int getStep(){
		return step;

	}
	
	public void deplacement(int code){
		if(code == 37 || code == 81){
			if(!collide(1)){
				m_x = m_x-getStep();
			}
		}
		if(code == 38 || code == 90){
			if(!collide(2)){
				m_y = m_y-getStep();
			}
		}
		if(code == 39 || code == 68){
			if(!collide(3)){
				m_x = m_x+getStep();
			}
		}
		if(code == 40 || code == 83){
			if(!collide(4)){
				m_y = m_y+getStep();
			}
		}
	}

	public boolean collide(int i){
		int tilex = (m_x/getStep());
		int tiley = (m_y/getStep());
		TileManager tileManager = m_gp.getRoom().getTileManager();
		//Liste des cases autorisées.
		boolean autorise;
		if(i == 1){
			if (tilex == 0){
				//Changer Room
				m_gp.setRoom(m_gp.getRoom().changerRoom());
				m_x = getStep()*(m_gp.MAX_SCREEN_COL-1);
				return true;
			}
			int val = tileManager.getTuile(tilex-1,tiley);
			autorise = (val == 8 || val == 9 || val == 10 || val == 11 || val == 12 || val == 13 || val == 14 || val == 15);
			if (autorise){ //Liste des cases autorisées.
				return false;
			}
			
			return !(val==0);
		}
		if(i == 2){
			if (tiley == 0){
				//Changer Room
				m_gp.setRoom(m_gp.getRoom().changerRoom());
				m_y = getStep()*(m_gp.MAX_SCREE_ROW-1);
				return true;
			}
			int val = tileManager.getTuile(tilex,tiley-1);
			autorise = (val == 8 || val == 9 || val == 10 || val == 11 || val == 12 || val == 13 || val == 14 || val == 15);
			if (autorise){
				return false;
			}
			
			return !(val==0);
		}
		if(i == 3){
			if (tilex == 15){
				//Changer Room
				m_gp.setRoom(m_gp.getRoom().changerRoom());
				m_x = 0;
				return true;
			}
			int val = tileManager.getTuile(tilex+1,tiley);
			autorise = (val == 8 || val == 9 || val == 10 || val == 11 || val == 12 || val == 13 || val == 14 || val == 15);
			if (autorise){
				return false;
			}
			
			return !(val==0);
		}
		if(i == 4){
			if (tiley == 11){
				//Changer Room
				m_gp.setRoom(m_gp.getRoom().changerRoom());
				m_y = 0;
				return true;
			}
			int val = tileManager.getTuile(tilex,tiley+1);
			autorise = (val == 8 || val == 9 || val == 10 || val == 11 || val == 12 || val == 13 || val == 14 || val == 15);
			if (autorise){
				return false;
			}
			return !(val==0);
		}
		
		return false;
	}

}
