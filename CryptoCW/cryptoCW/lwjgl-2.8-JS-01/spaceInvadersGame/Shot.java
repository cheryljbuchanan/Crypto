package spaceInvadersGame;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

public class Shot {

	float moveVel = 10; 
	float x,y;
	
	Vector2f sizeHalved = new Vector2f(3f, 1f);
	Vector2f pos = new Vector2f(0.0f, 75f);
	Vector2f vel = new Vector2f(0.0f, 0.0f);
	Vector2f acc = new Vector2f(0.0f, 0.0f);
	
	Player newPlayer = new Player();
	private float yPosition = 0.0f;
	
	public Shot(int x, int y) {
		
		this.x = x;
		this.y = y;
	
	}
	
	public Shot(){
		
	}

	public void drawShot(){
		
		GL11.glPushMatrix();
		
		GL11.glTranslatef(x, yPosition , 0.0f);
		
		//GL11.glTranslatef( a, pos.y + b, 0.0f);
		
		GL11.glLineWidth(5.0f);
		GL11.glBegin(GL11.GL_LINES);
		
		GL11.glVertex2d((pos.x + sizeHalved.x), (pos.y + sizeHalved.y));
		GL11.glVertex2d((pos.x - sizeHalved.x), (pos.y  - sizeHalved.y));
		GL11.glEnd();

		
		
		/*GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor3f(1.0f,1.0f,0.0f);
		GL11.glVertex2d((pos.x + sizeHalved.x) , (pos.y  - sizeHalved.y) );
		GL11.glVertex2d((pos.x + sizeHalved.x) , (pos.y + sizeHalved.y) );
		GL11.glVertex2d((pos.x - sizeHalved.x) , (pos.y + sizeHalved.y) );
		GL11.glVertex2d((pos.x - sizeHalved.x) , (pos.y  - sizeHalved.y) );
		GL11.glEnd();*/
		//vel.y = 150.0f;
		GL11.glPopMatrix();
	}

	public static void add(Shot shot, float f) {
		// TODO Auto-generated method stub
		
	}

	public void fire() {
		// TODO Auto-generated method stub
		drawShot();
		yPosition += moveVel;
	}
	
}
