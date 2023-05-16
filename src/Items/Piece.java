package Items;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Piece extends Item {
	

	
	
	public Piece(String name, int x, int y, int longX, int longY) {
		super(name,x,y,longX,longY);

		this.getPieceImage();
	}

	BufferedImage l_image = item_image;

	
	public void getPieceImage() {
		//gestion des exceptions
		try {
			item_image = ImageIO.read(getClass().getResource("/item/piece.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
