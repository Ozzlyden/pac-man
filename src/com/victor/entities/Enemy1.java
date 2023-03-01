package com.victor.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.victor.main.Game;
import com.victor.world.AStar;
import com.victor.world.Camera;
import com.victor.world.Vector2i;

public class Enemy1 extends Entity{
	
	private int frames = 0, maxFrames = 15, index = 0, maxIndex = 1;
	private BufferedImage[] spriteEnemy1;

	private int life = 2;
	public boolean ghostMode = false;
	public int ghostFrames = 0;
	
	public int nextTime = Entity.rand.nextInt(60*5 - 60*3) + 60*3;	//Tempo de ghostMode aleatorio


	public Enemy1(int x, int y, int width, int height,int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, null);
		
		spriteEnemy1 = new BufferedImage [2];
		
		spriteEnemy1[0] = Game.spritesheet.getSprite(0,48, 16, 16);
		spriteEnemy1[1] = Game.spritesheet.getSprite(16, 48, 16, 16);
		
	}
	
	//LOGICA I.A
		public void tick() {
			depth = 0;	//camada render
			
			if(ghostMode ==  false){
			//ALOGORITMO *A (IA Melhor
				if(path == null || path.size() == 0) {
					Vector2i start = new Vector2i((int) (x/16), (int) (y/16));	//posicao inicial
					// Colocamos as posicoes atuais do Player
					Vector2i end = new Vector2i((int) (Game.player.x/16), (int) (Game.player.y/16));	//destino final
					path = AStar.findPath(Game.world, start, end);
					//Sound.Clips.effect_enemy3.play();
					}
			else{
				if(new Random().nextInt(100) < 10) {
					//Sound.hurtEffect.play()
					//Game.player.life -= Entity.rand.nextInt(10);
					//Game.player.isDamaged = true;
				}
			}
			if(new Random().nextInt(100) < 90) {	//Nivel de inteligencia
			followPath(path);	//chamndo o AStar
				}
			}
			
			ghostFrames++;
			if(ghostFrames == nextTime) {
				nextTime = Entity.rand.nextInt(60*5 - 60*3) + 60*3;
				ghostFrames = 0;
				if(ghostMode == false) {
					System.out.print("Modo fantasma");
					ghostMode = true;
				}else {
					ghostMode = false;
				}
			}
		}
		
		
		public void render(Graphics g) {
			super.render(g);
			g.drawImage(spriteEnemy1[index],this.getX() - Camera.x, this.getY() - Camera.y, null);

		}
	}