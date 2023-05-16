package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

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
	

}
