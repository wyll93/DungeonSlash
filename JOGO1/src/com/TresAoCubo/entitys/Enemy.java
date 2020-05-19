package com.TresAoCubo.entitys;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.TresAoCubo.main.Game;
import com.TresAoCubo.world.World;

public class Enemy extends Entity{
	private int speed = 1;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		if(Game.rand.nextInt(100) < 95) {
			if((int)x < Game.player.getX() && World.isFree((int)x+speed, this.getY()) && 
					!(isColidding((int)x+speed, this.getY()))){
				x += speed;
				
			} else if ((int)x > Game.player.getX() && World.isFree((int)x-speed, this.getY()) &&
					!(isColidding((int)x-speed, this.getY()))){
				x -= speed;
				
			}else if((int)y < Game.player.getY()  && World.isFree(this.getX(), (int)y+speed) &&
					!(isColidding(this.getX(), (int)y+speed))){
				y += speed;
				
			} else if ((int)y > Game.player.getY()  && World.isFree(this.getX(), (int)y-speed) &&
					!(isColidding(this.getX(), (int)y-speed))){
				y -= speed;
			}
		}
	}
	
	public boolean isColidding(int xNext, int yNext) {
		Rectangle enemyCurrent = new Rectangle(xNext, yNext, World.TILE_SIZE, World.TILE_SIZE);
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this)
				continue;
			Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), World.TILE_SIZE, World.TILE_SIZE);
			if(enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
		}
		return false;
	}
}
