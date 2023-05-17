package entity;

import java.awt.image.BufferedImage;

import Items.Item;

public class Chest extends Entity {
    
    private Item contenu;
    private boolean estDispo;

    public Chest (int entityId, String name, int x, int y, BufferedImage img, Item contenu){
        this.entityId = entityId;
        this.contenu = contenu;
        m_x = x;
        m_y = y;
        m_idleImage = img;
        m_speed = 0;
        this.name = name;
        this.estDispo = true;
    }

    public Item getContenu(){
        return contenu;
    }

    public boolean getDispo(){
		return this.estDispo;
	}

	public void setDispo(boolean b){
		this.estDispo = b;
	}
}
