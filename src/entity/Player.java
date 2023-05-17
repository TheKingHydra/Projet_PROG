package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Items.Item;
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

	private ArrayList<Item> items;
	boolean isCollidingChest = false;
	

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
		this.items = new ArrayList<Item>();
	}
	
	/**
	 * Initialisation des donn�es membres avec des valeurs par d�faut
	 */
	protected void setDefaultValues() {
		m_x = 7*m_gp.TILE_SIZE;
		m_y = 5*m_gp.TILE_SIZE;
		m_speed = 4;
	}
	
	/**

	 * Récupération de l'image du personnage

	 */
	public void getPlayerImage() {
		//gestion des expections 
		try {
			m_idleImage = ImageIO.read(getClass().getResource("/Player/STEVE_EPEE.png"));
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
		setCollidingChest(false);
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

		//Vérif qu'il collide ou pas avec un coffre
		int tilex = m_x/getStep();
		int tiley = m_y/getStep();
		for (int j = 0; j < m_gp.getRoom().getEntities().size() ; j++){
			int etilex = m_gp.getRoom().getEntities().get(j).m_x/getStep();
			int etiley = m_gp.getRoom().getEntities().get(j).m_y/getStep();
			if (etiley == tiley && etilex == tilex-1){
				if(m_gp.getRoom().getEntities().get(j).name == "chest"){
					setCollidingChest(true);
				} else {
					setCollidingChest(false);
				}
			} else if (etiley == tiley && etilex == tilex+1){
				if(m_gp.getRoom().getEntities().get(j).name == "chest"){
					setCollidingChest(true);
				} else {
					setCollidingChest(false);
				}
			} else if (etiley == tiley-1 && etilex == tilex){
				if(m_gp.getRoom().getEntities().get(j).name == "chest"){
					setCollidingChest(true);
				} else {
					setCollidingChest(false);
				}
			} else if (etiley == tiley+1 && etilex == tilex){
				if(m_gp.getRoom().getEntities().get(j).name == "chest"){
					setCollidingChest(true);
				} else {
					setCollidingChest(false);
				}
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
				return true;
			}
			int val = tileManager.getTuile(tilex-1,tiley);

			//Vérifie que ça collide pas avec une entité
			for (int j = 0; j < m_gp.getRoom().getEntities().size() ; j++){
				int etilex = m_gp.getRoom().getEntities().get(j).m_x/getStep();
				int etiley = m_gp.getRoom().getEntities().get(j).m_y/getStep();
				if (etiley == tiley && etilex == tilex-1){
					if(m_gp.getRoom().getEntities().get(j).name == "chest"){
						return true;
					}
				}
				
			}

			autorise = (val == 1 || val == 3 || val == 4 || val == 5 || val == 6 || val == 7 || val == 14 || val == 15 || val == 16 || val == 17 || val == 18 || val == 21 || val == 26);
			if (autorise){ //Liste des cases autorisées.
				return false;
			}
			
			return !(val==0);
		}
		if(i == 2){
			if (tiley == 0){
				//Changer Room
				m_gp.setRoom(m_gp.getRoom().changerRoom());
				return true;
			}
			int val = tileManager.getTuile(tilex,tiley-1);

			//Vérifie que ça collide pas avec une entité
			for (int j = 0; j < m_gp.getRoom().getEntities().size() ; j++){
				int etilex = m_gp.getRoom().getEntities().get(j).m_x/getStep();
				int etiley = m_gp.getRoom().getEntities().get(j).m_y/getStep();
				if (etiley == tiley-1 && etilex == tilex){
					if(m_gp.getRoom().getEntities().get(j).name == "chest"){
						return true;
					}
				}
			}

			autorise = (val == 1 || val == 3 || val == 4 || val == 5 || val == 6 || val == 7 || val == 14 || val == 15 || val == 16 || val == 17 || val == 18 || val == 21 || val == 26);
			if (autorise){
				return false;
			}
			
			return !(val==0);
		}
		if(i == 3){
			if (tilex == 14){
				//Changer Room
				m_gp.setRoom(m_gp.getRoom().changerRoom());
				return true;
			}
			int val = tileManager.getTuile(tilex+1,tiley);

			//Vérifie que ça collide pas avec une entité
			for (int j = 0; j < m_gp.getRoom().getEntities().size() ; j++){
				int etilex = m_gp.getRoom().getEntities().get(j).m_x/getStep();
				int etiley = m_gp.getRoom().getEntities().get(j).m_y/getStep();
				if (etiley == tiley && etilex == tilex+1){
					if(m_gp.getRoom().getEntities().get(j).name == "chest"){
						return true;
					}
				}
			}

			autorise = (val == 1 || val == 3 || val == 4 || val == 5 || val == 6 || val == 7 || val == 14 || val == 15 || val == 16 || val == 17 || val == 18 || val == 21 || val == 26);
			if (autorise){
				return false;
			}
			
			return !(val==0);
		}
		if(i == 4){
			if (tiley == 10){
				//Changer Room
				m_gp.setRoom(m_gp.getRoom().changerRoom());
				return true;
			}
			int val = tileManager.getTuile(tilex,tiley+1);

			//Vérifie que ça collide pas avec une entité
			for (int j = 0; j < m_gp.getRoom().getEntities().size() ; j++){
				int etilex = m_gp.getRoom().getEntities().get(j).m_x/getStep();
				int etiley = m_gp.getRoom().getEntities().get(j).m_y/getStep();
				if (etiley == tiley+1 && etilex == tilex){
					if(m_gp.getRoom().getEntities().get(j).name == "chest"){
						return true;
					}
				}
			}


			autorise = (val == 1 || val == 3 || val == 4 || val == 5 || val == 6 || val == 7 || val == 14 || val == 15 || val == 16 || val == 17 || val == 18 || val == 21 || val == 26);
			if (autorise){
				return false;
			}
			return !(val==0);
		}
		
		return false;
	}

	public int getMaxScreenRow(){
		return m_gp.MAX_SCREE_ROW;
	}

	public int getMaxScreenCol(){
		return m_gp.MAX_SCREEN_COL;
	}

	public boolean isCollidingChest() {
		return isCollidingChest;
	}

	public void setCollidingChest(boolean isCollidingChest) {
		this.isCollidingChest = isCollidingChest;
	}

	public void interact(){
		if(isCollidingChest){
			ArrayList<Entity> entities = m_gp.getRoom().getEntities();
			for (int i = 0; i < entities.size();i++){
				if(entities.get(i).name == "chest"){
					int	tilex = m_x/getStep();
					int tiley = m_y/getStep();
					int etilex = entities.get(i).m_x/getStep();
					int etiley = entities.get(i).m_y/getStep();
					Entity c = new Chest(-1,"",0,0,null,null);
					if (etilex == tilex && etiley == tiley-1) {
						c = entities.get(i);
						if (((Chest) c).getDispo()){
							Item a = ((Chest) c).getContenu();
							items.add(a);
						}
						((Chest) c).setDispo(false);
					}
					if (etilex == tilex && etiley == tiley+1) {
						c = entities.get(i);
						if (((Chest) c).getDispo()){
							Item a = ((Chest) c).getContenu();
							items.add(a);
						}
						((Chest) c).setDispo(false);
					}
					if (etilex == tilex-1 && etiley == tiley) {
						c = entities.get(i);
						if (((Chest) c).getDispo()){
							Item a = ((Chest) c).getContenu();
							items.add(a);
						}
						((Chest) c).setDispo(false);
					}
					if (etilex == tilex+1 && etiley == tiley) {
						c = entities.get(i);
						if (((Chest) c).getDispo()){
							Item a = ((Chest) c).getContenu();
							items.add(a);
						}
						((Chest) c).setDispo(false);
					}
					System.out.println(items.size());
				}
			}
			
			
		} else {
			int tilex = m_x/getStep();
			int tiley = m_y/getStep();
			if(m_gp.getRoom().getId()==9){
				if ((tilex == 5 || tilex == 7)&&(tiley == 4 || tiley == 5)){
					m_gp.setRoom(m_gp.getRoom().changerRoom());
				}
			}
			if(m_gp.getRoom().getId()==11){
				if ((tilex == 0 || tilex == 2)&&(tiley == 4 || tiley == 5)){
					m_gp.setRoom(m_gp.getRoom().changerRoom());
				}
			}
		}
	}

	public ArrayList<Item> getItems(){
		return items;
	}

	public void setImage(BufferedImage img){

	}
}
