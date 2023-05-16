package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;
import tile.TileManager;

/**
 * Gestionnaire d'�v�nements (touche clavier)
 *
 */
public class KeyHandler implements KeyListener{

	private Player player;	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// r�cup�re le code du boutton appuy�
		int code = e.getKeyCode();
		//System.out.println(code);
		player.deplacement(code);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	public void setPlayer(Player player){
		this.player = player;
	}

}
