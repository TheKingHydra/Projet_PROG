package Level;

import entity.Player;
import tile.TileManager;

import java.util.ArrayList;

import entity.Entity;

public class Room {
	private int idRoom;
	private Porte porteHaut;
	private Porte porteBas;
	private Porte porteGauche;
	private Porte porteDroite;
    private Porte porteNether;
    private Player player;
	private TileManager tileManager;
    private ArrayList<Entity> entityTab;

	public Room(int idRoom, Porte porteHaut, Porte porteBas, Porte porteGauche, Porte porteDroite) {
		this.idRoom = idRoom;
		this.porteHaut = porteHaut;
		this.porteBas = porteBas;
		this.porteGauche = porteGauche;
		this.porteDroite = porteDroite;
	}
	
	public Room(int idRoom, Player player, TileManager tileManager, ArrayList<Entity> entities) {
		this.idRoom = idRoom;
		this.porteHaut = null;
		this.porteBas = null;
		this.porteGauche = null;
		this.porteDroite = null;
        this.tileManager = tileManager;
        this.player = player;
        this.entityTab = entities;
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
        if(s == "nether"){
            porteNether = p;
        }
    }

    public Room changerRoom(){
        Room r = this;
        int tilex = (player.m_x/player.getStep());
		int tiley = (player.m_y/player.getStep());
        int val = tileManager.getTuile(tilex, tiley);
        if (val == 1 || val == 3 || val == 18 || val == 21 || val == 29) {
        //   Planks   /   Path   /  Sandstone /  Netherrack / Nether Bricks
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
        if (val == 21){
            if (tilex == 7 && idRoom == 9){
                if (tiley == 4) {
                    player.m_y = player.getStep()*3;
                } else if (tiley == 5){
                    player.m_y = player.getStep()*4;
                }
                r = porteNether.getOtherRoom(this.idRoom);
                player.m_x = player.getStep()*3;
            } else if (tilex == 5 && idRoom == 9){
                if (tiley == 4) {
                    player.m_y = player.getStep()*3;
                } else if (tiley == 5){
                    player.m_y = player.getStep()*4;
                }
                r = porteNether.getOtherRoom(this.idRoom);
                player.m_x = player.getStep()*1;
            } else if (tilex == 3 && idRoom == 11){
                if (tiley == 4){
                   player.m_y = player.getStep()*5;
                } else if (tiley == 3) {
                    player.m_y = player.getStep()*4;
                }
                r = porteNether.getOtherRoom(this.idRoom);
                player.m_x = player.getStep()*7;
            } else if (tilex == 1 && idRoom == 11){
                
                if (tiley == 4){
                    player.m_y = player.getStep()*5;
                } else if (tiley == 3) {
                    player.m_y = player.getStep()*4;
                }
                r = porteNether.getOtherRoom(this.idRoom);
                player.m_x = player.getStep()*5;
            }
        }
        
        return r;
        
    }

    public ArrayList<Entity> getEntities(){
        return this.entityTab;
    }

}

