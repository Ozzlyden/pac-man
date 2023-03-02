package com.victor.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.victor.main.Game;
import com.victor.world.Camera;
import com.victor.world.World;

public class Player extends Entity {
	
	public boolean  stay, right, up, left, down; 

	public BufferedImage[] sprite_right;
	private BufferedImage[] sprite_left;
	
	public int lastDir = 1, lastDir_up_down = 1;
	
	public double life = 100, maxLife = 100;
	
	private int frames = 0, maxFrames = 8, index = 0, maxIndex = 2;
	private boolean moved = false;

	
	public boolean isDamaged = false;
	private int damageFrames = 0;
	
	public Player(int x, int y, int width, int height,double speed, BufferedImage sprite) {
		super(x, y, width, height,speed, sprite);
		
		sprite_right = new BufferedImage [4];
		sprite_left = new BufferedImage [4];
		
		//sprite_right[0] = Game.spritesheet.getSprite(32, 0, 16, 16); 
		//sprite_right[1] = Game.spritesheet.getSprite(48, 0, 16, 16); 
		//sprite_right[2] = Game.spritesheet.getSprite(64, 0, 16, 16);
		//sprite_right[3] = Game.spritesheet.getSprite(80, 0, 16, 16);

		//sprite_left[0] = Game.spritesheet.getSprite(32, 16, 16, 16);
		//sprite_left[1] = Game.spritesheet.getSprite(48, 16, 16, 16);
		//sprite_left[2] = Game.spritesheet.getSprite(64, 16, 16, 16);
		//sprite_left[3] = Game.spritesheet.getSprite(80, 16, 16, 16);
		
		for (int i = 0; i < 3; i++) {
			sprite_right[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 16, 16);	
		}
		for (int i = 0; i < 3; i++) {
			sprite_left[i] = Game.spritesheet.getSprite(32 + (i*16), 16, 16, 16);
		}

	}
	
	public void tick() {
		// CAMADA DE RENDER
		depth = 2;
		moved = false;
		
		//LOGICA DE MOVIMENTACAO
		if(right && World.isFree((int) (x + speed), this.getY())) {
			moved = true;
			x+=speed;
			lastDir = 1;
		}
		else if(left && World.isFree((int) (x - speed), this.getY())) {
			moved = true;
			x-=speed;
			lastDir = -1;
		}
		if(up && World.isFree(this.getX(), (int) (y - speed))) {
			//moved = true;
			y-=speed;
			lastDir_up_down = 1;
		}	
		else if(down && World.isFree(this.getX(), (int) (y + speed))) {
			//moved = true;
			y+=speed;
			lastDir_up_down = -1;

		}
		takeCoin();
		
		if(Game.moedas_cont == Game.moedas_atual) {
			//System.out.println("Ganhamos o jogo !");
		}
		
		if(moved) {		//se o player mexer, acada 5 frames, faz uma animacao
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
			
		}
	}
	
	public void takeCoin() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Moeda) {
				if(Entity.isColliding(this, current)) {
					Game.moedas_atual++;
					Game.entities.remove(i);
					return;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		if(moved == true && lastDir == 1) {
			g.drawImage(sprite_right[index], this.getX() - Camera.x,this.getY() - Camera.y,this.getWidth(),this.getHeight(), null);

		}else if(moved == true && lastDir == -1){
			g.drawImage(sprite_left[index], this.getX() - Camera.x,this.getY() - Camera.y,this.getWidth(),this.getHeight(), null);

		}
	}		
}
