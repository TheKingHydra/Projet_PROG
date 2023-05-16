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

    public int getId(){
        return idRoom;
    }

    public TileManager getTileManager(){
        return this.tileManager;
    }

    public void updatePortes(){
        if (porteBas != null){
            
        }
        if (porteHaut != null){
            
        }
        if (porteGauche != null){
            
        }
        if (porteDroite != null){
            
        }
    }

    public void setPorte(Porte p, String s){
        if(s == "gauche"){
            porteGauche = p;
        }
        if(s == "droite"){
            porteDroite = p;
        }
        if(s == "bas"){
            porteBas = p;
        }
        if(s == "haut"){
            porteHaut = p;
        }
    }

    public Room changerRoom(){
        Room r = this;
        int tilex = (player.m_x/player.getStep());
		int tiley = (player.m_y/player.getStep());
        int val = tileManager.getTuile(tilex, tiley);
        if (val == 1 || val == 3) {
            if(tilex == 0) {
                r = porteGauche.getOtherRoom(this.idRoom);
                player.m_x = player.getStep()*(player.getMaxScreenCol()-1);
            }   
            if(tilex == 14) {
                r = porteDroite.getOtherRoom(this.idRoom);
                player.m_x = 0;
            }
            if(tiley == 0) {
                r = porteHaut.getOtherRoom(this.idRoom);
                player.m_y = player.getStep()*(player.getMaxScreenRow()-1);
            }
            if(tiley == 10) {
                r = porteBas.getOtherRoom(this.idRoom);
                player.m_y = 0;
            }
        }
        return r;
        
    }
}

