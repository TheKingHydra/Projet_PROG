package Items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Item {
	
	int itemId;
	String itemName;
	int itemXTaille;
	int itemYTaille;
	public int itemYCoord;
	public int itemXCoord;
	
	public BufferedImage item_image;
	
	
	
	public Item(int itemId, String name, int x, int y, int longX, int longY) {
		this.itemId = itemId;
		itemName = name;
		itemXTaille = longX;
		itemYTaille = longY;
		itemXCoord = x;
		itemYCoord = y;
	}

	
	public void draw(Graphics2D a_g2) {
		// r�cup�re l'image du joueur
		BufferedImage l_image = item_image;
		// affiche le personnage avec l'image "image", avec les coordonn�es x et y, et de taille tileSize (16x16) sans �chelle, et 48x48 avec �chelle)
		a_g2.drawImage(l_image, itemXCoord, itemYCoord, itemXTaille, itemYTaille, null);
	}

	
}
