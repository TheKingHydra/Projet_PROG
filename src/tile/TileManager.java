package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

/**
 * 
 * Gestionnaire des tiles du jeu
 *
 */
public class TileManager {
	GamePanel m_gp;			//panel du jeu principal
	Tile[] m_tile;			//tableau de toutes les tiles possibles dans le jeu
	int m_maxTiles = 64;	//nombre maximum de tiles chargeable dans le jeu
	public int m_mapTileNum[][];	//r�partition des tiles dans la carte du jeu
	String mapPath;
	
	/**
	 * Constructeur
	 * @param gp
	 */
	public TileManager(GamePanel gp, String mapPath) {
		this.m_gp =  gp;
		m_tile = new Tile[m_maxTiles];
		m_mapTileNum = new int[gp.MAX_SCREEN_COL][gp.MAX_SCREE_ROW];
		this.getTileImage();
		this.mapPath = mapPath;
		this.loadMap(mapPath);
	}
	
	/**
	 * Chargement de toutes les tuiles du jeu
	 */
	public void getTileImage() {
		try {
			m_tile[0] = new Tile();
			m_tile[0].m_image = ImageIO.read(getClass().getResource("/tiles/GRASS.png"));
			
			m_tile[1] = new Tile();
			m_tile[1].m_image = ImageIO.read(getClass().getResource("/tiles/oak_planks.png"));
			
			m_tile[2] = new Tile();
			m_tile[2].m_image = ImageIO.read(getClass().getResource("/tiles/WATER.png"));
			
			m_tile[3] = new Tile();
			m_tile[3].m_image = ImageIO.read(getClass().getResource("/tiles/dirt_path_top.png"));
			
			m_tile[4] = new Tile();
			m_tile[4].m_image = ImageIO.read(getClass().getResource("/tiles/Grass&Torch up.png"));
			
			m_tile[5] = new Tile();
			m_tile[5].m_image = ImageIO.read(getClass().getResource("/tiles/Grass&Torch left.png"));

			m_tile[6] = new Tile();
			m_tile[6].m_image = ImageIO.read(getClass().getResource("/tiles/Grass&Torch right.png"));

			m_tile[7] = new Tile();
			m_tile[7].m_image = ImageIO.read(getClass().getResource("/tiles/Grass&Torch down.png"));

			m_tile[8] = new Tile();
			m_tile[8].m_image = ImageIO.read(getClass().getResource("/tiles/black_wool.png"));

			m_tile[9] = new Tile();
			m_tile[9].m_image = ImageIO.read(getClass().getResource("/tiles/oak_planks.png"));

			m_tile[10] = new Tile();
			m_tile[10].m_image = ImageIO.read(getClass().getResource("/tiles/oak_log_top.png"));

			m_tile[11] = new Tile();
			m_tile[11].m_image = ImageIO.read(getClass().getResource("/tiles/farmland_moist.png"));

			m_tile[12] = new Tile();
			m_tile[12].m_image = ImageIO.read(getClass().getResource("/tiles/cobblestone.png"));

			m_tile[13] = new Tile();
			m_tile[13].m_image = ImageIO.read(getClass().getResource("/tiles/smooth_stone.png"));

			m_tile[14] = new Tile();
			m_tile[14].m_image = ImageIO.read(getClass().getResource("/tiles/cobblestone_door.png"));

			m_tile[15] = new Tile();
			m_tile[15].m_image = ImageIO.read(getClass().getResource("/tiles/oak_stairs_door.png"));

            m_tile[16] = new Tile();
			m_tile[16].m_image = ImageIO.read(getClass().getResource("/tiles/cobblestone_stairs.png"));

			m_tile[17] = new Tile();
			m_tile[17].m_image = ImageIO.read(getClass().getResource("/tiles/sand.png"));

			m_tile[18] = new Tile();
			m_tile[18].m_image = ImageIO.read(getClass().getResource("/tiles/sandstone_top.png"));

			m_tile[19] = new Tile();
			m_tile[19].m_image = ImageIO.read(getClass().getResource("/tiles/lava_still.png"));

			m_tile[20] = new Tile();
			m_tile[20].m_image = ImageIO.read(getClass().getResource("/tiles/obsidian.png"));

			m_tile[21] = new Tile();
			m_tile[21].m_image = ImageIO.read(getClass().getResource("/tiles/netherrack.png"));

			m_tile[22] = new Tile();
			m_tile[22].m_image = ImageIO.read(getClass().getResource("/tiles/chest.png"));

			m_tile[23] = new Tile();
			m_tile[23].m_image = ImageIO.read(getClass().getResource("/tiles/chest_left.png"));

			m_tile[24] = new Tile();
			m_tile[24].m_image = ImageIO.read(getClass().getResource("/tiles/chest_right.png"));

			m_tile[25] = new Tile();
			m_tile[25].m_image = ImageIO.read(getClass().getResource("/tiles/chest_down.png"));

			m_tile[26] = new Tile();
			m_tile[26].m_image = ImageIO.read(getClass().getResource("/tiles/stone.png"));

			m_tile[27] = new Tile();
			m_tile[27].m_image = ImageIO.read(getClass().getResource("/tiles/glowstone.png"));

			m_tile[28] = new Tile();
			m_tile[28].m_image = ImageIO.read(getClass().getResource("/tiles/fire.png"));

			m_tile[29] = new Tile();
			m_tile[29].m_image = ImageIO.read(getClass().getResource("/tiles/nether_bricks.png"));

			m_tile[30] = new Tile();
			m_tile[30].m_image = ImageIO.read(getClass().getResource("/tiles/nether_quartz_ore.png"));

			m_tile[31] = new Tile();
			m_tile[31].m_image = ImageIO.read(getClass().getResource("/tiles/nether_gold_ore.png"));

			m_tile[32] = new Tile();
			m_tile[32].m_image = ImageIO.read(getClass().getResource("/tiles/netherrack.png"));

			m_tile[33] = new Tile();
			m_tile[33].m_image = ImageIO.read(getClass().getResource("/tiles/nether_bricks.png"));

			m_tile[63] = new Tile();
			m_tile[63].m_image = ImageIO.read(getClass().getResource("/tiles/gameover.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lecture du fichier txt contenant la map et chargement des tuiles correspondantes.
	 */
	public void loadMap(String filePath) {
		//charger le fichier txt de la map
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
			int col = 0;
			int row = 0;
			
			// Parcourir le fichier txt pour r�cup�rer les valeurs
			while (col < m_gp.MAX_SCREEN_COL && row < m_gp.MAX_SCREE_ROW) {
				String line = br.readLine();
				while (col < m_gp.MAX_SCREEN_COL) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					m_mapTileNum [col][row] = num;
					col++;
				}
				if (col == m_gp.MAX_SCREEN_COL) {
					col = 0;
					row ++;
				}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Affichage de la carte avec les diff�rentes tuiles
	 * @param g2
	 */
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while (col < m_gp.MAX_SCREEN_COL && row < m_gp.MAX_SCREE_ROW) {
			int tileNum = m_mapTileNum[col][row];
			
			g2.drawImage(m_tile[tileNum].m_image, x, y, m_gp.TILE_SIZE, m_gp.TILE_SIZE, null);
			col ++;
			x += m_gp.TILE_SIZE;
			if (col == m_gp.MAX_SCREEN_COL) {
				col = 0;
				row ++;
				x = 0;
				y += m_gp.TILE_SIZE;
			}
		}
		
	}

	public void setTuile(int x, int y, int val){
		m_mapTileNum[x][y] = val;
	}

	public int getTuile(int x, int y){
		return m_mapTileNum[x][y];
	}


}
