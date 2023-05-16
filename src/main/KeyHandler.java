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
	private TileManager tileManager;

	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// r�cup�re le code du boutton appuy�
		int code = e.getKeyCode();
		//System.out.println(code);
		deplacement(code);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}


	public void deplacement(int code){
		if(code == 37){
			if(!collide(1)){
				player.m_x = player.m_x-player.getStep();
			}
		}
		if(code == 38){
			if(!collide(2)){
				player.m_y = player.m_y-player.getStep();
			}
		}
		if(code == 39){
			if(!collide(3)){
				player.m_x = player.m_x+player.getStep();
			}
		}
		if(code == 40){
			if(!collide(4)){
				player.m_y = player.m_y+player.getStep();
			}
		}
	}

	public void setPlayer(Player player){
		this.player = player;
	}

	public boolean collide(int i){
		int tilex = (player.m_x/player.getStep());
		int tiley = (player.m_y/player.getStep());
		if(i == 1){
			int val = tileManager.getTuile(tilex-1,tiley);
			return !(val==0);
		}
		if(i == 2){
			int val = tileManager.getTuile(tilex,tiley-1);
			return !(val==0);
		}
		if(i == 3){
			int val = tileManager.getTuile(tilex+1,tiley);
			return !(val==0);
		}
		if(i == 4){
			int val = tileManager.getTuile(tilex,tiley+1);
			return !(val==0);
		}
		return false;
	}
	
	
	public TileManager getTileManager() {
		return tileManager;
	}

	public void setTileManager(TileManager tileManager) {
		this.tileManager = tileManager;
	}
}
