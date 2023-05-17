package Level;

import java.util.ArrayList;

public class Niveau {
	private ArrayList<Room> listeRoom;
	
	private ArrayList<Porte> listePorte;
	
	public Niveau (ArrayList<Room> listeRoom, ArrayList<Porte> listePorte) {
		this.listeRoom = listeRoom;
		this.listePorte = listePorte;
	}

    public ArrayList<Room> getListeRoom() {
		return listeRoom;
	}

	public void setListeRoom(ArrayList<Room> listeRoom) {
		this.listeRoom = listeRoom;
	}

	public ArrayList<Porte> getListePorte() {
		return listePorte;
	}

}
