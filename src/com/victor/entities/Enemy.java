package com.victor.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy extends Entity{
	

	public Enemy(int x, int y, int width, int height,int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, null);
	}
	
	//LOGICA I.A
	public void tick() {
		depth = 0;	//camada render
		
		/*
		//ALOGORITMO *A (IA Melhor)
		if(!isCollidingWithPlayer()) {
			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i((int) (x/16), (int) (y/16));	//posicao inicial
				// Colocamos as posicoes atuais do Player
				Vector2i end = new Vector2i((int) (Game.player.x/16), (int) (Game.player.y/16));	//destino final
				path = AStar.findPath(Game.world, start, end);
				Sound.Clips.effect_enemy3.play();
			}
		}else {
			if(new Random().nextInt(100) < 10) {
				//Sound.hurtEffect.play()
				Game.player.life -= Game.rand.nextInt(10);
				Game.player.isDamaged = true;
			}
		}
		if(new Random().nextInt(100) < 90)	//Nivel de inteligencia
		followPath(path);	//chamndo o AStar	
		*/
		
	}
	
	
	public void render(Graphics g) {
		
	}
}
