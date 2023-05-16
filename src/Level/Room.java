package Level;

import entity.Player;
import tile.TileManager;

public class Room {
	private int idRoom;
	private Porte porteHaut;
	private Porte porteBas;
	private Porte porteGauche;
	private Porte porteDroite;
    private Player player;
	private TileManager tileManager;

	public Room(int idRoom, Porte porteHaut, Porte porteBas, Porte porteGauche, Porte porteDroite) {
		this.idRoom = idRoom;
		this.porteHaut = porteHaut;
		this.porteBas = porteBas;
		this.porteGauche = porteGauche;
		this.porteDroite = porteDroite;
	}
	
	public Room(int idRoom, Player player, TileManager tileManager) {
		this.idRoom = idRoom;
		this.porteHaut = null;
		this.porteBas = null;
		this.porteGauche = null;
		this.porteDroite = null;
        this.tileManager = tileManager;
        this.player = player;
	}

    public Player getPlayer(){
        return this.player;
    }

    public TileManager getTileManager(){
        return this.tileManager;
    }
}
