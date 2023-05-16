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
            System.out.println("porteBas");
            tileManager.setTuile(8, 11, 14);
            tileManager.setTuile(7, 11, 15);
        }
        if (porteHaut != null){
            System.out.println("porteHaut");
            tileManager.setTuile(8, 0, 11);
            tileManager.setTuile(7, 0, 10);
        }
        if (porteGauche != null){
            System.out.println("porteGauche");
            tileManager.setTuile(0, 6, 13);
            tileManager.setTuile(0, 5, 12);
        }
        if (porteDroite != null){
            System.out.println("porteDroite");
            tileManager.setTuile(15, 6, 8);
            tileManager.setTuile(15, 5, 9);
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
        if (tileManager.getShouldChange()){
            Room r = this;
            if(player.m_x == 0) {
                r = porteGauche.getOtherRoom(this.idRoom);
            }
            if(player.m_x == 720) {
                r = porteDroite.getOtherRoom(this.idRoom);
            }
            if(player.m_y == 0) {
                r = porteHaut.getOtherRoom(this.idRoom);
            }
            if(player.m_y == 576) {
                r = porteBas.getOtherRoom(this.idRoom);
            }
            return r;
        } else {
            return null;
        }
    }
}
