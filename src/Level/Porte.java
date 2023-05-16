package Level;

public class Porte {
	private boolean estOuverte;
	private int idPorte;
	
	public Porte(int idPorte) {
		this.idPorte = idPorte;
		estOuverte = false; //par defaut, la porte est fermée
		
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
	
	

}
