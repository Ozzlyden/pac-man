package com.victor.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;
import com.victor.world.Camera;

public class Moeda extends Entity{
	
	private BufferedImage[] spriteMoeda;
	
	private int frames = 0, maxFrames = 20, index = 0, maxIndex = 2;

	public Moeda(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		spriteMoeda  = new BufferedImage [3];
		
		spriteMoeda[0] = Game.spritesheet.getSprite(144, 32, 16, 16);
		//spriteMoeda[1] = Game.spritesheet.getSprite(16, 128, 16, 16);
		//spriteMoeda[2] = Game.spritesheet.getSprite(32, 128, 16, 16);
		
	}

	public void tick() {
		depth = 0;
		
		/*
		//LOGICA ANIMACAO
		frames++;
		if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
		*/
	}
	
	public void render(Graphics g) {
		g.drawImage(spriteMoeda[index],this.getX() - Camera.x, this.getY() - Camera.y, null);
	}
	
}
