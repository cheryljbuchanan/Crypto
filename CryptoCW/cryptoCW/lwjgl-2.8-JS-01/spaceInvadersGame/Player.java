package spaceInvadersGame;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

public class Player {
	
	Vector2f shipSizeHalved = new Vector2f(30.0f, 10.0f);
	Vector2f shipPos = new Vector2f(400f, 50f);
	Vector2f shipVel = new Vector2f(0.0f, 0.0f);
	Vector2f shipAcc = new Vector2f(0.0f, 0.0f);
	
	ArrayList<Shot> fired = new ArrayList<Shot>();

	public void drawCannon(){
		
		GL11.glBegin(GL11.GL_QUADS);
		
		GL11.glColor3f(1.0f, 0.0f, 0.0f);
		
		GL11.glVertex2d(shipPos.x + (shipSizeHalved.x/4), (shipPos.y + 10f) - (shipSizeHalved.y*2));
		GL11.glVertex2d(shipPos.x + (shipSizeHalved.x/4), (shipPos.y + 10f) + (shipSizeHalved.y*2));
		GL11.glVertex2d(shipPos.x - (shipSizeHalved.x/4), (shipPos.y + 10f) + (shipSizeHalved.y*2));
		GL11.glVertex2d(shipPos.x - (shipSizeHalved.x/4), (shipPos.y + 10f) - (shipSizeHalved.y*2));
		
		GL11.glVertex2d(shipPos.x + shipSizeHalved.x, shipPos.y - shipSizeHalved.y);
		GL11.glVertex2d(shipPos.x + shipSizeHalved.x, shipPos.y + shipSizeHalved.y);
		GL11.glVertex2d(shipPos.x - shipSizeHalved.x, shipPos.y + shipSizeHalved.y);
		GL11.glVertex2d(shipPos.x - shipSizeHalved.x, shipPos.y - shipSizeHalved.y);
		GL11.glEnd();
	}

	public void move() {
		// TODO Auto-generated method stub
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))         shipVel.x = -150.0f;
		else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))   shipVel.x = 150.0f;
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)) fired.add(new Shot((int)shipPos.x, (int)shipPos.y));
		
		 if (shipPos.x < 50) shipPos.x = 50;
         if (shipPos.x > 750) shipPos.x = 750;
         if (shipPos.y < 50) shipPos.y = 50;
         if (shipPos.y > 600) shipPos.y = 600;
	}

	public void update(float deltaSec) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < fired.size(); i++){
			fired.get(i).fire();
		}
		
		Vector2f.add(shipVel, (Vector2f) shipAcc.scale(deltaSec), shipVel);
        Vector2f.add(shipPos, (Vector2f) shipVel.scale(deltaSec), shipPos);
		
	}
	
}
