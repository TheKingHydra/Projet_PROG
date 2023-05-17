package Level;

import java.util.ArrayList;

public class Niveau {
	private ArrayList<Room> listeRoom;
	private ArrayList<Porte> listePorte;
	
	public Niveau (ArrayList<Room> listeRoom, ArrayList<Porte> listePorte) {
		this.listeRoom = listeRoom;
		this.listePorte = listePorte;
	}
    
}
