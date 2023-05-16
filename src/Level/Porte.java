package Level;

public class Porte {
	private boolean estOuverte;
	private int idPorte;
	private Room room1;
	private Room room2;
	
	public Porte(int idPorte) {
		this.idPorte = idPorte;
		estOuverte = false; //par defaut, la porte est fermée
	}

	public Porte(int idPorte, Room room1, Room room2){
		this.idPorte = idPorte;
		estOuverte = false; //par defaut, la porte est fermée
		this.room1 = room1;
		this.room2 = room2;
	}

	public boolean estOuverte() {
		return estOuverte;
	}
		
		
	public void ouvrir() {	
		estOuverte = true;
		System.out.println("la porte est maintenant ouverte");
	}
	
	public void fermer() {	
		estOuverte = false;
		System.out.println("la porte est maintenant fermee");
		}
	
	public int getIdPorte() {
		return idPorte;
	}
	
	public Room getOtherRoom(int idRoom){
		if(idRoom == room1.getId()){
			return room2;
		} else if (idRoom == room2.getId()){
			return room1;
		}
		return null;
	}
	

}
