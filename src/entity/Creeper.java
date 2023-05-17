package entity;

import java.awt.image.BufferedImage;

public class Creeper extends Entity{
    
    public Creeper(int entityId, String name, int x, int y, BufferedImage img){
        this.entityId = entityId;
        m_x = x;
        m_y = y;
        m_idleImage = img;
        m_speed = 0;
        this.name = name;
    }
}
