package entities;

import java.util.Random;

import graphics.SpriteSheet;
import main.Main;

public class AlienCitizen extends Entity {
	public int height = 100;
	public int width = 100;
	public int moveSpeed = 2;
	int tick = 0;
	int randomDialog;
	Random random = new Random();
	SpriteSheet spriteSheet;

	public AlienCitizen(String link, int width, int height) {
		super(link, 384, 192);
		spriteSheet = new SpriteSheet(sprite, 96, 96);
		x = 600;
		y = 0;
		xvel = moveSpeed;
		sprite = spriteSheet.getTexture(0, 0);
	}

	public void update() {
		x += xvel;
		System.out.println(y + yvel + height);
		gravity();
		if(tick > 100){
			tick = 0;
			xvel *= -1;
			dir += 1 - (dir*2);
		}
		walk();
		tick++;
	}
	
	public void walk(){
		sprite = spriteSheet.getTexture((tick/10)%4, dir);
	}
	
	public void interact() {
		randomDialog = random.nextInt(4);
		switch(randomDialog){
		case 0:
			Main.getInstance().level.dialouge("Alien: Hello Human", "Me: Wasup");
			break;
		case 1:
			Main.getInstance().level.dialouge("Alien: Who are you?", "Me: Human...");
			break;
		case 2:
			Main.getInstance().level.dialouge("Alien: ...", "Me: ...");
			break;
		case 3:
			Main.getInstance().level.dialouge("Alien: Lmao", "Me: ??");
			break;
		case 4:
			Main.getInstance().level.dialouge("Alien: Pranked", "Me: Where is camera?");
			break;
		}
		

	}
	
	public void gravity() {
		if (y + yvel + height > GROUND) {
			inAir = false;
			yvel = 0;
			y = GROUND - height;
		} else {
			if (inAir) {
				yvel++;
			}
		}
		y += yvel;
		hitbox.update(x, y);
	}
}
