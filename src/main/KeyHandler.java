package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;

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
		if(code == 37){
			
			player.m_x = player.m_x-player.getStep();
		}
		if(code == 38){
			player.m_y = player.m_y-player.getStep();
		}
		if(code == 39){
			
			player.m_x = player.m_x+player.getStep();
		}
		if(code == 40){
			player.m_y = player.m_y+player.getStep();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}


	public void setPlayer(Player player){
		this.player = player;
	}
}
