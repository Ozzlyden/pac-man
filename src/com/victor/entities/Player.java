package com.victor.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.world.World;

public class Player extends Entity {
	
	public boolean  stay, right, up, left, down; 
	
	public Player(int x, int y, int width, int height,double speed, BufferedImage sprite) {
		super(x, y, width, height,speed, sprite);	
	}
	
	public void tick() {
		// CAMADA DE RENDER
		depth = 2;
		
		//LOGICA DE MOVIMENTACAO
		if(right && World.isFree((int) (x + speed), this.getY())) {
			x+=speed;
		}
		else if(left && World.isFree((int) (x - speed), this.getY())) {
			x-=speed;
		}
		if(up && World.isFree(this.getX(), (int) (y - speed))) {
			y-=speed;
		}	
		else if(down && World.isFree(this.getX(), (int) (y + speed))) {
			y+=speed;
		}
	}
		
}