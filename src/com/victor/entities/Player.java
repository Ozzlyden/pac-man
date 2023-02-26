package com.victor.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;
import com.victor.world.Camera;
import com.victor.world.World;

public class Player extends Entity {
	
	public boolean  stay, right, up, left, down; 
	
	public BufferedImage sprite_left, sprite_right, sprite_up, sprite_down;
	
	public int lastDir = 1, lastDir_up_down = 1;
	
	public Player(int x, int y, int width, int height,double speed, BufferedImage sprite) {
		super(x, y, width, height,speed, sprite);
		
		sprite_left = Game.spritesheet.getSprite(32, 16, 16, 16); 
		sprite_right = Game.spritesheet.getSprite(32, 0, 16, 16); 
		sprite_up = Game.spritesheet.getSprite(32, 48, 16, 16); 
		sprite_down = Game.spritesheet.getSprite(32, 32, 16, 16); 
	}
	
	public void tick() {
		// CAMADA DE RENDER
		depth = 2;
		
		//LOGICA DE MOVIMENTACAO
		if(right && World.isFree((int) (x + speed), this.getY())) {
			x+=speed;
			lastDir = 1;
		}
		else if(left && World.isFree((int) (x - speed), this.getY())) {
			x-=speed;
			lastDir = -1;
		}
		if(up && World.isFree(this.getX(), (int) (y - speed))) {
			y-=speed;
			lastDir_up_down = 1;
		}	
		else if(down && World.isFree(this.getX(), (int) (y + speed))) {
			y+=speed;
			lastDir_up_down = -1;

		}
	}
	
	public void render(Graphics g) {
		if(lastDir == 1) {
			super.render(g);
			g.drawImage(sprite_right, this.getX() - Camera.x,this.getY() - Camera.y,this.getWidth(),this.getHeight(), null);

		}else if(lastDir == -1){
			g.drawImage(sprite_left, this.getX() - Camera.x,this.getY() - Camera.y,this.getWidth(),this.getHeight(), null);

		}else if(lastDir_up_down == 1) {
			g.drawImage(sprite_up, this.getX() - Camera.x,this.getY() - Camera.y,this.getWidth(),this.getHeight(), null);

		}else {
			g.drawImage(sprite_down, this.getX() - Camera.x,this.getY() - Camera.y,this.getWidth(),this.getHeight(), null);

		}
	}
		
}
