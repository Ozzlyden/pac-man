package com.victor.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.victor.entities.Enemy1;
import com.victor.entities.Enemy2;
import com.victor.entities.Enemy3;
import com.victor.entities.Entity;
import com.victor.entities.Moeda;
import com.victor.entities.Player;
import com.victor.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	
	
	public World(String path){
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++){
				for(int yy = 0; yy < map.getHeight(); yy++){
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					
					/*
					 * Player (0xFF0026FF)
					 * Enemy 1 (0xFFFF0001)
					 * Enemy 2 (0xFFFF0002)
					 * Moeda  (0xFFFFD818)
					 */
					
					
					if(pixelAtual == 0xFF000000){
						//Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFFFFFFF){
						//Parede
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
					}else if(pixelAtual == 0xFF0026FF) {
						//Player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}
					else if(pixelAtual == 0xFFFFD800) {
						//Moeda
						Moeda moeda = new Moeda(xx*16, yy*16, 16, 16, 0, Entity.MOEDA_SPRITE);
						Game.entities.add(moeda);
						Game.moedas_cont++;
					}
					else if (pixelAtual == 0xFFFF0001) {
						//Enemy1
						Enemy1 enemy1 = new Enemy1(xx*16, yy*16, 16, 16, 0, Entity.ENEMY1_SPRITE);
						Game.entities.add(enemy1);
					}
					else if (pixelAtual == 0xFFFF0002) {
						//Enemy2
						Enemy2 enemy2 = new Enemy2(xx*16, yy*16, 16, 16, 0, Entity.ENEMY2_SPRITE);
						Game.entities.add(enemy2);
					}
					else if (pixelAtual == 0xFFFF0003) {
						//Enemy3
						Enemy3 enemy3 = new Enemy3(xx*16, yy*16, 16, 16, 0, Entity.ENEMY3_SPRITE);
						Game.entities.add(enemy3);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext,int ynext){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public static void restartGame(String level){
		//new Game();
		
		Game.player = new Player(0, 0, 16, 16, 2, Game.spritesheet.getSprite(32, 0, 16, 16));
		Game.entities.clear();
		Game.entities.add(Game.player);
		Game.moedas_atual = 0;
		Game.moedas_cont = 0;
		Game.world= new World("/level1.png");
		return;
	}
	
	public void render(Graphics g){
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
